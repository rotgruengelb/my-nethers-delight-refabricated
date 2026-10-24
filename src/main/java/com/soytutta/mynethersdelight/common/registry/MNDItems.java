package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.block.NetherStoveBlock;
import com.soytutta.mynethersdelight.common.item.GoldenEggItem;
import com.soytutta.mynethersdelight.common.item.MNDBlazierItem;
import com.soytutta.mynethersdelight.common.item.HotCreamConeItem;
import com.soytutta.mynethersdelight.common.item.HotCreamItem;
import com.soytutta.mynethersdelight.common.item.StriderEggItem;
import com.soytutta.mynethersdelight.common.item.StriderRockItem;
import com.soytutta.mynethersdelight.common.item.StuffedHoglinBlockItem;
import com.soytutta.mynethersdelight.common.utility.MNDFood;
import com.soytutta.mynethersdelight.common.utility.MNDFoodValues;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
//? if <26.3 {
import net.minecraft.world.item.SignItem;
//?}
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.MushroomColonyItem;

//? if <1.21.11 {
import net.minecraft.world.item.ItemNameBlockItem;
import vectorwing.farmersdelight.common.item.PlaceableItem;
import vectorwing.farmersdelight.common.item.PopsicleItem;
//?}

import java.util.function.Function;

public class MNDItems {
	public static final Item BLAZIER = register("blazier", namedAfterBlock(MNDBlocks.BLAZIER_BLOCK), p -> new MNDBlazierItem(MNDBlocks.BLAZIER_BLOCK, p));
	public static final Item NETHER_BRICKS_CABINET = register("nether_bricks_cabinet", blockItem(), p -> new BlockItem(MNDBlocks.NETHER_BRICKS_CABINET, p));
	public static final Item RED_NETHER_BRICKS_CABINET = register("red_nether_bricks_cabinet", blockItem(), p -> new BlockItem(MNDBlocks.RED_NETHER_BRICKS_CABINET, p));
	public static final Item BLACKSTONE_BRICKS_CABINET = register("blackstone_bricks_cabinet", blockItem(), p -> new BlockItem(MNDBlocks.BLACKSTONE_BRICKS_CABINET, p));
	public static final Item NETHER_STOVE = register("nether_bricks_stove", namedAfterBlock(MNDBlocks.NETHER_STOVE), p -> new BlockItem(MNDBlocks.NETHER_STOVE, p) {
		@Override
		protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
			return super.placeBlock(context, state.setValue(NetherStoveBlock.SOUL, false));
		}
	});
	public static final Item SOUL_NETHER_STOVE = register("nether_bricks_soul_stove", namedAfterBlock(MNDBlocks.NETHER_STOVE), p -> new BlockItem(MNDBlocks.NETHER_STOVE, p) {
		@Override
		protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
			return super.placeBlock(context, state.setValue(NetherStoveBlock.SOUL, true));
		}
	});
	// LETIOS-Compost/soil
	public static final Item LETIOS_COMPOST = register("letios_compost", blockItem(), p -> new BlockItem(MNDBlocks.LETIOS_COMPOST, p));
	public static final Item RESURGENT_SOIL = register("resurgent_soil", blockItem(), p -> new BlockItem(MNDBlocks.RESURGENT_SOIL, p));
	public static final Item RESURGENT_SOIL_FARMLAND = register("resurgent_soil_farmland", blockItem(), p -> new BlockItem(MNDBlocks.RESURGENT_SOIL_FARMLAND, p));
	// POWDERY
	public static final Item BULLET_PEPPER_CRATE = register("bullet_pepper_crate", blockItem(), p -> new BlockItem(MNDBlocks.BULLET_PEPPER_CRATE, p));
	public static final Item POWDER_CANNON = register("powder_cannon", namedAfterBlock(MNDBlocks.POWDERY_CANNON), p -> new BlockItem(MNDBlocks.POWDERY_CANNON, p));
	public static final Item POWDERY_TORCH = register("powdery_torch", blockItem(), p -> standingAndWallItem(MNDBlocks.POWDERY_TORCH, MNDBlocks.WALL_POWDERY_TORCH, p));
	public static final Item POWDERY_CABINET = register("powdery_cabinet", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_CABINET, p));
	public static final Item BLOCK_OF_POWDERY_CANNON = register("powdery_block", blockItem(), p -> new BlockItem(MNDBlocks.BLOCK_OF_POWDERY_CANNON, p));
	public static final Item BLOCK_OF_STRIPPED_POWDERY_CANNON = register("stripped_powdery_block", blockItem(), p -> new BlockItem(MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON, p));
	public static final Item POWDERY_PLANKS = register("powdery_planks", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_PLANKS, p));
	public static final Item POWDERY_PLANKS_STAIRS = register("powdery_stairs", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_PLANKS_STAIRS, p));
	public static final Item POWDERY_PLANKS_SLAB = register("powdery_slab", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_PLANKS_SLAB, p));
	public static final Item POWDERY_MOSAIC = register("powdery_mosaic", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_MOSAIC, p));
	public static final Item POWDERY_MOSAIC_STAIRS = register("powdery_mosaic_stairs", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_MOSAIC_STAIRS, p));
	public static final Item POWDERY_MOSAIC_SLAB = register("powdery_mosaic_slab", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_MOSAIC_SLAB, p));
	public static final Item POWDERY_FENCE = register("powdery_fence", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_FENCE, p));
	public static final Item POWDERY_FENCE_GATE = register("powdery_fence_gate", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_FENCE_GATE, p));
	public static final Item POWDERY_DOOR = register("powdery_door", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_DOOR, p));
	public static final Item POWDERY_TRAPDOOR = register("powdery_trapdoor", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_TRAPDOOR, p));
	public static final Item POWDERY_BUTTON = register("powdery_button", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_BUTTON, p));
	public static final Item POWDERY_PRESSURE_PLATE = register("powdery_pressure_plate", blockItem(), p -> new BlockItem(MNDBlocks.POWDERY_PRESSURE_PLATE, p));
	public static final Item POWDERY_SIGN = register("powdery_sign", blockItem().stacksTo(16), p -> signItem(p, MNDBlocks.POWDERY_SIGN, MNDBlocks.POWDERY_WALL_SIGN));
	public static final Item POWDERY_HANGING_SIGN = register("powdery_hanging_sign", blockItem().stacksTo(16), p -> new HangingSignItem(MNDBlocks.POWDERY_HANGING_SIGN, MNDBlocks.POWDERY_WALL_HANGING_SIGN, p));
	// FUNGUS-Colony
	public static final Item WARPED_FUNGUS_COLONY = register("warped_fungus_colony", blockItem(), p -> new MushroomColonyItem(MNDBlocks.WARPED_FUNGUS_COLONY, p));
	public static final Item CRIMSON_FUNGUS_COLONY = register("crimson_fungus_colony", blockItem(), p -> new MushroomColonyItem(MNDBlocks.CRIMSON_FUNGUS_COLONY, p));
	// STRIDER
	public static final Item STRIDER_ROCK = register("strider_rock", new Item.Properties().stacksTo(16).fireResistant(), StriderRockItem::new);
	public static final Item STRIDER_EGG = register("strider_egg", foodItem(MNDFoodValues.STRIDER_EGG).stacksTo(16), StriderEggItem::new);
	public static final Item ENCHANTED_GOLDEN_EGG = register("enchanted_golden_egg", foodItem(MNDFoodValues.ENCHANTED_GOLDEN_EGG).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true), GoldenEggItem::new);
	public static final Item GOLDEN_EGG = register("golden_egg", foodItem(MNDFoodValues.GOLDEN_EGG).rarity(Rarity.RARE), GoldenEggItem::new);
	public static final Item BOILED_EGG = register("boiled_egg", foodItem(MNDFoodValues.BOILED_EGG), p -> new ConsumableItem(p));
	public static final Item DEVILED_EGG = register("deviled_egg", foodItem(MNDFoodValues.DEVILED_EGG), p -> new ConsumableItem(p));
	public static final Item SCOTCH_EGGS = register("scotch_eggs", bowlFoodItem(MNDFoodValues.SCOTCH_EGGS), p -> new ConsumableItem(p, true));
	public static final Item EGG_SOUP = register("egg_soup", bowlFoodItem(MNDFoodValues.EGG_SOUP), p -> new ConsumableItem(p, true));
	public static final Item STRIDER_SLICE = register("strider_slice", foodItem(MNDFoodValues.STRIDER_SLICE).fireResistant(), p -> new ConsumableItem(p, false));
	public static final Item MINCED_STRIDER = register("minced_strider", foodItem(MNDFoodValues.MINCED_STRIDER).fireResistant(), p -> new ConsumableItem(p));
	public static final Item BLEEDING_TARTAR = register("bleeding_tartar", bowlFoodItem(MNDFoodValues.BLEEDING_TARTAR), p -> new ConsumableItem(p, true));
	public static final Item STRIDER_WITH_GRILLED_FUNGUS = register("strider_with_grilled_fungus", bowlFoodItem(MNDFoodValues.STRIDER_AND_GRILLED_FUNGUS), p -> new ConsumableItem(p, true));
	public static final Item STRIDER_STEW = register("strider_stew", bowlFoodItem(MNDFoodValues.STRIDER_STEW), p -> new ConsumableItem(p, true));
	public static final Item CRIMSON_STROGANOFF = register("crimson_stroganoff", bowlFoodItem(MNDFoodValues.CRIMSON_STROGANOFF), p -> new ConsumableItem(p, true));
	public static final Item STRIDERLOAF_BLOCK = register("striderloaf", namedAfterBlock(MNDBlocks.STRIDERLOAF_BLOCK).stacksTo(1), p -> placeableItem(MNDBlocks.STRIDERLOAF_BLOCK, p));
	public static final Item STRIDERLOAF = register("plate_of_striderloaf", bowlFoodItem(MNDFoodValues.STRIDERLOAF), p -> new ConsumableItem(p, true));
	public static final Item COLD_STRIDERLOAF_BLOCK = register("cold_striderloaf", namedAfterBlock(MNDBlocks.COLD_STRIDERLOAF_BLOCK).stacksTo(1), p -> placeableItem(MNDBlocks.COLD_STRIDERLOAF_BLOCK, p));
	public static final Item COLD_STRIDERLOAF = register("plate_of_cold_striderloaf", bowlFoodItem(MNDFoodValues.COLD_STRIDERLOAF), p -> new ConsumableItem(p, true));
	// HOGLIN
	public static final Item HOGLIN_LOIN = register("hoglin_loin", foodItem(MNDFoodValues.HOGLIN_LOIN), p -> new ConsumableItem(p, false));
	// HOGLIN-Sausage
	public static final Item HOGLIN_SAUSAGE = register("hoglin_sausage", foodItem(MNDFoodValues.HOGLIN_SAUSAGE), p -> new ConsumableItem(p, false));
	public static final Item ROASTED_SAUSAGE = register("roasted_sausage", foodItem(MNDFoodValues.ROASTED_SAUSAGE), p -> new ConsumableItem(p));
	public static final Item HOTDOG = register("hotdog", foodItem(MNDFoodValues.HOTDOG), p -> new ConsumableItem(p, false));
	public static final Item HOTDOG_WITH_MIXED_SALAD = register("hotdog_with_mixed_salad", foodItem(MNDFoodValues.HOTDOG_WITH_MIXED_SALAD), p -> new ConsumableItem(p, true));
	public static final Item HOTDOG_WITH_NETHER_SALAD = register("hotdog_with_nether_salad", foodItem(MNDFoodValues.HOTDOG_WITH_NETHER_SALAD), p -> new ConsumableItem(p, false));
	public static final Item SAUSAGE_AND_POTATOES = register("sausage_and_potatoes", bowlFoodItem(MNDFoodValues.SAUSAGE_AND_POTATOES), p -> new ConsumableItem(p, false));
	public static final Item BREAKFAST_SAMPLER = register("breakfast_sampler", bowlFoodItem(MNDFoodValues.BREAKFAST_SAMPLER), p -> new ConsumableItem(p, true));
	// HOGLIN-Loin
	public static final Item COOKED_LOIN = register("cooked_loin", foodItem(MNDFoodValues.COOKED_LOIN), p -> new ConsumableItem(p));
	public static final Item NETHER_BURGER = register("nether_burger", foodItem(MNDFoodValues.NETHER_BURGER), p -> new ConsumableItem(p));
	public static final Item BLUE_TENDERLOIN_STEAK = register("blue_tenderloin_steak", bowlFoodItem(MNDFoodValues.BLUE_TENDERLOIN_STEAK), p -> new ConsumableItem(p, true));
	public static final Item RED_LOIN_STICK = register("red_loin_on_a_stick", foodItem(MNDFoodValues.RED_LOIN_STICK), p -> new ConsumableItem(p));
	public static final Item BACON_WRAPPED_SAUSAGE_STICK = register("bacon-wrapped_sausage_on_a_stick", foodItem(MNDFoodValues.BACON_WRAPPED_SAUSAGE_STICK), p -> new ConsumableItem(p));
	public static final Item FRIED_HOGLIN_CHOP = register("fried_hoglin_chop", bowlFoodItem(MNDFoodValues.FRIED_HOGLIN_CHOP), p -> new ConsumableItem(p, true));
	// GHAST
	public static final Item GHASTA = register("ghasta", foodItem(MNDFoodValues.GHASTA), p -> new ConsumableItem(p, false));
	public static final Item TWISTED_GHASTA = register("twisted_ghasta", bowlFoodItem(MNDFoodValues.TWISTED_GHASTA), p -> new ConsumableItem(p, true));
	public static final Item GIANT_TAKOYAKI = register("giant_takoyaki", bowlFoodItem(MNDFoodValues.GIANT_TAKOYAKI), p -> new ConsumableItem(p));
	public static final Item FRIES_GHASTA = register("fries_ghasta", foodItem(MNDFoodValues.FRIES_GHASTA), p -> new ConsumableItem(p));
	public static final Item SPICY_NOODLE_SOUP = register("spicy_noodle_soup", bowlFoodItem(MNDFoodValues.SPICY_NOODLE_SOUP), p -> new ConsumableItem(p, true));
	public static final Item SPICY_COTTON = register("spicy_cotton", foodItem(MNDFoodValues.SPICY_COTTON), p -> new ConsumableItem(p, false));
	public static final Item GHASMATI = registerSimple("ghasmati", basicItem());
	public static final Item GHAST_SALAD = register("ghast_salad", bowlFoodItem(MNDFoodValues.GHAST_SALAD), p -> new ConsumableItem(p, true));
	public static final Item DRIED_GHAST_WITH_MILK = register("dried_ghast_with_milk", bowlFoodItem(MNDFoodValues.DRIED_GHAST_WITH_MILK), p -> new ConsumableItem(p, false));
	public static final Item SIZZLING_PUDDING = register("sizzling_pudding", bowlFoodItem(MNDFoodValues.SIZZLING_PUDDING), p -> new ConsumableItem(p, false));
	public static final Item TEAR_POPSICLE = register("tear_popsicle", foodItem(MNDFoodValues.TEAR_POPSICLE).rarity(Rarity.RARE), MNDItems::popsicleItem);
	public static final Item GHASTA_WITH_CREAM_BLOCK = register("ghasta_with_cream", namedAfterBlock(MNDBlocks.GHASTA_WITH_CREAM_BLOCK).stacksTo(1), p -> placeableItem(MNDBlocks.GHASTA_WITH_CREAM_BLOCK, p));
	public static final Item GHASTA_WITH_CREAM = register("plate_of_ghasta_with_cream", bowlFoodItem(MNDFoodValues.GHASTA_WITH_CREAM), p -> new ConsumableItem(p, true));
	public static final Item GHAST_DOUGH = register("ghast_dough", foodItem(MNDFoodValues.GHASTA), p -> new ConsumableItem(p, false));
	public static final Item GHAST_SOURDOUGH = register("ghast_sourdough", foodItem(MNDFoodValues.GHASTA), p -> new ConsumableItem(p, false));
	public static final Item SLICES_OF_BREAD = register("slices_of_bread", foodItem(MNDFoodValues.SLICES_OF_BREAD), p -> new ConsumableItem(p));
	public static final Item TOASTS = register("toasts", foodItem(MNDFoodValues.TOASTS), p -> new ConsumableItem(p));
	public static final Item BREAD_LOAF_BLOCK = register("bread_loaf", namedAfterBlock(MNDBlocks.BREAD_LOAF_BLOCK).stacksTo(16), p -> placeableItem(MNDBlocks.BREAD_LOAF_BLOCK, p));
	// SPICY
	public static final Item BULLET_PEPPER = register("bullet_pepper", MNDFoodValues.properties(MNDFoodValues.BULLET_PEPPER), p -> itemNameBlockItem(MNDBlocks.BULLET_PEPPER, p));
	public static final Item PEPPER_POWDER = registerSimple("pepper_powder", basicItem());
	public static final Item STUFFED_PEPPER = register("stuffed_pepper", foodItem(MNDFoodValues.STUFFED_PEPPER), p -> new ConsumableItem(p, false));
	public static final Item SPICY_SKEWER = register("spicy_skewer", foodItem(MNDFoodValues.SPICY_SKEWER), p -> new ConsumableItem(p, false));
	public static final Item CHILIDOG = register("chilidog", foodItem(MNDFoodValues.CHILIDOG), p -> new ConsumableItem(p, true));
	public static final Item SPICY_HOGLIN_STEW = register("spicy_hoglin_stew", bowlFoodItem(MNDFoodValues.SPICY_HOGLIN_STEW), p -> new ConsumableItem(p, true));
	public static final Item HOT_WINGS = register("hot_wings", bowlFoodItem(MNDFoodValues.HOT_WINGS), p -> new ConsumableItem(p, true));
	public static final Item HOT_WINGS_BUCKET = register("hot_wings_bucket", bucketFoodItem(MNDFoodValues.HOT_WINGS_BUCKET), p -> new ConsumableItem(p, true));
	public static final Item SPICY_CURRY = register("spicy_curry", bowlFoodItem(MNDFoodValues.SPICY_CURRY), p -> new ConsumableItem(p, true));
	// MAGMA CUBE
	public static final Item ROCK_SOUP = register("rock_soup", bowlFoodItem(MNDFoodValues.ROCK_SOUP), p -> new ConsumableItem(p, true));
	public static final Item BURNT_ROLL = register("burnt_roll", foodItem(MNDFoodValues.BURNT_ROLL).fireResistant(), p -> new ConsumableItem(p, false));
	public static final Item MAGMA_CAKE = register("magma_cake_block", blockItem().stacksTo(1).fireResistant(), p -> placeableItem(MNDBlocks.MAGMA_CAKE_BLOCK, p));
	public static final Item MAGMA_CAKE_SLICE = register("magma_cake_slice", foodItem(MNDFoodValues.MAGMA_CAKE_SLICE).stacksTo(16).fireResistant(), p -> new ConsumableItem(p, false, true));
	// LAVA
	public static final Item HOT_CREAM = register("hot_cream", bucketFoodItem(MNDFoodValues.HOT_CREAM), HotCreamItem::new);
	public static final Item HOT_CREAM_CONE = register("hot_cream_cone", foodItem(MNDFoodValues.HOT_CREAM_CONE).stacksTo(16), HotCreamConeItem::new);
	// TROPHY
	public static final Item GOLDEN_TROPHY = register("golden_trophy", blockItem().rarity(Rarity.EPIC), p -> new BlockItem(MNDBlocks.GOLDEN_TROPHY, p));
	public static final Item HOGLIN_TROPHY = register("hoglin_trophy", blockItem(), p -> new BlockItem(MNDBlocks.HOGLIN_TROPHY, p));
	public static final Item WAXED_HOGLIN_TROPHY = register("waxed_hoglin_trophy", blockItem(), p -> new BlockItem(MNDBlocks.WAXED_HOGLIN_TROPHY, p));
	public static final Item ZOGLIN_TROPHY = register("zoglin_trophy", blockItem(), p -> new BlockItem(MNDBlocks.ZOGLIN_TROPHY, p));
	public static final Item SKOGLIN_TROPHY = register("skoglin_trophy", blockItem(), p -> new BlockItem(MNDBlocks.SKOGLIN_TROPHY, p));
	// STUFFED HOGLIN
	public static final Item HOGLIN_HIDE = registerSimple("hoglin_hide", basicItem().rarity(Rarity.UNCOMMON));
	public static final Item RAW_STUFFED_HOGLIN = registerSimple("raw_stuffed_hoglin", basicItem().stacksTo(1).rarity(Rarity.UNCOMMON));
	public static final Item ROAST_STUFFED_HOGLIN = register("roast_stuffed_hoglin", namedAfterBlock(MNDBlocks.STUFFED_HOGLIN).stacksTo(1).rarity(Rarity.UNCOMMON), p -> new StuffedHoglinBlockItem(MNDBlocks.STUFFED_HOGLIN, p));
	public static final Item ROAST_EAR = register("roast_ear", foodItem(MNDFoodValues.ROAST_EAR).rarity(Rarity.UNCOMMON), p -> new ConsumableItem(p));
	public static final Item PLATE_OF_STUFFED_HOGLIN_SNOUT = register("plate_of_stuffed_hoglin_snout", bowlFoodItem(MNDFoodValues.PLATE_OF_STUFFED_HOGLIN_SNOUT).rarity(Rarity.UNCOMMON), p -> new ConsumableItem(p, true));
	public static final Item PLATE_OF_STUFFED_HOGLIN_HAM = register("plate_of_stuffed_hoglin_ham", bowlFoodItem(MNDFoodValues.PLATE_OF_STUFFED_HOGLIN_HAM).rarity(Rarity.UNCOMMON), p -> new ConsumableItem(p, true));
	public static final Item PLATE_OF_STUFFED_HOGLIN = register("plate_of_stuffed_hoglin", bowlFoodItem(MNDFoodValues.PLATE_OF_STUFFED_HOGLIN).rarity(Rarity.UNCOMMON), p -> new ConsumableItem(p, true));

	private static Item.Properties basicItem() {
		return new Item.Properties();
	}

	private static Item.Properties blockItem() {
		//? if >=1.21.11 {
		//return new Item.Properties().useBlockDescriptionPrefix();
		//?} else {
		return new Item.Properties();
		//?}
	}

	private static Item.Properties namedAfterBlock(Block block) {
		//? if >=1.21.11 {
		//return new Item.Properties().overrideDescription(block.getDescriptionId());
		//?} else {
		return new Item.Properties();
		//?}
	}

	private static Item.Properties foodItem(MNDFood food) {
		return MNDFoodValues.properties(food);
	}

	private static Item placeableItem(Block block, Item.Properties properties) {
		//? if >=1.21.11 {
		//return new BlockItem(block, properties);
		//?} else {
		return new PlaceableItem(block, properties);
		//?}
	}

	private static Item standingAndWallItem(Block standing, Block wall, Item.Properties properties) {
		//? if >=1.21.11 {
		//return new StandingAndWallBlockItem(standing, wall, Direction.DOWN, properties);
		//?} else {
		return new StandingAndWallBlockItem(standing, wall, properties, Direction.DOWN);
		//?}
	}

	private static Item signItem(Item.Properties properties, Block standing, Block wall) {
		//? if >=26.3 {

		//return new StandingAndWallBlockItem(standing, wall, Direction.DOWN, properties);
		//?} else if >=1.21.11 {
		//return new SignItem(standing, wall, properties);
		//?} else {
		return new SignItem(properties, standing, wall, Direction.DOWN);
		//?}
	}

	private static Item popsicleItem(Item.Properties properties) {
		//? if >=1.21.11 {
		//return new ConsumableItem(properties);
		//?} else {
		return new PopsicleItem(properties);
		//?}
	}

	private static Item itemNameBlockItem(Block block, Item.Properties properties) {
		//? if >=1.21.11 {
		//return new BlockItem(block, properties.useItemDescriptionPrefix());
		//?} else {
		return new ItemNameBlockItem(block, properties);
		//?}
	}

	private static Item.Properties bowlFoodItem(MNDFood food) {
		return MNDFoodValues.properties(food).craftRemainder(Items.BOWL).stacksTo(16);
	}

	private static Item.Properties bucketFoodItem(MNDFood food) {
		return MNDFoodValues.properties(food).craftRemainder(Items.BUCKET).stacksTo(3);
	}

	private static <I extends Item> I register(String name, Item.Properties properties, Function<Item.Properties, I> factory) {
		ResourceLocation id = MyNethersDelight.id(name);

		//? if >=1.21.11 {

		/*ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
		I item = factory.apply(properties.setId(key));
		Registry.register(BuiltInRegistries.ITEM, key, item);
		*///?} else {
		I item = factory.apply(properties);
		Registry.register(BuiltInRegistries.ITEM, id, item);
		//?}
		return item;
	}

	private static Item registerSimple(String name, Item.Properties properties) {
		return register(name, properties, Item::new);
	}

	public static void initialize() {
	}
}
