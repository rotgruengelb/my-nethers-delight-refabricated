package com.soytutta.mynethersdelight.core.data;

import com.soytutta.mynethersdelight.common.tag.MNDModTags;
import com.soytutta.mynethersdelight.common.tag.MNDCommonTags;

import com.soytutta.mynethersdelight.common.tag.CompatibilityTags;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MyCommonTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
//? if >=26.2 {

//import net.minecraft.tags.BlockItemTags;
//?}
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class MNDItemTags extends FabricTagProvider.ItemTagProvider {

	public MNDItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@SuppressWarnings("unchecked")
	private static ResourceKey<Item>[] keyOf(Item... items) {
		return Arrays.stream(items)
				.map(b -> b.builtInRegistryHolder().key())
				.toArray(ResourceKey[]::new);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.registerCommonTags();
		this.registerModTags();
		this.registerMinecraftTags();
		this.registerCompatibilityTags();
	}

	private void registerCommonTags() {
		this.tag(MyCommonTags.FOODS_GIANT_TENTACLES).add(keyOf(MNDItems.GHASTA));
		this.tag(MyCommonTags.FOODS_MAGMA_CUBE).add(keyOf(MNDItems.MAGMA_CAKE, MNDItems.MAGMA_CAKE_SLICE, MNDItems.ROCK_SOUP, MNDItems.BURNT_ROLL));
		this.tag(MyCommonTags.FOODS_RAW_STRIDER).add(keyOf(MNDItems.STRIDER_SLICE, MNDItems.MINCED_STRIDER));
		this.tag(MyCommonTags.FOODS_RAW_HOGLIN).add(keyOf(MNDItems.HOGLIN_LOIN, MNDItems.HOGLIN_SAUSAGE));
		this.tag(MyCommonTags.FOODS_COOKED_HOGLIN).add(keyOf(MNDItems.COOKED_LOIN, MNDItems.ROASTED_SAUSAGE));
		this.tag(MyCommonTags.FOODS_RAW_SAUSAGE).add(keyOf(MNDItems.HOGLIN_SAUSAGE));
		this.tag(MyCommonTags.FOODS_COOKED_SAUSAGE).add(keyOf(MNDItems.ROASTED_SAUSAGE));
		this.tag(MNDCommonTags.FOODS_RAW_PORK).addTag(MyCommonTags.FOODS_RAW_HOGLIN);
		this.tag(MNDCommonTags.FOODS_COOKED_PORK).addTag(MyCommonTags.FOODS_COOKED_HOGLIN);
		this.tag(MyCommonTags.FOODS_BOILED_EGG).add(keyOf(MNDItems.BOILED_EGG));
		this.tag(MyCommonTags.FOODS_RAW_GHAST).add(keyOf(MNDItems.GHASTA, MNDItems.GHASMATI));
		this.tag(MyCommonTags.FOODS_RICE_PASTA).add(keyOf(MNDItems.GHASTA));
		this.tag(MNDCommonTags.FOODS_PASTA).add(keyOf(MNDItems.GHASTA));
		this.tag(MNDCommonTags.CROPS_RICE).add(keyOf(MNDItems.GHASMATI));
		this.tag(MNDCommonTags.FOODS_DOUGH).add(keyOf(MNDItems.GHAST_DOUGH));
		this.tag(MNDCommonTags.FOODS_COOKED_EGG).addTag(MyCommonTags.FOODS_BOILED_EGG).add(keyOf(MNDItems.GOLDEN_EGG, MNDItems.ENCHANTED_GOLDEN_EGG));
		this.tag(MyCommonTags.Items.STORAGE_BLOCKS_BULLET_PEPPER).add(keyOf(MNDItems.BULLET_PEPPER_CRATE));
	}

	private void registerModTags() {
		this.tag(MNDTags.STUFFED_HOGLIN_ITEMS).add(keyOf(MNDItems.ROAST_EAR, MNDItems.PLATE_OF_STUFFED_HOGLIN, MNDItems.PLATE_OF_STUFFED_HOGLIN_HAM, MNDItems.PLATE_OF_STUFFED_HOGLIN_SNOUT));

		this.tag(MNDTags.Items.POWDERY_BLOCKS).add(keyOf(MNDItems.BLOCK_OF_POWDERY_CANNON, MNDItems.BLOCK_OF_STRIPPED_POWDERY_CANNON));
		this.tag(MNDTags.BLOCK_OF_POWDERY).addTag(MNDTags.Items.POWDERY_BLOCKS);

		//? if >=1.21.11 {
		//this.tag(MNDTags.STOVE_SOUL_FUEL).addTag(ItemTags.SOUL_FIRE_BASE_BLOCKS);
		//?} else {
		this.getOrCreateTagBuilder(MNDTags.STOVE_SOUL_FUEL).forceAddTag(ItemTags.SOUL_FIRE_BASE_BLOCKS);
		//?}
		this.tag(MNDTags.STOVE_FIRE_FUEL).addTag(MNDTags.HOT_SPICE).add(keyOf(Items.BLAZE_ROD, Items.FIRE_CHARGE, Items.MAGMA_CREAM, Items.MAGMA_BLOCK));

		this.tag(MNDTags.HOGLIN_WAXED).add(keyOf(Items.NETHER_WART, Items.HONEYCOMB));
		this.tag(MNDTags.HOGLIN_CURE).add(keyOf(Items.GHAST_TEAR));

		this.tag(MNDTags.BOILED_EGG_CANDIDATE).add(keyOf(MNDItems.STRIDER_EGG));
		this.tag(MNDTags.HOT_SPICE).add(keyOf(MNDItems.BULLET_PEPPER, MNDItems.PEPPER_POWDER, Items.BLAZE_POWDER));
	}

	private void registerMinecraftTags() {
		this.tag(ConventionalItemTags.BREAD_FOODS).add(keyOf(MNDItems.SLICES_OF_BREAD, MNDItems.TOASTS));

		this.tag(ItemTags.SOUL_FIRE_BASE_BLOCKS).add(keyOf(MNDItems.LETIOS_COMPOST, MNDItems.RESURGENT_SOIL, MNDItems.RESURGENT_SOIL_FARMLAND));

		this.tag(ItemTags.MEAT).addTag(MyCommonTags.FOODS_RAW_HOGLIN).addTag(MyCommonTags.FOODS_COOKED_HOGLIN).add(keyOf(MNDItems.MINCED_STRIDER, MNDItems.ROAST_EAR));
		this.tag(ItemTags.HOGLIN_FOOD).add(keyOf(MNDItems.BULLET_PEPPER));
		this.tag(ItemTags.STRIDER_FOOD).add(keyOf(MNDItems.HOT_CREAM_CONE, MNDItems.BULLET_PEPPER));
		this.tag(ItemTags.STRIDER_TEMPT_ITEMS).add(keyOf(MNDItems.HOT_CREAM_CONE));

		this.tag(ItemTags.CAT_FOOD).addTag(MyCommonTags.FOODS_RAW_STRIDER).add(keyOf(MNDItems.GHASMATI));
		this.tag(ItemTags.WOLF_FOOD).add(keyOf(MNDItems.HOTDOG));

		this.tag(ItemTags.PIGLIN_FOOD).addTag(MNDTags.STUFFED_HOGLIN_ITEMS).addTag(MyCommonTags.FOODS_COOKED_HOGLIN).addTag(MyCommonTags.FOODS_RAW_HOGLIN);
		this.tag(ItemTags.PIGLIN_LOVED).add(keyOf(MNDItems.GOLDEN_EGG, MNDItems.ENCHANTED_GOLDEN_EGG, MNDItems.GOLDEN_TROPHY));

		this.tag(ItemTags.PLANKS).add(keyOf(MNDItems.POWDERY_PLANKS));
		this.tag(ItemTags.WOODEN_BUTTONS).add(keyOf(MNDItems.POWDERY_BUTTON));
		this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(keyOf(MNDItems.POWDERY_PRESSURE_PLATE));
		this.tag(ItemTags.WOODEN_TRAPDOORS).add(keyOf(MNDItems.POWDERY_TRAPDOOR));
		this.tag(ItemTags.WOODEN_DOORS).add(keyOf(MNDItems.POWDERY_DOOR));
		this.tag(ItemTags.WOODEN_SLABS).add(keyOf(MNDItems.POWDERY_PLANKS_SLAB));
		this.tag(ItemTags.SLABS).add(keyOf(MNDItems.POWDERY_MOSAIC_SLAB));
		this.tag(ItemTags.WOODEN_STAIRS).add(keyOf(MNDItems.POWDERY_PLANKS_STAIRS));
		this.tag(ItemTags.STAIRS).add(keyOf(MNDItems.POWDERY_MOSAIC_STAIRS));
		this.tag(ItemTags.WOODEN_FENCES).add(keyOf(MNDItems.POWDERY_FENCE));
		this.tag(ItemTags.SIGNS).add(keyOf(MNDItems.POWDERY_SIGN));
		this.tag(ItemTags.HANGING_SIGNS).add(keyOf(MNDItems.POWDERY_HANGING_SIGN));

		this.tag(ItemTags.NON_FLAMMABLE_WOOD).addTag(MNDTags.Items.POWDERY_BLOCKS).add(keyOf(MNDItems.POWDERY_PLANKS, MNDItems.POWDERY_MOSAIC,
				MNDItems.POWDERY_PLANKS_SLAB, MNDItems.POWDERY_MOSAIC_SLAB, MNDItems.POWDERY_PLANKS_STAIRS, MNDItems.POWDERY_MOSAIC_STAIRS,
				MNDItems.POWDERY_FENCE, MNDItems.POWDERY_FENCE_GATE, MNDItems.POWDERY_DOOR, MNDItems.POWDERY_TRAPDOOR,
				MNDItems.POWDERY_PRESSURE_PLATE, MNDItems.POWDERY_BUTTON, MNDItems.POWDERY_SIGN, MNDItems.POWDERY_HANGING_SIGN));
	}

	public void registerCompatibilityTags() {
		this.tag(MNDModTags.FEASTS).add(keyOf(MNDItems.ROAST_STUFFED_HOGLIN, MNDItems.STRIDERLOAF_BLOCK, MNDItems.COLD_STRIDERLOAF_BLOCK, MNDItems.GHASTA_WITH_CREAM_BLOCK, MNDItems.BREAD_LOAF_BLOCK, MNDItems.MAGMA_CAKE));
		this.tag(MNDModTags.WOODEN_CABINETS).add(keyOf(MNDItems.POWDERY_CABINET));
		this.tag(MNDModTags.CABINETS).add(keyOf(MNDItems.NETHER_BRICKS_CABINET, MNDItems.RED_NETHER_BRICKS_CABINET, MNDItems.BLACKSTONE_BRICKS_CABINET));
		this.tag(MNDModTags.WILD_CROPS_ITEM).add(keyOf(MNDItems.BULLET_PEPPER, MNDItems.POWDER_CANNON));
		this.tag(CompatibilityTags.HORROR_LASAGNA_MEATS).add(keyOf(MNDItems.MINCED_STRIDER, MNDItems.HOGLIN_LOIN, MNDItems.HOGLIN_SAUSAGE));
		this.tag(CompatibilityTags.RAW_MEATS).add(keyOf(MNDItems.MINCED_STRIDER, MNDItems.HOGLIN_LOIN, MNDItems.HOGLIN_SAUSAGE));
	}
}
