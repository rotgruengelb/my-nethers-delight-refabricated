package com.soytutta.mynethersdelight.core.data;

import com.soytutta.mynethersdelight.core.data.recipes.MNDCraftingRecipes;
import com.soytutta.mynethersdelight.core.data.recipes.MNDCookingRecipes;
import com.soytutta.mynethersdelight.core.data.recipes.MNDCuttingRecipes;
import com.soytutta.mynethersdelight.core.data.recipes.MNDSmeltingRecipes;
import com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
//? if >=26.3 {

/*import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
*///?}
//? if >=26.3 {
//import vectorwing.farmersdelight.data.Recipes;
//?}

import java.util.concurrent.CompletableFuture;

public class MNDRecipes extends FabricRecipeProvider {

    public MNDRecipes(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

	//? if >=26.3 {
	/*@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
		return createReloadableRecipeProvider(recipes, advancements);
	}
	*///? } else if >=1.21.11 {
	/*@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		MNDRecipeBuilders.setItems(registries.lookupOrThrow(Registries.ITEM));
		return new RecipeProvider(registries, output) {
			@Override
			public void buildRecipes() {
				registerAll(output);
			}
		};
	}
	*///? } else {
	@Override
	public void buildRecipes(RecipeOutput output) {
		registerAll(output);
	}
	//?}

	@Override
	public String getName() {
		return "My Nether's Delight Recipes";
	}

	//? if >=26.3 {
	
	/*public static RecipeProvider createReloadableRecipeProvider(BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
		return new RecipeProvider(recipes, advancements) {
			@Override
			public void buildRecipes() {
				Recipes.recipeContext = recipes;
				MNDRecipeBuilders.setItems(output.lookup(Registries.ITEM));
				registerAll(output);
			}
		};
	}
	*///?}

	private static void registerAll(RecipeOutput output) {
		MNDSmeltingRecipes.register(output);
		MNDCraftingRecipes.register(output);
		MNDCuttingRecipes.register(output);
		MNDCookingRecipes.register(output);
	}
}
