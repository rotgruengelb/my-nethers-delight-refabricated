package com.soytutta.mynethersdelight.common;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.FloatRange;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.IntegerRange;

import static com.soytutta.mynethersdelight.MyNethersDelight.CONFIG;

public final class MNDConfiguration extends WrappedConfig {
	public Settings settings = new Settings();
	public Farming farming = new Farming();

	public MNDConfiguration() {
	}

	public static boolean enablePiglinFoodTrades() {
		return CONFIG.settings.enablePiglinFoodTrades;
	}

	public static double piglinFoodTradeChance() {
		return CONFIG.settings.piglinFoodTradeChance;
	}

	public static boolean enableFrogMagmaCakeBehavior() {
		return CONFIG.settings.enableFrogMagmaCakeBehavior;
	}

	public static boolean enableResurgentSoilPropagation() {
		return CONFIG.farming.enableResurgentSoilPropagation;
	}

	public static double resurgentSoilTickMultiplier() {
		return CONFIG.farming.resurgentSoilTickMultiplier;
	}

	public static int resurgentSoilGrowthRange() {
		return CONFIG.farming.resurgentSoilGrowthRange;
	}

	public static int resurgentFarmlandHeatSearchRadius() {
		return CONFIG.farming.resurgentFarmlandHeatSearchRadius;
	}

	public static int enableForceInjectLoot() {
		return CONFIG.settings.enableForceInjectLoot;
	}

	public static class Settings implements Section {
		@Comment("Allows Piglins to barter food items and Strider Rocks from this mod." + "Special hunting-related trades are not affected.")
		public boolean enablePiglinFoodTrades = true;

		@Comment("Chance that a Piglin barter gives food or Strider Rocks from this mod." + "0.25 equals 25%; 0 disables these results.")
		@FloatRange(min = 0.0, max = 1.0)
		public double piglinFoodTradeChance = 0.25;

		@Comment("Allows Frogs to seek out Magma Cake and Magma Cake Slices," + "and lets players feed slices directly to a Frog.")
		public boolean enableFrogMagmaCakeBehavior = true;

		@Comment("Force inject loot drops regardless of datapack status.")
		public boolean enableForceInjectLoot = false;
	}

	public static class Farming implements Section {
		@Comment("Allows Resurgent Soil and Resurgent Soil Farmland to propagate nearby plants.")
		public boolean enableResurgentSoilPropagation = true;

		@Comment("Multiplier for growth, transformation and propagation attempts made by Resurgent Soil" + "and Resurgent Soil Farmland. Farmland hydration and drying are not affected.")
		@FloatRange(min = 0.0, max = 16.0)
		public double resurgentSoilTickMultiplier = 1.0;

		@Comment("Maximum vertical distance that Resurgent Soil follows connected plants while applying growth." + "Range: 0 ~ 64.")
		@IntegerRange(min = 0, max = 64)
		public int resurgentSoilGrowthRange = 10;

		@Comment("Horizontal radius that Resurgent Soil Farmland searches for valid heat sources." + "Vertical range is half this value. Set to 0 to disable heat hydration." + "Range: 0 ~ 16.")
		@IntegerRange(min = 0, max = 16)
		public int resurgentFarmlandHeatSearchRadius = 7;
	}
}
