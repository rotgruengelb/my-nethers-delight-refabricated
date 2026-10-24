package com.soytutta.mynethersdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(StemBlock.class)
public class StemBlockMixin {
	//? if >=26.1.2 {
    //@ModifyExpressionValue(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"))
	//?} else {
    @ModifyExpressionValue(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    //?}
    private boolean mynethersdelightrefabricated$modifyRandomTickCallForStems(boolean original, @Local(ordinal = 1) BlockState below) {
        if (below.getBlock() instanceof ResurgentSoilBlock block)
			return block.canSustainPlant((Block)(Object)this).orElse(original);

        if (below.getBlock() instanceof ResurgentSoilFarmlandBlock block)
            return block.canSustainPlant((Block)(Object)this).orElse(original);
        return original;
    }
}
