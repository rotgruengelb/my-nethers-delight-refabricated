package com.soytutta.mynethersdelight.core.mixin;

import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
//? if <26.1.2 {
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
//?}
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(TrunkPlacer.class)
public class KeepResurgentSoilTreeMixin {
	//? if <26.1.2 {
    @Inject(at = @At(value = "HEAD"), method = "setDirtAt", cancellable = true)
    private static void cancelSetDirtIfResurgentSoil(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, CallbackInfo ci) {
        if (level.isStateAtPosition(pos, state -> state.is(MNDBlocks.RESURGENT_SOIL))) {
            ci.cancel();
        }
    }
	//?}
}
