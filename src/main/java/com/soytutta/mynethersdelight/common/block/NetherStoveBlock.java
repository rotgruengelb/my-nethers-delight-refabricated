

package com.soytutta.mynethersdelight.common.block;

import com.soytutta.mynethersdelight.common.utility.MNDItemResults;

//? if <26.3 {
import com.mojang.serialization.MapCodec;
//?}
import com.soytutta.mynethersdelight.common.tag.MNDTags;
//? if <1.21.11 {
import com.soytutta.mynethersdelight.common.block.entity.NetherStoveBlockEntity;
//?}
import com.soytutta.mynethersdelight.common.registry.MNDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
//? if >=1.21.11 {
//import net.minecraft.world.InteractionResult;
//?}
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;
import vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity;
import vectorwing.farmersdelight.common.registry.ModSounds;

public class NetherStoveBlock extends AbstractStoveBlock {
    //? if <26.3 {
    public static final MapCodec<NetherStoveBlock> CODEC = simpleCodec(NetherStoveBlock::new);
    //?}

    public static final BooleanProperty SOUL = BooleanProperty.create("soul");

    public NetherStoveBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LIT, false)
                .setValue(SOUL, false));
    }

    //? if <26.1.2 {
    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    //?}

    @Override
    //? if >=1.21.11 {
    /*public InteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos,
                                           Player player, InteractionHand hand, BlockHitResult hit) {
    *///?} else {
    public net.minecraft.world.ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos,
                                           Player player, InteractionHand hand, BlockHitResult hit) {
    //?}

        var base = super.useItemOn(heldStack, state, level, pos, player, hand, hit);
        if (base.consumesAction()) return base;

        if (state.getValue(LIT)) {
            boolean isSoul = state.getValue(SOUL);
            boolean isNormal = !isSoul;

            if ((isNormal && heldStack.is(MNDTags.STOVE_SOUL_FUEL)) ||
                    (isSoul && heldStack.is(MNDTags.STOVE_FIRE_FUEL))) {

                if (!player.getAbilities().instabuild) {
                    heldStack.shrink(1);
                }

                BlockState newState = state.setValue(SOUL, !isSoul);
                level.setBlock(pos, newState, 11);

                BlockPos front = pos.relative(state.getValue(FACING));

                if (!level.isClientSide) {
                    level.playSound(null, pos,
                            isNormal ? SoundEvents.SOUL_ESCAPE.value() : SoundEvents.GENERIC_EXPLODE.value(),
                            SoundSource.BLOCKS,
                            isNormal ? 1.5F : 1.0F,
                            isNormal ? 0.5F : 1.5F
                    );
                }

                ParticleOptions particle = isNormal ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;

                for (int i = 0; i < 5; i++) {
                    level.addParticle(particle,
                            front.getX() + 0.5,
                            front.getY() + 0.5,
                            front.getZ() + 0.5,
                            0, 0.02, 0);
                }

                return MNDItemResults.SUCCESS;
            }
        }

        return MNDItemResults.PASS;
    }

    //? if <1.21.11 {
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NetherStoveBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide
                ? createTickerHelper(type, MNDBlockEntityTypes.NETHER_STOVE, NetherStoveBlockEntity::particleTick)
                : createTickerHelper(type, MNDBlockEntityTypes.NETHER_STOVE, AbstractStoveBlockEntity::serverTick);
    }
    //?}

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (state.getValue(LIT)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY();
            double z = pos.getZ() + 0.5;

            if (rand.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            ParticleOptions flame = state.getValue(SOUL)
                    ? ParticleTypes.SOUL_FIRE_FLAME
                    : ParticleTypes.FLAME;

            level.addParticle(flame, x, y + 0.2, z, 0, 0, 0);
            level.addParticle(ParticleTypes.SMOKE, x, y + 0.3, z, 0, 0, 0);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SOUL);
    }
}
