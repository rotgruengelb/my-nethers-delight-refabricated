package com.soytutta.mynethersdelight.common.crafting;

import com.soytutta.mynethersdelight.common.block.BlazierBlock;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.registry.MNDRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import com.mojang.serialization.MapCodec;

public class BlazierTemperatureRecipe extends CustomRecipe {

	//? if >=26.1.2 {
	/*public static final BlazierTemperatureRecipe HEATING = new BlazierTemperatureRecipe(true);
	public static final BlazierTemperatureRecipe COOLING = new BlazierTemperatureRecipe(false);
	public static final MapCodec<BlazierTemperatureRecipe> HEATING_MAP_CODEC = MapCodec.unit(BlazierTemperatureRecipe::heating);
	public static final MapCodec<BlazierTemperatureRecipe> COOLING_MAP_CODEC = MapCodec.unit(BlazierTemperatureRecipe::cooling);
	public static final StreamCodec<RegistryFriendlyByteBuf, BlazierTemperatureRecipe> HEATING_STREAM_CODEC = StreamCodec.unit(BlazierTemperatureRecipe.heating());
	public static final StreamCodec<RegistryFriendlyByteBuf, BlazierTemperatureRecipe> COOLING_STREAM_CODEC = StreamCodec.unit(BlazierTemperatureRecipe.cooling());
	*///?}

    private final boolean heating;

	//? if >=26.1.2 {
    /*private BlazierTemperatureRecipe(boolean heating) {
        this.heating = heating;
    }
	*///?} else {
	private BlazierTemperatureRecipe(CraftingBookCategory category, boolean heating) {
        super(category);
        this.heating = heating;
    }
	//?}

	//? if >=26.1.2 {
    /*public static BlazierTemperatureRecipe heating() {
        return HEATING;
    }

    public static BlazierTemperatureRecipe cooling() {
        return COOLING;
    }
	*///?} else {
	public static BlazierTemperatureRecipe heating(CraftingBookCategory category) {
        return new BlazierTemperatureRecipe(category, true);
    }

    public static BlazierTemperatureRecipe cooling(CraftingBookCategory category) {
        return new BlazierTemperatureRecipe(category, false);
    }
	//?}

    public boolean isHeating() {
        return this.heating;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack blazier = ItemStack.EMPTY;
        int blazeRods = 0;

        for (int index = 0; index < input.size(); index++) {
            ItemStack stack = input.getItem(index);
            if (stack.isEmpty()) {
                continue;
            }
            if (stack.is(MNDItems.BLAZIER) && blazier.isEmpty()) {
                blazier = stack;
            } else if (this.heating && stack.is(Items.BLAZE_ROD)) {
                blazeRods++;
            } else {
                return false;
            }
        }

        if (blazier.isEmpty()) {
            return false;
        }
        if (!this.heating) {
            return blazeRods == 0;
        }
        return blazeRods == 2 && canIncrease(blazier);
    }

    @Override
	//? if >=26.1.2 {
    //public ItemStack assemble(CraftingInput input) {
	//?} else {
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
	//?}
        ItemStack blazier = findBlazier(input);
        if (blazier.isEmpty()) {
            return ItemStack.EMPTY;
        }

        BlockItemStateProperties properties = blazier.getOrDefault(
                DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        Boolean lit = properties.get(BlazierBlock.LIT);
        BlazierBlock.HeatLevel heat = properties.get(BlazierBlock.HEAT);
        BlazierBlock.HeatLevel currentHeat = heat == null
                ? BlazierBlock.HeatLevel.SMELTING
                : heat;

        if (!this.heating && Boolean.FALSE.equals(lit)) {
            return new ItemStack(Items.NETHER_BRICK);
        }

        BlazierBlock.HeatLevel resultHeat;
        boolean resultLit;
        if (this.heating) {
            if (Boolean.FALSE.equals(lit)) {
                resultHeat = BlazierBlock.HeatLevel.SMOKING;
            } else {
                resultHeat = currentHeat.increase().orElse(null);
                if (resultHeat == null) {
                    return ItemStack.EMPTY;
                }
            }
            resultLit = true;
        } else {
            resultHeat = currentHeat.decrease().orElse(BlazierBlock.HeatLevel.SMOKING);
            resultLit = currentHeat != BlazierBlock.HeatLevel.SMOKING;
        }

        ItemStack result = blazier.copy();
        result.setCount(1);
        result.set(DataComponents.BLOCK_STATE, properties
                .with(BlazierBlock.HEAT, resultHeat)
                .with(BlazierBlock.LIT, resultLit));
        return result;
    }

    private static ItemStack findBlazier(CraftingInput input) {
        for (int index = 0; index < input.size(); index++) {
            ItemStack stack = input.getItem(index);
            if (stack.is(MNDItems.BLAZIER)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean canIncrease(ItemStack blazier) {
        BlockItemStateProperties properties = blazier.getOrDefault(
                DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        if (Boolean.FALSE.equals(properties.get(BlazierBlock.LIT))) {
            return true;
        }
        BlazierBlock.HeatLevel heat = properties.get(BlazierBlock.HEAT);
        return heat != null && heat != BlazierBlock.HeatLevel.SMELTING;
    }

    //? if <1.21.11 {
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= (this.heating ? 3 : 1);
    }
    //?}

    @Override
    //? if >=1.21.11 {
    //public RecipeSerializer<? extends CustomRecipe> getSerializer() {
    //?} else {
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
    //?}
        return this.heating
                ? MNDRecipeSerializers.BLAZIER_HEATING
                : MNDRecipeSerializers.BLAZIER_COOLING;
    }
}
