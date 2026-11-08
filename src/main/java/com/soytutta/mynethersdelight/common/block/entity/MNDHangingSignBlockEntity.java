package com.soytutta.mynethersdelight.common.block.entity;

import com.soytutta.mynethersdelight.common.registry.MNDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MNDHangingSignBlockEntity extends HangingSignBlockEntity {

    public MNDHangingSignBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState);
	}

	@NotNull
	@Override
	public BlockEntityType<?> getType() {
		return MNDBlockEntityTypes.MND_HSIGN;
	}

	@Override
	public boolean isValidBlockState(BlockState blockState) {
		return this.getType().isValid(blockState);
	}

    @Override
    public int getTextLineHeight() {
        return 9;
    }

    @Override
    public int getMaxTextLineWidth() {
        return 60;
    }

    @Override
    public SoundEvent getSignInteractionFailedSoundEvent() {
        return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL;
    }
}
