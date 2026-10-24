package com.soytutta.mynethersdelight.core.data.recipes;

import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.ingredient;
import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.shaped;
import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.shapeless;
import com.soytutta.mynethersdelight.common.tag.MNDCommonTags;

import com.soytutta.mynethersdelight.common.crafting.BlazierTemperatureRecipe;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import com.soytutta.mynethersdelight.common.tag.MyCommonTags;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
// 26.2 split the criterion package into the triggers and the predicates.
//? if >=26.2 {
//import net.minecraft.advancements.triggers.InventoryChangeTrigger;
//?} else {
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
//?}
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ConventionalTags;

public class MNDCraftingRecipes {

	public static void register(RecipeOutput output) {
		recipesVanillaAlternatives(output);
		recipesBlocks(output);
		recipesCraftedMeals(output);
		//? if <26.3 {
		SpecialRecipeBuilder.special(FoodServingRecipe::new).save(output, "food_serving");
		//?}
		SpecialRecipeBuilder.special(BlazierTemperatureRecipe::heating)
				.save(output,
						"mynethersdelight:crafting/blazier_heating");
		SpecialRecipeBuilder.special(BlazierTemperatureRecipe::cooling)
				.save(output,
						"mynethersdelight:crafting/blazier_cooling");
	}

	private static void recipesVanillaAlternatives(RecipeOutput output) {
		shapeless(RecipeCategory.MISC, Items.SUGAR)
				.requires(MNDItems.STRIDER_EGG)
				.unlockedBy("has_sugar", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR, MNDItems.STRIDER_EGG))
				.save(output, "mynethersdelight:crafting/sugar_alt");
		shapeless(RecipeCategory.MISC, Items.BLAZE_POWDER, 2)
				.requires(Items.BLAZE_POWDER).requires(MNDItems.PEPPER_POWDER, 3)
				.unlockedBy("has_blaze_powder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_POWDER, MNDItems.PEPPER_POWDER))
				.save(output, "mynethersdelight:crafting/blaze_powder_alt");

		shaped(RecipeCategory.MISC, Items.STICK)
				.pattern("#").pattern("#")
				.define('#', (Ingredient.of(Items.BAMBOO, MNDItems.POWDER_CANNON)))
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON))
				.save(output, "mynethersdelight:crafting/stick_alt");
		shaped(RecipeCategory.REDSTONE, Blocks.TNT)
				.pattern("#s#")
				.pattern("s#s")
				.pattern("#s#")
				.define('#', MNDItems.POWDER_CANNON)
				.define('s', ItemTags.SAND)
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON))
				.save(output, "mynethersdelight:crafting/tnt_alt");
		shaped(RecipeCategory.MISC, Items.SCAFFOLDING, 6)
				.pattern("B#b")
				.pattern("B b")
				.pattern("B b")
				.define('b', (Ingredient.of(Items.BAMBOO, MNDItems.POWDER_CANNON)))
				.define('B', (Ingredient.of(MNDItems.POWDER_CANNON, Items.BAMBOO)))
				.define('#', (Ingredient.of(ModItems.CANVAS.get(), Items.STRING)))
				.unlockedBy("has_powder_cannon_or_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON, ModItems.CANVAS.get()))
				.save(output, "mynethersdelight:crafting/scaffolding_alt");
		shaped(RecipeCategory.MISC, ModBlocks.BAMBOO_BASKET.get())
				.pattern("B b")
				.pattern("# #")
				.pattern("b#B")
				.define('b', (Ingredient.of(Items.BAMBOO, MNDItems.POWDER_CANNON)))
				.define('B', (Ingredient.of(MNDItems.POWDER_CANNON, Items.BAMBOO)))
				.define('#', ModItems.CANVAS.get())
				.unlockedBy("has_powder_cannon_or_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON, ModItems.CANVAS.get()))
				.save(output, "mynethersdelight:crafting/basket_alt");
	}

	private static void recipesBlocks(RecipeOutput output) {
		shaped(RecipeCategory.MISC, MNDItems.BLAZIER)
				.pattern("###")
				.pattern("###")
				.pattern("#b#")
				.define('#', Items.BLAZE_ROD)
				.define('b', Items.NETHER_BRICK)
				.unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
				.save(output, "mynethersdelight:crafting/blazefire");
		MNDRecipeBuilders.stonecutting(Ingredient.of(Items.NETHER_BRICKS), RecipeCategory.BUILDING_BLOCKS, MNDItems.NETHER_BRICKS_CABINET)
				.unlockedBy("has_nether_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICKS))
				.save(output, "mynethersdelight:stonecutting/nether_bricks_cabinet");
		MNDRecipeBuilders.stonecutting(Ingredient.of(Items.RED_NETHER_BRICKS), RecipeCategory.BUILDING_BLOCKS, MNDItems.RED_NETHER_BRICKS_CABINET)
				.unlockedBy("has_nether_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICKS))
				.save(output, "mynethersdelight:stonecutting/red_nether_bricks_cabinet");
		MNDRecipeBuilders.stonecutting(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE, Items.BLACKSTONE), RecipeCategory.BUILDING_BLOCKS, MNDItems.BLACKSTONE_BRICKS_CABINET)
				.unlockedBy("has_blackstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACKSTONE, Items.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE_BRICKS))
				.save(output, "mynethersdelight:stonecutting/blackstone_bricks_cabinet");
		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.BULLET_PEPPER_CRATE, 1)
				.requires(MNDItems.BULLET_PEPPER, 9)
				.unlockedBy("has_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.BULLET_PEPPER))
				.save(output, "mynethersdelight:crafting/bullet_papper_crate");
		shapeless(RecipeCategory.MISC, MNDItems.BULLET_PEPPER, 9)
				.requires(MNDItems.BULLET_PEPPER_CRATE)
				.unlockedBy("has_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.BULLET_PEPPER))
				.save(output, "mynethersdelight:crafting/bullet_papper_crate_alt");

		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.BLOCK_OF_POWDERY_CANNON)
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', MNDItems.POWDER_CANNON)
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON))
				.save(output, "mynethersdelight:crafting/block_of_powdery_cannon");
		shaped(RecipeCategory.MISC, MNDBlocks.POWDERY_CABINET)
				.pattern("XXX")
				.pattern("T T")
				.pattern("XXX")
				.define('X', MNDItems.POWDERY_PLANKS_SLAB).define('T', MNDItems.POWDERY_TRAPDOOR)
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/block_of_powdery_cabinet");
		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.POWDERY_PLANKS, 2)
				.requires(MNDTags.Items.POWDERY_BLOCKS)
				.group("planks")
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON))
				.save(output, "mynethersdelight:crafting/powdery_plank");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.POWDERY_PLANKS_SLAB, 6)
				.pattern("###")
				.define('#', MNDItems.POWDERY_PLANKS)
				.group("wooden_slab")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_slab");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.POWDERY_PLANKS_STAIRS, 4)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', MNDItems.POWDERY_PLANKS)
				.group("wooden_stairs")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_stairs");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.POWDERY_MOSAIC)
				.pattern("#")
				.pattern("#")
				.define('#', MNDItems.POWDERY_PLANKS_SLAB)
				.unlockedBy("has_powdery_mosaic", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_MOSAIC, MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_mosaic");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.POWDERY_MOSAIC_SLAB, 6)
				.pattern("###")
				.define('#', MNDItems.POWDERY_MOSAIC)
				.group("wooden_slab")
				.unlockedBy("has_powdery_mosaic", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_MOSAIC, MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_mosaic_slab");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.POWDERY_MOSAIC_STAIRS, 4)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', MNDItems.POWDERY_MOSAIC)
				.group("wooden_stairs")
				.unlockedBy("has_powdery_mosaic", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_MOSAIC, MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_mosaic_stairs");
		shaped(RecipeCategory.REDSTONE, MNDBlocks.POWDERY_DOOR, 3)
				.pattern("##")
				.pattern("##")
				.pattern("##")
				.define('#', MNDItems.POWDERY_PLANKS)
				.group("wooden_door")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_door");
		shaped(RecipeCategory.REDSTONE, MNDBlocks.POWDERY_TRAPDOOR, 2)
				.pattern("###")
				.pattern("###")
				.define('#', MNDItems.POWDERY_PLANKS)
				.group("wooden_trapdoor")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_trapdoor");
		shaped(RecipeCategory.REDSTONE, MNDBlocks.POWDERY_PRESSURE_PLATE)
				.pattern("##")
				.define('#', MNDItems.POWDERY_PLANKS)
				.group("wooden_pressure_plate")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_pressure_plate");
		shapeless(RecipeCategory.REDSTONE, MNDItems.POWDERY_BUTTON)
				.requires(MNDItems.POWDERY_PLANKS)
				.group("wooden_button")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_button");
		shaped(RecipeCategory.MISC, MNDBlocks.POWDERY_SIGN, 3)
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('#', MNDItems.POWDERY_PLANKS).define('X', Items.STICK)
				.group("wooden_sign")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_sign");
		shaped(RecipeCategory.MISC, MNDBlocks.POWDERY_HANGING_SIGN, 6)
				.pattern("X X")
				.pattern("###")
				.pattern("###")
				.define('#', MNDItems.BLOCK_OF_STRIPPED_POWDERY_CANNON).define('X', Items.CHAIN)
				.group("wooden_hanging_sign")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.BLOCK_OF_STRIPPED_POWDERY_CANNON))
				.save(output, "mynethersdelight:crafting/powdery_hanging_sign");
		shaped(RecipeCategory.MISC, MNDBlocks.POWDERY_FENCE, 3)
				.pattern("#X#")
				.pattern("#X#")
				.define('#', MNDItems.POWDERY_PLANKS).define('X', Items.STICK)
				.group("wooden_fence")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_fence");
		shaped(RecipeCategory.REDSTONE, MNDBlocks.POWDERY_FENCE_GATE)
				.pattern("X#X")
				.pattern("X#X")
				.define('#', MNDItems.POWDERY_PLANKS).define('X', Items.STICK)
				.group("wooden_fence_gate")
				.unlockedBy("has_powdery_planks", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDERY_PLANKS))
				.save(output, "mynethersdelight:crafting/powdery_fence_gate");

		shaped(RecipeCategory.MISC, MNDBlocks.POWDERY_TORCH, 2)
				.pattern("P").pattern("#")
				.define('#', MNDItems.POWDER_CANNON).define('P', MNDItems.BULLET_PEPPER)
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON))
				.save(output, "mynethersdelight:crafting/powdery_torch");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.GOLDEN_TROPHY, 2)
				.pattern("iGi")
				.pattern("iBi")
				.pattern("iSi")
				.define('G', MNDItems.GOLDEN_TROPHY).define('S', MNDItems.SKOGLIN_TROPHY)
				.define('B', Blocks.GOLD_BLOCK).define('i', Items.GOLD_INGOT)
				.group("nether_trophy")
				.unlockedBy("has_golden_trophy", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GOLDEN_TROPHY))
				.save(output, "mynethersdelight:crafting/golden_trophy");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.HOGLIN_TROPHY)
				.pattern("W#W")
				.pattern("bBb")
				.pattern("WGW")
				.define('#', MNDItems.HOGLIN_HIDE).define('G', Items.GOLD_INGOT)
				.define('B', Blocks.BONE_BLOCK).define('b', Items.BONE)
				.define('W', ItemTags.PLANKS)
				.group("nether_trophy")
				.unlockedBy("has_hoglin_hide", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_HIDE))
				.save(output, "mynethersdelight:crafting/hoglin_trophy");
		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.WAXED_HOGLIN_TROPHY)
				.requires(MNDItems.HOGLIN_TROPHY)
				.requires(ingredient(MNDTags.HOGLIN_WAXED))
				.group("nether_trophy")
				.unlockedBy("has_hoglin_trophy", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_TROPHY))
				.save(output, "mynethersdelight:crafting/waxed_hoglin_trophy");
		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.HOGLIN_TROPHY)
				.requires(MNDItems.ZOGLIN_TROPHY)
				.requires(ingredient(MNDTags.HOGLIN_CURE))
				.group("nether_trophy")
				.unlockedBy("has_zoglin_trophy", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.ZOGLIN_TROPHY))
				.save(output, "mynethersdelight:crafting/hoglin_trophy_cure");
		shaped(RecipeCategory.BUILDING_BLOCKS, MNDBlocks.ZOGLIN_TROPHY)
				.pattern("wZw")
				.pattern("Z#Z")
				.pattern("wZw")
				.define('#', MNDItems.HOGLIN_TROPHY).define('Z', Items.ROTTEN_FLESH).define('w', Items.WARPED_FUNGUS)
				.group("nether_trophy")
				.unlockedBy("has_hoglin_trophy", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_TROPHY))
				.save(output, "mynethersdelight:crafting/zoglin_trophy");
		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.HOGLIN_TROPHY)
				.requires(MNDItems.SKOGLIN_TROPHY)
				.requires(Ingredient.of(MNDItems.HOGLIN_HIDE))
				.group("nether_trophy")
				.unlockedBy("has_skoglin_trophy", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.SKOGLIN_TROPHY))
				.save(output, "mynethersdelight:crafting/skoglin_trophy");

		shapeless(RecipeCategory.MISC, MNDItems.NETHER_STOVE)
				.requires(MNDItems.SOUL_NETHER_STOVE)
				.requires(ingredient(MNDTags.STOVE_FIRE_FUEL))
				.group("nether_stove")
				.unlockedBy("has_soul_stove", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.SOUL_NETHER_STOVE))
				.save(output, "mynethersdelight:crafting/nethers_stove_alt0");
		shaped(RecipeCategory.MISC, MNDItems.NETHER_STOVE)
				.pattern("iii")
				.pattern("B B")
				.pattern("BCB")
				.define('i', ConventionalItemTags.NETHER_BRICKS)
				.define('B', Blocks.POLISHED_BLACKSTONE_BRICKS).define('C', Blocks.CAMPFIRE)
				.group("nether_stove")
				.unlockedBy("has_campfire", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CAMPFIRE))
				.save(output, "mynethersdelight:crafting/nethers_stove");

		shapeless(RecipeCategory.MISC, MNDItems.SOUL_NETHER_STOVE)
				.requires(MNDItems.NETHER_STOVE)
				.requires(ingredient(MNDTags.STOVE_SOUL_FUEL))
				.group("soul_nether_stove")
				.unlockedBy("has_stove", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.NETHER_STOVE))
				.save(output, "mynethersdelight:crafting/soul_nethers_stove_alt0");
		shaped(RecipeCategory.MISC, MNDItems.SOUL_NETHER_STOVE)
				.pattern("iii")
				.pattern("B B")
				.pattern("BCB")
				.define('i', ConventionalItemTags.NETHER_BRICKS)
				.define('B', Blocks.POLISHED_BLACKSTONE_BRICKS).define('C', Blocks.SOUL_CAMPFIRE)
				.group("soul_nether_stove")
				.unlockedBy("has_soul_campfire", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.SOUL_CAMPFIRE))
				.save(output, "mynethersdelight:crafting/soul_nethers_stove");

		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.LETIOS_COMPOST, 1)
				.requires(Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL))
				.requires(Items.ROTTEN_FLESH, 2)
				.requires(Ingredient.of(Items.WARPED_ROOTS, Items.CRIMSON_ROOTS))
				.requires(Ingredient.of(Items.CRIMSON_ROOTS, Items.WARPED_ROOTS, ModItems.STRAW.get()))
				.requires(Items.BONE_MEAL, 4)
				.unlockedBy("has_rotten_flesh", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROTTEN_FLESH))
				.unlockedBy("has_roots", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_ROOTS, Items.WARPED_ROOTS))
				.save(output, "mynethersdelight:crafting/letios_compost_from_rotten_flesh");
		shapeless(RecipeCategory.BUILDING_BLOCKS, MNDItems.LETIOS_COMPOST, 1)
				.requires(Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL))
				.requires(Items.BONE_MEAL, 2)
				.requires(Ingredient.of(Items.WARPED_ROOTS, Items.CRIMSON_ROOTS))
				.requires(Ingredient.of(Items.CRIMSON_ROOTS, Items.WARPED_ROOTS, ModItems.STRAW.get()))
				.requires(Items.ROTTEN_FLESH, 4)
				.unlockedBy("has_bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
				.unlockedBy("has_roots", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_ROOTS, Items.WARPED_ROOTS))
				.save(output, "mynethersdelight:crafting/letios_compost_from_bone_alt");
	}

	private static void recipesCraftedMeals(RecipeOutput output) {
		shapeless(RecipeCategory.MISC, MNDItems.BLEEDING_TARTAR)
				.requires(MNDItems.MINCED_STRIDER, 2)
				.requires(ConventionalItemTags.EGGS)
				.requires(Items.BOWL)
				.unlockedBy("has_minced_strider", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.MINCED_STRIDER))
				.save(output, "mynethersdelight:crafting/bleeding_tartar");
		shapeless(RecipeCategory.MISC, MNDItems.HOTDOG)
				.requires(MNDItems.ROASTED_SAUSAGE)
				.requires(ConventionalItemTags.BREAD_FOODS)
				.unlockedBy("has_sausage", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_SAUSAGE))
				.save(output, "mynethersdelight:crafting/hotdog");
		shapeless(RecipeCategory.MISC, MNDItems.HOTDOG_WITH_MIXED_SALAD, 2)
				.requires(ModItems.MIXED_SALAD.get())
				.requires(MNDItems.HOTDOG, 2)
				.unlockedBy("has_hotdog", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOTDOG))
				.save(output, "mynethersdelight:crafting/hotdog_with_mixed_salad");
		shapeless(RecipeCategory.MISC, MNDItems.HOTDOG_WITH_NETHER_SALAD, 2)
				.requires(ModItems.NETHER_SALAD.get())
				.requires(MNDItems.HOTDOG, 2)
				.unlockedBy("has_hotdog", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOTDOG))
				.save(output, "mynethersdelight:crafting/hotdog_with_nether_salad");
		shapeless(RecipeCategory.MISC, MNDItems.STRIDERLOAF_BLOCK)
				.requires(MNDItems.STRIDER_SLICE)
				.requires(MNDItems.MINCED_STRIDER, 3)
				.requires(Items.BOWL)
				.unlockedBy("has_minced_strider", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.MINCED_STRIDER))
				.save(output, "mynethersdelight:crafting/striderloaf");

		shapeless(RecipeCategory.MISC, MNDItems.BLUE_TENDERLOIN_STEAK)
				.requires(MNDItems.COOKED_LOIN)
				.requires(Blocks.WARPED_FUNGUS)
				.requires(Ingredient.of(Items.WARPED_FUNGUS, Items.WARPED_ROOTS, ModItems.STRAW.get()))
				.requires(Items.BOWL)
				.unlockedBy("has_hoglin_loin", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_LOIN))
				.group("blue_tenderloin_steak_group")
				.save(output, "mynethersdelight:crafting/blue_tenderloin_steak");
		shapeless(RecipeCategory.MISC, MNDItems.NETHER_BURGER)
				.requires(ConventionalItemTags.BREAD_FOODS)
				.requires(MNDItems.COOKED_LOIN)
				.requires(Items.TWISTING_VINES)
				.requires(Items.CRIMSON_FUNGUS)
				.requires(Ingredient.of(Items.WARPED_FUNGUS, Items.CRIMSON_FUNGUS))
				.unlockedBy("has_hoglin_loin", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_LOIN))
				.save(output, "mynethersdelight:crafting/nether_burger");
		shapeless(RecipeCategory.MISC, MNDItems.RED_LOIN_STICK)
				.requires(MNDItems.COOKED_LOIN)
				.requires(Blocks.CRIMSON_FUNGUS)
				.requires(Ingredient.of(Items.CRIMSON_FUNGUS, Items.RED_MUSHROOM))
				.requires(Items.STICK)
				.unlockedBy("has_hoglin_loin", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_LOIN))
				.save(output, "mynethersdelight:crafting/red_loin_on_a_stick");
		shapeless(RecipeCategory.MISC, MNDItems.BACON_WRAPPED_SAUSAGE_STICK)
				.requires(MNDItems.ROASTED_SAUSAGE)
				.requires(ModItems.COOKED_BACON.get())
				.requires(Items.STICK)
				.unlockedBy("has_sausage", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_SAUSAGE))
				.save(output, "mynethersdelight:crafting/bacon_wrapped_sausage_stick");
		shapeless(RecipeCategory.MISC, MNDItems.BREAKFAST_SAMPLER)
				.requires(MNDItems.ROASTED_SAUSAGE, 2)
				.requires(Ingredient.of(Items.HONEY_BOTTLE, MNDItems.STRIDER_EGG))
				.requires(MNDCommonTags.FOODS_COOKED_EGG)
				.requires(MNDCommonTags.FOODS_COOKED_EGG)
				.requires(ConventionalItemTags.BREAD_FOODS)
				.requires(Items.BOWL)
				.unlockedBy("has_sausage", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.ROASTED_SAUSAGE))
				.save(output, "mynethersdelight:crafting/breakfast_sampler");
		shaped(RecipeCategory.MISC, MNDItems.GOLDEN_EGG)
				.pattern("###")
				.pattern("#E#")
				.pattern("###")
				.define('E', MyCommonTags.FOODS_BOILED_EGG)
				.define('#', Items.GOLD_INGOT)
				.unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
				.save(output, "mynethersdelight:crafting/golden_egg");

		shapeless(RecipeCategory.MISC, MNDItems.STUFFED_PEPPER)
				.requires(MNDItems.BULLET_PEPPER)
				.requires(MNDCommonTags.FOODS_COOKED_PORK)
				.requires(ConventionalItemTags.MILK_DRINKS)
				.unlockedBy("has_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.BULLET_PEPPER))
				.save(output, "mynethersdelight:crafting/stuffed_pepper");

		shapeless(RecipeCategory.MISC, MNDItems.HOT_CREAM_CONE, 3)
				.requires(MNDItems.HOT_CREAM)
				.requires(MNDItems.POWDER_CANNON)
				.requires(MNDItems.POWDER_CANNON)
				.requires(MNDItems.POWDER_CANNON)
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON, MNDItems.HOT_CREAM, MNDItems.HOT_CREAM_CONE))
				.save(output, "mynethersdelight:crafting/hotcream_cone");

		shapeless(RecipeCategory.MISC, MNDItems.HOT_CREAM, 1)
				.requires(Items.BUCKET)
				.requires(MNDItems.HOT_CREAM_CONE)
				.requires(MNDItems.HOT_CREAM_CONE)
				.requires(MNDItems.HOT_CREAM_CONE)
				.unlockedBy("has_powder_cannon", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.POWDER_CANNON, MNDItems.HOT_CREAM, MNDItems.HOT_CREAM_CONE))
				.save(output, "mynethersdelight:crafting/hotcream_bucket");

		shaped(RecipeCategory.FOOD, MNDItems.TEAR_POPSICLE, 1)
				.pattern(" ii")
				.pattern("i#i")
				.pattern("-i ")
				.define('#', Items.GHAST_TEAR)
				.define('i', Items.ICE)
				.define('-', Items.STICK)
				.unlockedBy("has_ghast", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GHAST_TEAR))
				.save(output, "mynethersdelight:crafting/tear_popsicle");
		shapeless(RecipeCategory.MISC, MNDItems.DRIED_GHAST_WITH_MILK)
				.requires(MNDItems.GHASMATI)
				.requires(ConventionalItemTags.MILK_DRINKS)
				.requires(Items.BOWL)
				.unlockedBy("has_ghasmati", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHASMATI))
				.save(output, "mynethersdelight:crafting/dried_ghast_with_milk");
		shapeless(RecipeCategory.MISC, MNDItems.SIZZLING_PUDDING)
				.requires(MNDItems.GHASMATI)
				.requires(ConventionalItemTags.MILK_DRINKS)
				.requires(ConventionalItemTags.EGGS)
				.requires(Ingredient.of(Items.BLAZE_POWDER, MNDItems.PEPPER_POWDER))
				.requires(Items.BOWL)
				.unlockedBy("has_ghasmati", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHASMATI))
				.save(output, "mynethersdelight:crafting/sizzling_pudding");

		shapeless(RecipeCategory.MISC, MNDItems.SPICY_COTTON)
				.requires(MNDItems.GHASTA)
				.requires(MNDTags.HOT_SPICE)
				.requires(Items.BLAZE_ROD)
				.requires(MNDItems.GHASTA)
				.unlockedBy("has_blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD, MNDItems.GHASTA))
				.save(output, "mynethersdelight:crafting/spicy_cotton");
		shaped(RecipeCategory.MISC, MNDItems.GHASTA_WITH_CREAM_BLOCK)
				.pattern("GGG")
				.pattern("GT#")
				.pattern("GBG")
				.define('G', Ingredient.of(MNDItems.GHASTA, MNDItems.GHASMATI)).define('T', Items.GHAST_TEAR)
				.define('#', Items.MAGMA_CREAM).define('B', Items.BOWL)
				.unlockedBy("has_ghast", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHASMATI, MNDItems.GHASTA))
				.save(output, "mynethersdelight:crafting/ghasta_with_cream");

		shapeless(RecipeCategory.MISC, MNDItems.GHAST_DOUGH, 2)
				.requires(ConventionalItemTags.EGGS)
				.requires(MNDItems.GHASMATI, 2)
				.requires(ConventionalItemTags.EGGS)
				.unlockedBy("has_ghast", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHASMATI, MNDItems.GHASTA))
				.save(output, "mynethersdelight:crafting/ghast_dough");
		shapeless(RecipeCategory.MISC, MNDItems.GHAST_SOURDOUGH)
				.requires(MNDItems.GHAST_DOUGH)
				.requires(MNDCommonTags.FOODS_DOUGH)
				.requires(MNDCommonTags.FOODS_DOUGH)
				.requires(MNDCommonTags.FOODS_DOUGH)
				.unlockedBy("has_ghast", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHASMATI, MNDItems.GHASTA))
				.save(output, "mynethersdelight:crafting/ghast_sourdough");

		shapeless(RecipeCategory.MISC, MNDItems.GHAST_SALAD)
				.requires(MyCommonTags.FOODS_RAW_GHAST)
				.requires(vegetablesPatch())
				.requires(ConventionalItemTags.CARROT_CROPS)
				.requires(Items.BOWL)
				.unlockedBy("has_ghast", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHASMATI, MNDItems.GHASTA))
				.save(output, "mynethersdelight:crafting/ghast_salad");

		shapeless(RecipeCategory.MISC, MNDItems.SPICY_SKEWER)
				.requires(MNDItems.BULLET_PEPPER)
				.requires(MyCommonTags.FOODS_RAW_STRIDER)
				.requires(Items.BLAZE_ROD)
				.requires(MNDItems.BULLET_PEPPER)
				.unlockedBy("has_blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
				.save(output, "mynethersdelight:crafting/spicy_skewer");
		shaped(RecipeCategory.MISC, MNDItems.RAW_STUFFED_HOGLIN)
				.pattern("hwh")
				.pattern("l#l")
				.pattern("hch")
				.define('c', MNDItems.CRIMSON_FUNGUS_COLONY)
				.define('l', MNDItems.HOGLIN_LOIN)
				.define('#', MNDItems.HOGLIN_HIDE)
				.define('h', ModItems.HAM.get())
				.define('w', MNDItems.WARPED_FUNGUS_COLONY)
				.unlockedBy("has_hoglin_hide", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOGLIN_HIDE))
				.save(output, "mynethersdelight:crafting/raw_stuffed_hoglin");

		shapeless(RecipeCategory.MISC, MNDItems.HOT_WINGS_BUCKET)
				.requires(MNDItems.HOT_WINGS, 3)
				.requires(Items.BUCKET)
				.unlockedBy("has_hot_wings", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOT_WINGS_BUCKET, MNDItems.HOT_WINGS))
				.save(output, "mynethersdelight:crafting/hot_wings_bucket");
		shapeless(RecipeCategory.MISC, MNDItems.HOT_WINGS, 3)
				.requires(MNDItems.HOT_WINGS_BUCKET)
				.requires(Items.BOWL, 3)
				.unlockedBy("has_hot_wings", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.HOT_WINGS_BUCKET, MNDItems.HOT_WINGS))
				.save(output, "mynethersdelight:crafting/hot_wings_bucket_alt");

		shapeless(RecipeCategory.MISC, MNDItems.BURNT_ROLL)
				.requires(Items.MAGMA_CREAM)
				//? if >=26.1.2 {
				//.requires(ConventionalItemTags.RAW_MEAT_FOODS)
				//?} else {
				.requires(ConventionalTags.FOODS_RAW_MEATS)
				//?}
				.unlockedBy("has_magma_cream", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGMA_CREAM))
				.save(output, "mynethersdelight:crafting/burnt_roll");
		shapeless(RecipeCategory.MISC, MNDItems.ROCK_SOUP)
				.requires(Items.MAGMA_CREAM, 2)
				.requires(MNDItems.STRIDER_EGG, 2)
				.requires(Items.BOWL)
				.unlockedBy("has_magma_cream", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGMA_CREAM))
				.save(output, "mynethersdelight:crafting/rock_soup");
		shaped(RecipeCategory.MISC, MNDItems.MAGMA_CAKE)
				.pattern("MMM")
				.pattern("PHP")
				.pattern("###")
				.define('H', MNDItems.HOT_CREAM).define('M', Items.MAGMA_CREAM)
				.define('#', ModItems.STRAW.get()).define('P', MNDItems.PEPPER_POWDER)
				.unlockedBy("has_magma_cream", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGMA_CREAM))
				.save(output, "mynethersdelight:crafting/magma_cake");
		shapeless(RecipeCategory.MISC, MNDItems.MAGMA_CAKE)
				.requires(MNDItems.MAGMA_CAKE_SLICE, 7)
				.unlockedBy("has_magma_cream", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGMA_CREAM))
				.save(output, "mynethersdelight:crafting/magma_cake_alt");
	}

	private static Ingredient vegetablesPatch() {
		return DefaultCustomIngredients.difference(ingredient(ConventionalItemTags.VEGETABLE_FOODS), Ingredient.of(Items.MELON_SLICE));
	}
}
