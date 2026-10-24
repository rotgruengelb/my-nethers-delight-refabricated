package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
//? if >=26.3 {

//import com.mojang.serialization.MapCodec;
//?}
import com.soytutta.mynethersdelight.common.world.configuration.PowderyCaneConfiguration;
import com.soytutta.mynethersdelight.common.world.feature.PowderyCaneFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;

public class MNDBiomeFeatures {
	public static final ResourceKey<Feature<?>> POWDERY_CANE_KEY = ResourceKey.create(
			Registries.FEATURE,
			MyNethersDelight.id("powderycane")
	);

	//? if >=26.3 {
	/*public static final MapCodec<? extends Feature> POWDERY_CANE = Registry.register(
			BuiltInRegistries.FEATURE_TYPE,
			MyNethersDelight.id("powderycane"),
			PowderyCaneFeature.CODEC
	);
	*///?} else {
	public static final Feature<PowderyCaneConfiguration> POWDERY_CANE = Registry.register(
			BuiltInRegistries.FEATURE,
			POWDERY_CANE_KEY,
			new PowderyCaneFeature(PowderyCaneConfiguration.CODEC)
	);
	//?}

	public static void initialize() {

	}
}
