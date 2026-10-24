package com.soytutta.mynethersdelight.core.data;

import com.soytutta.mynethersdelight.common.tag.MNDModTags;

import com.soytutta.mynethersdelight.common.tag.MNDTags;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.tag.MyCommonTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
//? if >=26.2 {

//import net.minecraft.tags.BlockItemTags;
//?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class MNDBlockTags extends FabricTagProvider.BlockTagProvider {

	public MNDBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@SuppressWarnings("unchecked")
	private static ResourceKey<Block>[] keyOf(Block... blocks) {
		return Arrays.stream(blocks)
				.map(b -> b.builtInRegistryHolder().key())
				.toArray(ResourceKey[]::new);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.registerModTags();
		this.registerMinecraftTags();
		this.registerForgeTags();
		this.registerBlockMineables();
	}

	protected void registerBlockMineables() {
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(keyOf(MNDBlocks.BLAZIER_BLOCK, MNDBlocks.NETHER_BRICKS_CABINET, MNDBlocks.RED_NETHER_BRICKS_CABINET, MNDBlocks.BLACKSTONE_BRICKS_CABINET, MNDBlocks.NETHER_STOVE, MNDBlocks.GOLDEN_TROPHY));
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(keyOf(MNDBlocks.HOGLIN_TROPHY, MNDBlocks.WAXED_HOGLIN_TROPHY, MNDBlocks.ZOGLIN_TROPHY, MNDBlocks.SKOGLIN_TROPHY, MNDBlocks.GOLDEN_TROPHY, MNDBlocks.POWDERY_CANNON, MNDBlocks.POWDERY_CANE, MNDBlocks.POWDERY_CHUBBY_SAPLING, MNDBlocks.BULLET_PEPPER, MNDBlocks.POWDERY_CABINET, MNDBlocks.BLOCK_OF_POWDERY_CANNON, MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON, MNDBlocks.POWDERY_MOSAIC, MNDBlocks.POWDERY_MOSAIC_STAIRS, MNDBlocks.POWDERY_MOSAIC_SLAB, MNDBlocks.BULLET_PEPPER_CRATE));
		this.tag(MNDModTags.MINEABLE_WITH_KNIFE).add(keyOf(MNDBlocks.STUFFED_HOGLIN, MNDBlocks.BREAD_LOAF_BLOCK, MNDBlocks.GHASTA_WITH_CREAM_BLOCK, MNDBlocks.STRIDERLOAF_BLOCK, MNDBlocks.MAGMA_CAKE_BLOCK, MNDBlocks.POWDERY_CANNON, MNDBlocks.POWDERY_CANE, MNDBlocks.POWDERY_CHUBBY_SAPLING, MNDBlocks.BULLET_PEPPER));
	}

	protected void registerMinecraftTags() {
		this.tag(BlockTags.FLOWER_POTS).add(keyOf(MNDBlocks.POTTED_BULLET_PEPPER, MNDBlocks.POTTED_POWDERY_CANNON));
		this.tag(BlockTags.PLANKS).add(keyOf(MNDBlocks.POWDERY_PLANKS));
		this.tag(BlockTags.SLABS).add(keyOf(MNDBlocks.POWDERY_MOSAIC_SLAB));
		this.tag(BlockTags.WOODEN_SLABS).add(keyOf(MNDBlocks.POWDERY_PLANKS_SLAB));
		this.tag(BlockTags.STAIRS).add(keyOf(MNDBlocks.POWDERY_MOSAIC_STAIRS));
		this.tag(BlockTags.WOODEN_STAIRS).add(keyOf(MNDBlocks.POWDERY_PLANKS_STAIRS));
		this.tag(BlockTags.WOODEN_FENCES).add(keyOf(MNDBlocks.POWDERY_FENCE));
		this.tag(BlockTags.FENCE_GATES).add(keyOf(MNDBlocks.POWDERY_FENCE_GATE));
		this.tag(BlockTags.WOODEN_DOORS).add(keyOf(MNDBlocks.POWDERY_DOOR));
		this.tag(BlockTags.WOODEN_TRAPDOORS).add(keyOf(MNDBlocks.POWDERY_TRAPDOOR));
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(keyOf(MNDBlocks.POWDERY_PRESSURE_PLATE));
		this.tag(BlockTags.WOODEN_BUTTONS).add(keyOf(MNDBlocks.POWDERY_BUTTON));
		this.tag(BlockTags.SIGNS).add(keyOf(MNDBlocks.POWDERY_WALL_SIGN));
		this.tag(BlockTags.STANDING_SIGNS).add(keyOf(MNDBlocks.POWDERY_SIGN));
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(keyOf(MNDBlocks.POWDERY_WALL_HANGING_SIGN));
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(keyOf(MNDBlocks.POWDERY_HANGING_SIGN));
		this.tag(BlockTags.BAMBOO_PLANTABLE_ON).add(keyOf(MNDBlocks.RESURGENT_SOIL, MNDBlocks.POWDERY_CANNON));
		this.tag(BlockTags.HOGLIN_REPELLENTS).add(keyOf(MNDBlocks.HOGLIN_TROPHY, MNDBlocks.WAXED_HOGLIN_TROPHY, MNDBlocks.ZOGLIN_TROPHY, MNDBlocks.BULLET_PEPPER, MNDBlocks.WARPED_FUNGUS_COLONY));
		this.tag(BlockTags.PIGLIN_REPELLENTS).add(keyOf(MNDBlocks.ZOGLIN_TROPHY, MNDBlocks.BULLET_PEPPER));
		this.tag(BlockTags.GUARDED_BY_PIGLINS).add(keyOf(MNDBlocks.GOLDEN_TROPHY));
		this.tag(BlockTags.WITHER_SUMMON_BASE_BLOCKS).add(keyOf(MNDBlocks.RESURGENT_SOIL));

		this.tag(BlockTags.MUSHROOM_GROW_BLOCK).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		//? if >=26.1.2 {
		//this.tag(BlockTags.GROWS_CROPS).add(keyOf(MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		//?}
		this.tag(BlockTags.SMALL_FLOWERS).add(keyOf(MNDBlocks.BULLET_PEPPER));
		this.tag(BlockTags.FENCES).add(keyOf(MNDBlocks.POWDERY_FENCE));
	}

	protected void registerForgeTags() {
		this.tag(ConventionalBlockTags.STORAGE_BLOCKS).addTag(MyCommonTags.Blocks.STORAGE_BLOCKS_BULLET_PEPPER);
		this.tag(MyCommonTags.Blocks.STORAGE_BLOCKS_BULLET_PEPPER).add(keyOf(MNDBlocks.BULLET_PEPPER_CRATE));
		this.tag(ConventionalBlockTags.STRIPPED_LOGS).add(keyOf(MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON));
		this.tag(BlockTags.DIRT).add(keyOf(MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		this.tag(BlockTags.NYLIUM).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		this.tag(BlockTags.INFINIBURN_NETHER).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		this.tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
		this.tag(BlockTags.SOUL_SPEED_BLOCKS).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, MNDBlocks.RESURGENT_SOIL_FARMLAND));
	}

	protected void registerModTags() {
		this.tag(MNDTags.Blocks.POWDERY_BLOCKS).add(keyOf(MNDBlocks.BLOCK_OF_POWDERY_CANNON, MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON));
		this.tag(MNDTags.BELOW_PROPAGATE_PLANT).add(keyOf(Blocks.SPORE_BLOSSOM, Blocks.HANGING_ROOTS, Blocks.GLOW_LICHEN));
		//? if >=1.21.11 {
		//this.tag(MNDTags.ABOVE_PROPAGATE_PLANT).add(keyOf(Blocks.KELP, Blocks.LILY_PAD, Blocks.DEAD_BUSH, Blocks.GLOW_LICHEN)).addTag(BlockTags.SAPLINGS).addTag(BlockTags.CROPS);
		//?} else {
		this.getOrCreateTagBuilder(MNDTags.ABOVE_PROPAGATE_PLANT).add(keyOf(Blocks.KELP, Blocks.LILY_PAD, Blocks.DEAD_BUSH, Blocks.GLOW_LICHEN)).forceAddTag(BlockTags.SAPLINGS).forceAddTag(BlockTags.CROPS);
		//?}

		this.tag(MNDTags.RESURGENT_SOIL_PLANT).add(keyOf(Blocks.CACTUS))
				.addTag(MNDTags.ABOVE_PROPAGATE_PLANT).addTag(MNDTags.BELOW_PROPAGATE_PLANT);

		this.tag(MNDModTags.CABINETS_BLOCK).add(keyOf(MNDBlocks.NETHER_BRICKS_CABINET, MNDBlocks.RED_NETHER_BRICKS_CABINET, MNDBlocks.BLACKSTONE_BRICKS_CABINET));
		this.tag(MNDModTags.WOODEN_CABINETS_BLOCK).add(keyOf(MNDBlocks.POWDERY_CABINET));
		this.tag(MNDModTags.TRAY_HEAT_SOURCES).add(keyOf(MNDBlocks.MAGMA_CAKE_BLOCK, MNDBlocks.BLAZIER_BLOCK));
		this.tag(MNDModTags.HEAT_SOURCES).add(keyOf(MNDBlocks.NETHER_STOVE, MNDBlocks.BULLET_PEPPER_CRATE));
		this.tag(MNDModTags.WILD_CROPS).add(keyOf(MNDBlocks.BULLET_PEPPER));
		this.tag(MNDModTags.UNAFFECTED_BY_RICH_SOIL).add(keyOf(MNDBlocks.WARPED_FUNGUS_COLONY, MNDBlocks.CRIMSON_FUNGUS_COLONY));
		this.tag(MNDModTags.MUSHROOM_COLONY_GROWABLE_ON).add(keyOf(MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL));
		this.tag(MNDModTags.FEASTS_BLOCK).add(keyOf(MNDBlocks.STUFFED_HOGLIN, MNDBlocks.STRIDERLOAF_BLOCK, MNDBlocks.GHASTA_WITH_CREAM_BLOCK, MNDBlocks.MAGMA_CAKE_BLOCK, MNDBlocks.BREAD_LOAF_BLOCK));

		this.tag(MNDTags.SHOWCASE_ACTIVATORS).add(keyOf(Blocks.WITHER_ROSE, Blocks.SOUL_SAND, MNDBlocks.LETIOS_COMPOST, MNDBlocks.RESURGENT_SOIL, Blocks.NETHER_WART, Blocks.CRIMSON_FUNGUS, MNDBlocks.CRIMSON_FUNGUS_COLONY, Blocks.WARPED_FUNGUS, MNDBlocks.WARPED_FUNGUS_COLONY, Blocks.RED_MUSHROOM, Blocks.BROWN_MUSHROOM, Blocks.TWISTING_VINES, Blocks.WEEPING_VINES, Blocks.BONE_BLOCK)).add(keyOf(ModBlocks.RED_MUSHROOM_COLONY.get(), ModBlocks.BROWN_MUSHROOM_COLONY.get()));
		//? if >=1.21.11 {
		//this.tag(MNDTags.SHOWCASE_FLAMES).add(keyOf(Blocks.MAGMA_BLOCK, MNDBlocks.MAGMA_CAKE_BLOCK, Blocks.LANTERN, Blocks.TORCH, MNDBlocks.POWDERY_TORCH, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN, Blocks.FURNACE, Blocks.SMOKER, Blocks.BLAST_FURNACE, MNDBlocks.NETHER_STOVE)).add(keyOf(ModBlocks.STOVE.get())).addTag(BlockTags.CAMPFIRES).addTag(BlockTags.CANDLES);
		//?} else {
		this.getOrCreateTagBuilder(MNDTags.SHOWCASE_FLAMES).add(keyOf(Blocks.MAGMA_BLOCK, MNDBlocks.MAGMA_CAKE_BLOCK, Blocks.LANTERN, Blocks.TORCH, MNDBlocks.POWDERY_TORCH, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN, Blocks.FURNACE, Blocks.SMOKER, Blocks.BLAST_FURNACE, MNDBlocks.NETHER_STOVE)).add(keyOf(ModBlocks.STOVE.get())).forceAddTag(BlockTags.CAMPFIRES).forceAddTag(BlockTags.CANDLES);
		//?}

		this.tag(MNDTags.LETIOS_ACTIVATORS).add(keyOf(Blocks.TWISTING_VINES_PLANT, Blocks.WEEPING_VINES_PLANT)).addTag(MNDTags.SHOWCASE_ACTIVATORS);
		//? if >=1.21.11 {
		//this.tag(MNDTags.LETIOS_FLAMES).add(keyOf(Blocks.LAVA_CAULDRON, MNDBlocks.POWDERY_CANE, MNDBlocks.POWDERY_CANNON, MNDBlocks.BULLET_PEPPER, MNDBlocks.GHASTA_WITH_CREAM_BLOCK, MNDBlocks.POTTED_BULLET_PEPPER, MNDBlocks.WALL_POWDERY_TORCH, Blocks.WALL_TORCH)).addTag(MNDTags.SHOWCASE_FLAMES).addTag(ModTags.HEAT_SOURCES).addTag(BlockTags.CANDLE_CAKES).addTag(BlockTags.FIRE).add(keyOf(Blocks.LAVA));
		//?} else {
		this.getOrCreateTagBuilder(MNDTags.LETIOS_FLAMES).add(keyOf(Blocks.LAVA_CAULDRON, MNDBlocks.POWDERY_CANE, MNDBlocks.POWDERY_CANNON, MNDBlocks.BULLET_PEPPER, MNDBlocks.GHASTA_WITH_CREAM_BLOCK, MNDBlocks.POTTED_BULLET_PEPPER, MNDBlocks.WALL_POWDERY_TORCH, Blocks.WALL_TORCH)).forceAddTag(MNDTags.SHOWCASE_FLAMES).forceAddTag(ModTags.HEAT_SOURCES).forceAddTag(BlockTags.CANDLE_CAKES).forceAddTag(BlockTags.FIRE).add(keyOf(Blocks.LAVA));
		//?}

		//? if >=1.21.11 {
		//this.tag(MNDTags.POWDERY_CANNON_PLANTABLE_ON).add(keyOf(Blocks.MAGMA_BLOCK, MNDBlocks.POWDERY_CANNON, MNDBlocks.POWDERY_CHUBBY_SAPLING)).addTag(ConventionalBlockTags.GRAVELS).addTag(ConventionalBlockTags.NETHERRACKS).addTag(BlockTags.NYLIUM).addTag(BlockTags.SAND).addTag(BlockTags.DIRT).addTag(BlockTags.BASE_STONE_NETHER).addTag(BlockTags.SOUL_SPEED_BLOCKS);
		//?} else {
		this.getOrCreateTagBuilder(MNDTags.POWDERY_CANNON_PLANTABLE_ON).add(keyOf(Blocks.MAGMA_BLOCK, MNDBlocks.POWDERY_CANNON, MNDBlocks.POWDERY_CHUBBY_SAPLING)).forceAddTag(ConventionalBlockTags.GRAVELS).forceAddTag(ConventionalBlockTags.NETHERRACKS).forceAddTag(BlockTags.NYLIUM).forceAddTag(BlockTags.SAND).forceAddTag(BlockTags.DIRT).forceAddTag(BlockTags.BASE_STONE_NETHER).forceAddTag(BlockTags.SOUL_SPEED_BLOCKS);
		//?}
		this.tag(MNDTags.POWDERY_CANE).add(keyOf(MNDBlocks.POWDERY_CANNON, MNDBlocks.POWDERY_CANE, MNDBlocks.BULLET_PEPPER));
	}
}
