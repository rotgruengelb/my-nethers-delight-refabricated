package com.soytutta.mynethersdelight.common.block;

import com.soytutta.mynethersdelight.common.utility.MNDItemResults;
import com.soytutta.mynethersdelight.common.utility.MNDResults;
import com.soytutta.mynethersdelight.common.utility.MNDTextUtils;

//? if <26.3 {
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
//?}
import java.util.List;
import java.util.function.Consumer;
import java.util.Optional;

import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import org.jetbrains.annotations.Nullable;

import com.soytutta.mynethersdelight.common.MNDConfiguration;
import com.soytutta.mynethersdelight.common.block.entity.BlazierBlockEntity;
import com.soytutta.mynethersdelight.common.item.MNDBlazierItem;
import com.soytutta.mynethersdelight.common.registry.MNDBlockEntityTypes;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
//? if >=1.21.11 {
//import net.minecraft.world.entity.InsideBlockEffectApplier;
//?}
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
//? if >=1.21.11 {
//import net.minecraft.world.item.component.TooltipDisplay;
//?}
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
//? if >=1.21.11 {
//import net.minecraft.world.level.redstone.Orientation;
//?}
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
//? if <1.21.11 {
import net.minecraft.world.level.block.state.properties.DirectionProperty;
//?}
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;

public class BlazierBlock extends BaseEntityBlock {

    //? if <26.3 {
    public static final MapCodec<BlazierBlock> CODEC = RecordCodecBuilder.mapCodec((p) ->
            p.group(propertiesCodec()).apply(p, BlazierBlock::new));
    //?}

    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
    protected static final VoxelShape SHAPE_BLAZEFIRE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0F, 0.0F, 2.0F, 14.0F, 6.0F, 14.0F), BooleanOp.OR);
    protected static final VoxelShape SHAPE_BLAZEFIRE_SMOKING = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0F, 0.0F, 2.0F, 14.0F, 4.0F, 14.0F), BooleanOp.OR);
    private static final int EXTINGUISHED_TOOLTIP_COLOR = 0x8B2635;

    public enum HeatLevel implements StringRepresentable {
        SMELTING("smelting", 0xFFB52A),
        BAKING("baking", 0xF08324),
        CAMPFIRE("campfire", 0xD65A2B),
        SMOKING("smoking", 0xB23A33);

        private final String name;
        private final int tooltipColor;

        HeatLevel(String name, int tooltipColor) {
            this.name = name;
            this.tooltipColor = tooltipColor;
        }

        @Override
        public String getSerializedName() { return this.name; }

        public int getTooltipColor() {
            return this.tooltipColor;
        }

        public static Optional<HeatLevel> byName(String name) {
            for (HeatLevel heat : values()) {
                if (heat.name.equals(name)) {
                    return Optional.of(heat);
                }
            }
            return Optional.empty();
        }

        public Optional<HeatLevel> decrease() {
            return switch (this) {
                case SMELTING -> Optional.of(BAKING);
                case BAKING   -> Optional.of(CAMPFIRE);
                case CAMPFIRE -> Optional.of(SMOKING);
                case SMOKING  -> Optional.empty();
            };
        }

        public Optional<HeatLevel> increase() {
            return switch (this) {
                case SMELTING -> Optional.empty();
                case BAKING   -> Optional.of(SMELTING);
                case CAMPFIRE -> Optional.of(BAKING);
                case SMOKING  -> Optional.of(CAMPFIRE);
            };
        }

        public int getBlazepowderDrop(RandomSource random) {
            return switch (this) {
                case SMELTING -> 1 + random.nextInt(5);
                case BAKING   -> 1 + random.nextInt(4);
                case CAMPFIRE -> 1 + random.nextInt(2);
                case SMOKING  -> random.nextInt(2);
            };
        }
    }

    public static final EnumProperty<HeatLevel> HEAT = EnumProperty.create("heat", HeatLevel.class);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    //? if >=1.21.11 {
    //public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    //?} else {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    //?}

    //? if <26.3 {
    @Override
    public MapCodec<BlazierBlock> codec() { return CODEC; }
    //?}

    public BlazierBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, true)
                .setValue(HEAT, HeatLevel.SMELTING)
                .setValue(FACING, Direction.NORTH));

		LandPathNodeTypesRegistry.registerDynamic(
				this,
				(state, world, pos, neighbor) -> {
					BlazierBlock block = (BlazierBlock) state.getBlock();
					return block.getBlockPathType(state, world, pos, null);
				});
    }

    //? if <1.21.11 {
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag tooltipFlag) {
        MNDBlazierItem.addHeatTooltip(stack, tooltip::add);
    }
    //?}

    private void addHeatTooltip(ItemStack stack, Consumer<Component> tooltip) {
        BlockItemStateProperties properties = stack.getOrDefault(DataComponents.BLOCK_STATE,
                BlockItemStateProperties.EMPTY);
        Boolean lit = properties.get(LIT);
        HeatLevel heat = properties.get(HEAT);
        HeatLevel displayedHeat = heat == null ? HeatLevel.SMELTING : heat;
        String heatName = Boolean.FALSE.equals(lit)
                ? "extinguished"
                : displayedHeat.getSerializedName();
        int heatColor = Boolean.FALSE.equals(lit)
                ? EXTINGUISHED_TOOLTIP_COLOR
                : displayedHeat.getTooltipColor();
        Component heatComponent = Component.translatable(
                        "tooltip.mynethersdelight.blazier.heat." + heatName)
                .withStyle(style -> style.withColor(TextColor.fromRgb(heatColor)));

        tooltip.accept(Boolean.FALSE.equals(lit)
                ? heatComponent
                : Component.translatable("tooltip.mynethersdelight.blazier.heat",
                        heatComponent).withStyle(ChatFormatting.GRAY));
    }

    @Override
    //? if >=1.21.11 {
    /*protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                              BlockPos pos, Player player, InteractionHand hand,
                                              BlockHitResult hitResult) {
    *///?} else {
    protected net.minecraft.world.ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                              BlockPos pos, Player player, InteractionHand hand,
                                              BlockHitResult hitResult) {
    //?}
        if (!true) {
            return MNDItemResults.PASS;
        }
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof BlazierBlockEntity blazeEntity)) {
            return MNDItemResults.PASS;
        }

        ItemStack heldItem = player.getItemInHand(hand);

        if (state.getValue(LIT)) {
            Optional<RecipeHolder<?>> recipeOpt = blazeEntity.getCookableRecipe(heldItem);
            if (recipeOpt.isPresent()) {
                if (!level.isClientSide &&
                        blazeEntity.placeFood(player, heldItem, blazeEntity.getCookTimeForRecipe(recipeOpt.get(), state.getValue(HEAT)))) {
                    player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return MNDItemResults.SUCCESS;
                }
                return MNDItemResults.CONSUME;
            }

            if (!level.isClientSide && blazeEntity.hasFreeSlot()) {
                if (blazeEntity.hasRecipeInLowerHeat(heldItem)) {
                    MNDTextUtils.sendActionBar(player,
                            Component.translatable("block.mynethersdelight.blazier.too_hot"));
                } else if (blazeEntity.hasRecipeInHigherHeat(heldItem)) {
                    MNDTextUtils.sendActionBar(player,
                            Component.translatable("block.mynethersdelight.blazier.too_cold"));
                }
            }
        }

        return MNDItemResults.PASS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if (!true) return InteractionResult.PASS;
        if (!player.isShiftKeyDown()) return InteractionResult.PASS;
        if (!level.isClientSide) {
            boolean isLit = state.getValue(LIT);

            if (!isLit) {
                level.destroyBlock(pos, false, player);
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(Items.NETHER_BRICK));
                return MNDResults.consume();
            }

            HeatLevel current = state.getValue(HEAT);
            Optional<HeatLevel> next = current.decrease();

            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BlazierBlockEntity blazeEntity) {
                Containers.dropContents(level, pos, blazeEntity.getItems());
                blazeEntity.clearContent();
            }

            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(Items.BLAZE_POWDER, level.getRandom().nextInt(2)));

            if (next.isPresent()) {
                level.setBlock(pos, state
                        .setValue(HEAT, next.get())
                        .setValue(LIT, true), 11);
                level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH,
                        SoundSource.BLOCKS, 0.35F, 1.3F);
            } else {
                level.setBlock(pos, state.setValue(LIT, false), 11);
                level.levelEvent(LevelEvent.SOUND_EXTINGUISH_FIRE, pos, 0);
            }

            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }
        return MNDResults.sidedSuccess(level.isClientSide);
    }

    private int damageForHeat(BlazierBlock.HeatLevel heat) {
        return switch (heat) {
            case SMELTING -> 4;
            case BAKING   -> 3;
            case CAMPFIRE -> 2;
            case SMOKING  -> 1;
        };
    }

    @Override
    //? if >=1.21.11 {
    /*protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean isInside) {
        if (true && state.getValue(LIT) && entity instanceof LivingEntity) {
            int damage = damageForHeat(state.getValue(HEAT));
            entity.hurt(level.damageSources().campfire(), damage);
        }
        //? if >=1.21.11 {
        //super.entityInside(state, level, pos, entity, applier, isInside);
        //?} else {
        super.entityInside(state, level, pos, entity);
        //?}
    }
    *///?} else {
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (true && state.getValue(LIT) && entity instanceof LivingEntity) {
            int damage = damageForHeat(state.getValue(HEAT));
            entity.hurt(level.damageSources().campfire(), damage);
        }
        //? if >=1.21.11 {
        //super.entityInside(state, level, pos, entity, applier, isInside);
        //?} else {
        super.entityInside(state, level, pos, entity);
        //?}
    }
    //?}

    @Nullable
    public PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity) {
        return true && state.getValue(LIT)
                ? PathType.DAMAGE_FIRE
                : null;
    }

    @Override
    //? if >=1.21.11 {
    /*protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean isMoving) {
        if (!level.isClientSide) {
            FluidState fluid = level.getFluidState(pos);
            if (fluid.is(FluidTags.WATER)) {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
                level.levelEvent(LevelEvent.SOUND_EXTINGUISH_FIRE, pos, 0);
                return;
            }
        }
        super.neighborChanged(state, level, pos, block, orientation, isMoving);
    }
    *///?} else {
    protected void neighborChanged(BlockState state, Level level, BlockPos pos,
                                   Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            FluidState fluid = level.getFluidState(pos);
            if (fluid.is(FluidTags.WATER)) {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
                level.levelEvent(LevelEvent.SOUND_EXTINGUISH_FIRE, pos, 0);
                return;
            }
        }
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }
    //?}

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (!true) return null;
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (level.getFluidState(pos).is(FluidTags.WATER)) return null;
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(LIT, true)
                .setValue(HEAT, HeatLevel.SMELTING);
    }

    @Override
    //? if >=1.21.11 {
    //protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
    //?} else {
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
    //?}
        ItemStack stack = new ItemStack(MNDItems.BLAZIER);
        stack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY
                .with(LIT, state.getValue(LIT))
                .with(HEAT, state.getValue(HEAT)));
        return stack;
    }

    // 1.21.11 removes the block entity before the block is told about the removal, the
    // items of the blazier are dropped by the block entity itself.
    //? if <1.21.11 {
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos,
                            BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BlazierBlockEntity blazeEntity) {
                Containers.dropContents(level, pos, blazeEntity.getItems());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }
    //?}

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if (state.getValue(LIT)) {
            return state.getValue(HEAT) == HeatLevel.SMOKING ? SHAPE_BLAZEFIRE_SMOKING : SHAPE_BLAZEFIRE;
        } else {
            return PLATE_SHAPE;
        }
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlazierBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        if (!true) return null;
        if (!state.getValue(LIT)) {
            return createTickerHelper(type, MNDBlockEntityTypes.BLAZIER,
                    BlazierBlockEntity::cooldownTick);
        }
        if (level.isClientSide) {
            return createTickerHelper(type, MNDBlockEntityTypes.BLAZIER,
                    BlazierBlockEntity::particleTick);
        } else {
            return createTickerHelper(type, MNDBlockEntityTypes.BLAZIER,
                    BlazierBlockEntity::cookTick);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!true) return;
        if (!state.getValue(LIT)) return;

        HeatLevel heat = state.getValue(HEAT);

        if (random.nextInt(10) == 0) {
            level.playLocalSound(
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.BLAZE_BURN, SoundSource.BLOCKS,
                    0.4F + random.nextFloat() * 0.4F,
                    0.8F + random.nextFloat() * 0.4F,
                    false
            );
        }

        if (random.nextInt(5) != 0) return;

        spawnSmokePair(level, pos, random, 0.3);

        if (heat.ordinal() >= HeatLevel.CAMPFIRE.ordinal()) {
            spawnSmokePair(level, pos, random, 0.3);
        }

        if (heat.ordinal() >= HeatLevel.BAKING.ordinal()) {
            spawnRisingSmoke(level, pos, random, 2, 0.4, 0.1);
        }

        if (heat == HeatLevel.SMELTING) {
            spawnRisingSmoke(level, pos, random, 3, 0.4, 0.08);
        }
    }

    private static void spawnSmokePair(Level level, BlockPos pos, RandomSource random, double spread) {
        level.addParticle(ParticleTypes.SMOKE,
                randX(pos, random, spread),
                pos.getY() + 0.3,
                randZ(pos, random, spread),
                0, 0.02, 0);

        level.addParticle(ParticleTypes.LARGE_SMOKE,
                randX(pos, random, spread * 0.75),
                pos.getY() + 0.2,
                randZ(pos, random, spread * 0.75),
                0, 0.01, 0);
    }

    private static void spawnRisingSmoke(Level level, BlockPos pos, RandomSource random, int count, double spread, double speed) {
        for (int i = 0; i < count; i++) {
            level.addParticle(ParticleTypes.SMOKE,
                    randX(pos, random, spread),
                    pos.getY() + 0.4,
                    randZ(pos, random, spread),
                    (random.nextDouble() - 0.5) * speed,
                    random.nextDouble() * speed,
                    (random.nextDouble() - 0.5) * speed);

            level.addParticle(ParticleTypes.LARGE_SMOKE,
                    randX(pos, random, spread * 0.75),
                    pos.getY() + 0.2,
                    randZ(pos, random, spread * 0.75),
                    0, 0.01, 0);
        }
    }

    private static double randX(BlockPos pos, RandomSource random, double spread) {
        return pos.getX() + 0.5 + (random.nextDouble() - 0.5) * spread;
    }

    private static double randZ(BlockPos pos, RandomSource random, double spread) {
        return pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * spread;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, HEAT, FACING);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}
