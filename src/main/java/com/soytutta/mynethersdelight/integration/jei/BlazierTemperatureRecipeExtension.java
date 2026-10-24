package com.soytutta.mynethersdelight.integration.jei;

import com.soytutta.mynethersdelight.common.block.BlazierBlock;
import com.soytutta.mynethersdelight.common.crafting.BlazierTemperatureRecipe;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.crafting.RecipeHolder;
//? if >=1.21.11 {
//import net.minecraft.world.item.crafting.display.SlotDisplay;
//?}
//? if >=26.1.2 {
//import net.minecraft.world.item.ItemStackTemplate;
//?}

import java.util.ArrayList;
import java.util.List;

public class BlazierTemperatureRecipeExtension
        implements ICraftingCategoryExtension<BlazierTemperatureRecipe> {

    //? if >=1.21.11 {
    /*@Override
    public List<SlotDisplay> getIngredients(RecipeHolder<BlazierTemperatureRecipe> recipeHolder) {
        List<SlotDisplay> displays = new ArrayList<>();
        for (ItemStack stack : ingredientStacks(recipeHolder.value())) {
            //? if >=26.1.2 {
            
            //displays.add(new SlotDisplay.ItemStackSlotDisplay(ItemStackTemplate.fromNonEmptyStack(stack)));
            //?} else {
            displays.add(new SlotDisplay.ItemStackSlotDisplay(stack));
            //?}
        }
        return displays;
    }
    *///? }

    //? if >=1.21.11 {
    //private static List<ItemStack> ingredientStacks(BlazierTemperatureRecipe recipe) {
    //? } else {
    private static List<ItemStack> ingredientStacks(BlazierTemperatureRecipe recipe) {
    //?}
        List<ItemStack> blazierInputs = new ArrayList<>();
        List<ItemStack> outputs = new ArrayList<>();
        List<List<ItemStack>> inputs = new ArrayList<>();

        if (recipe.isHeating()) {
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, false));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));

            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMELTING, true));

            inputs.add(blazierInputs);
            inputs.add(List.of(new ItemStack(Items.BLAZE_ROD)));
            inputs.add(List.of(new ItemStack(Items.BLAZE_ROD)));
        } else {
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMELTING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, false));

            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, false));
            outputs.add(new ItemStack(Items.NETHER_BRICK));

            inputs.add(blazierInputs);
        }

        return inputs.stream().flatMap(List::stream).toList();
    }

    @Override
    public void setRecipe(RecipeHolder<BlazierTemperatureRecipe> recipeHolder,
                          IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper,
                          IFocusGroup focuses) {
        List<ItemStack> blazierInputs = new ArrayList<>();
        List<ItemStack> outputs = new ArrayList<>();
        List<List<ItemStack>> inputs = new ArrayList<>();

        if (recipeHolder.value().isHeating()) {
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, false));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));

            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMELTING, true));

            inputs.add(blazierInputs);
            inputs.add(List.of(new ItemStack(Items.BLAZE_ROD)));
            inputs.add(List.of(new ItemStack(Items.BLAZE_ROD)));
        } else {
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMELTING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            blazierInputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, false));

            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.BAKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.CAMPFIRE, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, true));
            outputs.add(createBlazierStack(BlazierBlock.HeatLevel.SMOKING, false));
            outputs.add(new ItemStack(Items.NETHER_BRICK));

            inputs.add(blazierInputs);
        }

        List<IRecipeSlotBuilder> inputSlots =
                craftingGridHelper.createAndSetInputs(builder, inputs, 0, 0);
        IRecipeSlotBuilder outputSlot =
                craftingGridHelper.createAndSetOutputs(builder, outputs);
        IRecipeSlotBuilder blazierInputSlot =
                inputSlots.get(recipeHolder.value().isHeating() ? 0 : 4);
        builder.createFocusLink(blazierInputSlot, outputSlot);
        builder.setShapeless();
    }

    static ItemStack createBlazierStack(BlazierBlock.HeatLevel heat, boolean lit) {
        ItemStack stack = new ItemStack(MNDItems.BLAZIER);
        stack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY
                .with(BlazierBlock.LIT, lit)
                .with(BlazierBlock.HEAT, heat));
        return stack;
    }
}
