package com.soytutta.mynethersdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(CactusBlock.class)
public class CactusBlockMixin {
	//? if >=26.1.2 {
    
    //@ModifyExpressionValue(method = "canSurvive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
	//?} else {
    @ModifyExpressionValue(method = "canSurvive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    //?}
    private boolean mynethersdelightrefabricated$allowPlantsOnCactus(boolean original, BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.below());

		if (state.getBlock() != (Object)this)
            return original;

        if (blockState.getBlock() instanceof ResurgentSoilBlock block)
            return block.canSustainPlant(state).orElse(original);

        if (blockState.getBlock() instanceof ResurgentSoilFarmlandBlock block)
			return block.canSustainPlant(state).orElse(original);

        return original;
    }
}
