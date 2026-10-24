package com.soytutta.mynethersdelight.common.block.crops;

//? if <26.3 {
import com.mojang.serialization.MapCodec;
//?}
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
//? if >=1.21.11 {
//import net.minecraft.world.level.ScheduledTickAccess;
//?}
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PowderyCannonSaplingBlock extends Block implements BonemealableBlock {
    //? if <26.3 {
    public static final MapCodec<PowderyCannonSaplingBlock> CODEC = simpleCodec(PowderyCannonSaplingBlock::new);
    //?}
    public PowderyCannonSaplingBlock(Properties properties) {
        super(properties);
    }

    //? if <26.3 {
    public MapCodec<PowderyCannonSaplingBlock> codec() {
        return CODEC;
    }
    //?}
    protected static final VoxelShape SAPLING_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(MNDTags.POWDERY_CANNON_PLANTABLE_ON);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        //? if >=1.21.11 {
		//Vec3 vec3 = state.getOffset(pos);
		//?} else {
		Vec3 vec3 = state.getOffset(level, pos);
		//?}
        return SAPLING_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(3) == 0 && level.isEmptyBlock(pos.above())) {
            this.growBamboo(level, pos);
        }

    }

    @Override
    //? if >=1.21.11 {
    /*protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTicks, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (direction == Direction.UP && (neighborState.is(MNDBlocks.POWDERY_CANNON) || neighborState.is(MNDBlocks.POWDERY_CANE) || neighborState.is(MNDBlocks.BULLET_PEPPER) || neighborState.is(Blocks.BAMBOO))) {
                return MNDBlocks.POWDERY_CANNON.defaultBlockState();
            }

            return state;
        }
    }
    *///?} else {
    public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
        if (!state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (direction == Direction.UP && (offsetState.is(MNDBlocks.POWDERY_CANNON) || offsetState.is(MNDBlocks.POWDERY_CANE) || offsetState.is(MNDBlocks.BULLET_PEPPER) || offsetState.is(Blocks.BAMBOO))) {
                level.setBlock(pos, MNDBlocks.POWDERY_CANNON.defaultBlockState(), 2);
            }

            return state;
        }
    }
    //?}

    @Override
    //? if >=1.21.11 {
    //protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
    //?} else {
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
    //?}
        return new ItemStack(MNDItems.POWDER_CANNON);
    }

    protected void growBamboo(Level level, BlockPos pos) {
        level.setBlock(pos.above(), MNDBlocks.POWDERY_CANNON.defaultBlockState().setValue(BambooStalkBlock.LEAVES, BambooLeaves.SMALL), 3);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growBamboo(level, pos);
    }
}
