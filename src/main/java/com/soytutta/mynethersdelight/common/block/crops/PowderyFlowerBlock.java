package com.soytutta.mynethersdelight.common.block.crops;

import com.soytutta.mynethersdelight.common.utility.MNDItemResults;
import com.soytutta.mynethersdelight.common.utility.MNDTools;

//? if <26.3 {
import com.mojang.serialization.MapCodec;
//?}
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
//? if >=1.21.11 {
//import net.minecraft.world.entity.InsideBlockEffectApplier;
//?}
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

import static com.soytutta.mynethersdelight.common.block.utility.MNDBlockStateProperties.PRESSURE;

public class PowderyFlowerBlock extends BushBlock implements BonemealableBlock {

    //? if <26.3 {
    public static final MapCodec<PowderyFlowerBlock> CODEC = simpleCodec(PowderyFlowerBlock::new);
    //?}
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0);

    public PowderyFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(LIT, false)
                .setValue(PRESSURE, 0));
        LandPathNodeTypesRegistry.registerDynamic(this,
                (state, world, pos, neighbor) -> getBlockPathType(state, world, pos, null));
    }

    //? if <26.3 {
    @Override
    public MapCodec<BushBlock> codec() {
        return CODEC.xmap(block -> block, block -> (PowderyFlowerBlock) block);
    }
    //?}

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockBelow = level.getBlockState(pos.below());
        return blockBelow.is(MNDTags.POWDERY_CANNON_PLANTABLE_ON) || blockBelow.is(MNDTags.POWDERY_CANE);
    }

    @Override
    //? if >=1.21.11 {
    //protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
    //?} else {
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
    //?}
        return new ItemStack(MNDItems.BULLET_PEPPER);
    }

    @Nullable
    public PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity) {
        return PathType.DAMAGE_OTHER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        //? if >=1.21.11 {
        //Vec3 vec3 = state.getOffset(pos);
        //?} else {
        Vec3 vec3 = state.getOffset(worldIn, pos);
        //?}
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    //? if >=1.21.11 {
    /*protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTicks, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            scheduledTicks.scheduleTick(pos, this, 2);
            return state;
        }

        if (direction == Direction.UP && (neighborState.is(MNDBlocks.POWDERY_CANE) || neighborState.is(MNDBlocks.BULLET_PEPPER))) {
            return MNDBlocks.POWDERY_CANE.defaultBlockState()
                    .setValue(PowderyCaneBlock.LIT, state.getValue(LIT))
                    .setValue(PRESSURE, state.getValue(PRESSURE))
                    .setValue(PowderyCaneBlock.AGE, 0);
        }
        return super.updateShape(state, level, scheduledTicks, pos, direction, neighborPos, neighborState, random);
    }
    *///?} else {
    public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 2);
            return state;
        }

        if (direction == Direction.UP && (offsetState.is(MNDBlocks.POWDERY_CANE) || offsetState.is(MNDBlocks.BULLET_PEPPER))) {
            return MNDBlocks.POWDERY_CANE.defaultBlockState()
                    .setValue(PowderyCaneBlock.LIT, state.getValue(LIT))
                    .setValue(PRESSURE, state.getValue(PRESSURE))
                    .setValue(PowderyCaneBlock.AGE, 0);
        }
        return super.updateShape(state, direction, offsetState, level, pos, offsetPos);
    }
    //?}

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, LIT, PRESSURE);
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.isClientSide || !state.is(this)) return;

        int pressure = state.getValue(PRESSURE);
        boolean isLit = state.getValue(LIT);

        if (!state.canSurvive(world, pos)) {
            if (isLit) explodeAndReset(world, pos, state);
            world.destroyBlock(pos, true);
            return;
        }

        if (pressure == 2 && isLit) {
            explodeAndReset(world, pos, state);
            return;
        }

        if (pressure > 0) {
            world.setBlock(pos, state.setValue(PRESSURE, pressure - 1), 2);
            world.scheduleTick(pos, this, 20);
        }
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.is(this)) return;

        int i = state.getValue(AGE);
        if (i < MAX_AGE) {
            int newAge = i + 1;
            BlockState blockstate = state.setValue(AGE, newAge);
            if (newAge == MAX_AGE) {
                blockstate = blockstate.setValue(LIT, true);
            }
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            return;
        }
        BlockState blockBelow = level.getBlockState(pos.below());
        if ((blockBelow.is(MNDBlocks.POWDERY_CANNON))
                || (blockBelow.is(MNDBlocks.RESURGENT_SOIL) && random.nextFloat() < 0.5F)
                || (blockBelow.is(ConventionalBlockTags.GRAVELS) && random.nextFloat() < 0.25F)
                || (blockBelow.is(BlockTags.NYLIUM) && random.nextFloat() < 0.05F)) {
            BlockPos posAbove = pos.above();
            if (level.isEmptyBlock(posAbove) || level.getBlockState(posAbove).canBeReplaced()) {
                BlockState newBulletPepperState = MNDBlocks.BULLET_PEPPER.defaultBlockState();

                if (newBulletPepperState.hasProperty(LIT)) {
                    newBulletPepperState = newBulletPepperState.setValue(LIT, state.getValue(LIT));
                }
                if (newBulletPepperState.hasProperty(PRESSURE)) {
                    newBulletPepperState = newBulletPepperState.setValue(PRESSURE, state.getValue(PRESSURE));
                }
                level.setBlock(posAbove, newBulletPepperState, 3);
            }
        }
        level.scheduleTick(pos, this, 1);
    }

    //? if >=1.21.11 {
    /*protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean isInside) {
        if (!level.isClientSide && entity instanceof LivingEntity && entity.getType() != EntityType.PANDA && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75F, 0.8F));

            if (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) {
                int currentPressure = state.getValue(PRESSURE);
                if (currentPressure < 2) {
                    level.setBlock(pos, state.setValue(PRESSURE, currentPressure + 1), 2);
                }
                level.scheduleTick(pos, this, 1);

                if (state.getValue(LIT)) {
                    explodeAndReset(level, pos, state);
                }
            }
        }
    }
    *///?} else {
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity && entity.getType() != EntityType.PANDA && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75F, 0.8F));

            if (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) {
                int currentPressure = state.getValue(PRESSURE);
                if (currentPressure < 2) {
                    level.setBlock(pos, state.setValue(PRESSURE, currentPressure + 1), 2);
                }
                level.scheduleTick(pos, this, 1);

                if (state.getValue(LIT)) {
                    explodeAndReset(level, pos, state);
                }
            }
        }
    }
    //?}

    //? if >=1.21.11 {
    //protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    //?} else {
    protected net.minecraft.world.ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    //?}
        int i = state.getValue(AGE);
        boolean flag = i == MAX_AGE;
        if (i > 1 && state.getValue(LIT)) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (MNDTools.isKnife(heldItem) || heldItem.is(ConventionalItemTags.SHEAR_TOOLS)) {
                int j = 1 + level.getRandom().nextInt(2);
                popResource(level, pos, new ItemStack(MNDItems.BULLET_PEPPER, j + (flag ? 1 : 0)));
                level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
                BlockState blockstate = state.setValue(AGE, 0).setValue(LIT, false).setValue(PRESSURE, 0);
                level.setBlock(pos, blockstate, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
                return level.isClientSide ? MNDItemResults.SUCCESS : MNDItemResults.CONSUME;
            }
        }
        return !flag && stack.is(Items.BONE_MEAL) ? MNDItemResults.SKIP_DEFAULT : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && state.getValue(LIT)) {
            explodeAndReset(level, pos, state);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && state.getValue(PRESSURE) < 2 && !player.isCrouching()) {
            level.setBlock(pos, state.setValue(PRESSURE, state.getValue(PRESSURE) + 1), 2);
        }

        if (state.getValue(LIT)) {
            ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (!MNDTools.isKnife(heldItem) && !heldItem.is(ConventionalItemTags.SHEAR_TOOLS)) {
                explodeAndReset(level, pos, state);
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (random.nextFloat() < 0.75F && state.getValue(AGE) < MAX_AGE) {
            int newAge = state.getValue(AGE) + 1;
            BlockState updatedState = state.setValue(AGE, newAge);

            if (newAge == MAX_AGE) {
                updatedState = updatedState.setValue(LIT, true);
            }
            level.setBlock(pos, updatedState, 2);
        } else {
            BlockPos posAbove = pos.above();
            if (level.isEmptyBlock(posAbove) || level.getBlockState(posAbove).canBeReplaced()) {
                BlockState newBulletPepperState = MNDBlocks.BULLET_PEPPER.defaultBlockState();

                if (newBulletPepperState.hasProperty(LIT)) {
                    newBulletPepperState = newBulletPepperState.setValue(LIT, state.getValue(LIT));
                }
                if (newBulletPepperState.hasProperty(PRESSURE)) {
                    newBulletPepperState = newBulletPepperState.setValue(PRESSURE, state.getValue(PRESSURE));
                }
                level.setBlock(posAbove, newBulletPepperState, 3);
            }
        }
    }

    private void explodeAndReset(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide && state.getValue(LIT)) {
            level.playSound(null, pos, SoundEvents.CREEPER_PRIMED, SoundSource.BLOCKS, 0.5F, 0.25F);
            level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.55F, false, Level.ExplosionInteraction.NONE);
            level.setBlock(pos, state.setValue(AGE, 0).setValue(LIT, false).setValue(PRESSURE, 0), 2);
        }
    }
}
