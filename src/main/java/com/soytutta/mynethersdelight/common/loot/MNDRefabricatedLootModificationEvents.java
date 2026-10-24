package com.soytutta.mynethersdelight.common.loot;

import com.google.common.collect.Maps;
import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.MNDConfiguration;
import com.soytutta.mynethersdelight.common.block.feasts.BreadLoafBlock;
import com.soytutta.mynethersdelight.common.block.feasts.MagmaCakeBlock;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.core.mixin.LootItemAccessor;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
// 26.2 split the criterion package into the triggers and the predicates.
//? if >=26.2 {
/*import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
*///?} else {
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
//?}
import net.minecraft.core.HolderLookup;
//? if >=26.3 {
/*import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
*///?}
import com.soytutta.mynethersdelight.common.tag.MNDModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
//? if <26.3 {
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
//?} else {
//import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
//?}
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Map;

public class MNDRefabricatedLootModificationEvents {
	private static final ResourceKey<LootTable> ENTITIES_GHAST = vanillaKey("entities/ghast");
	private static final ResourceKey<LootTable> ENTITIES_HOGLIN = vanillaKey("entities/hoglin");
	private static final ResourceKey<LootTable> ENTITIES_STRIDER = vanillaKey("entities/strider");
	private static final ResourceKey<LootTable> BLOCKS_MAGMA_CAKE_BLOCK = key("blocks/magma_cake_block");
	private static final ResourceKey<LootTable> BLOCKS_BREAD_LOAF_BLOCK = key("blocks/bread_loaf_block");
	public static final ResourceKey<LootTable> MND_BASTION_HOGLIN_STABLE = key("chests/mnd_bastion_hoglin_stable");
	public static final ResourceKey<LootTable> MND_BASTION_TREASURE = key("chests/mnd_bastion_treasure");
	public static final ResourceKey<LootTable> MND_PIGLIN_FOOD_BARTER = key("gameplay/mnd_piglin_bartering");
	public static final ResourceKey<LootTable> MND_PIGLIN_DEFAULT_BARTER = key("gameplay/mnd_piglin_bartering_without_food");

	public static void initialize() {
		LootTableEvents.MODIFY.register(MNDRefabricatedLootModificationEvents::modifyTable);
	}

	private static void modifyTable(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
		if (!source.isBuiltin())
			return;
		chestLoot(key, tableBuilder, registries);
		replaceLoot(key, tableBuilder);
		scavengingLoot(key, tableBuilder, registries);
		slicingLoot(key, tableBuilder, registries);
		piglinBarteringFood(key, tableBuilder, registries);
	}

	private static void piglinBarteringFood(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, HolderLookup.Provider registries) {
		if (key != BuiltInLootTables.PIGLIN_BARTERING) return;
		if (!MNDConfiguration.enablePiglinFoodTrades()) return;

		float foodChance = (float) MNDConfiguration.piglinFoodTradeChance();
		float lootChance = Math.max(0.0f, 1.0f - foodChance);

		//? if >=26.3 {
		/*Holder<LootTable> foodBarter = lootTable(registries, MND_PIGLIN_FOOD_BARTER);
		Holder<LootTable> defaultBarter = lootTable(registries, MND_PIGLIN_DEFAULT_BARTER);
		*///?} else {
		ResourceKey<LootTable> foodBarter = MND_PIGLIN_FOOD_BARTER;
		ResourceKey<LootTable> defaultBarter = MND_PIGLIN_DEFAULT_BARTER;
		//?}

		tableBuilder.withPool(LootPool.lootPool()
				.add(NestedLootTable.lootTableReference(foodBarter))
				.when(LootItemRandomChanceCondition.randomChance(foodChance)));
		tableBuilder.withPool(LootPool.lootPool()
				.add(NestedLootTable.lootTableReference(defaultBarter))
				.when(LootItemRandomChanceCondition.randomChance(lootChance)));
	}

	private static void chestLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, HolderLookup.Provider registries) {
		//? if >=26.3 {
		/*Holder<LootTable> hoglinStable = lootTable(registries, MND_BASTION_HOGLIN_STABLE);
		Holder<LootTable> treasure = lootTable(registries, MND_BASTION_TREASURE);
		*///?} else {
		ResourceKey<LootTable> hoglinStable = MND_BASTION_HOGLIN_STABLE;
		ResourceKey<LootTable> treasure = MND_BASTION_TREASURE;
		//?}

		if (key == BuiltInLootTables.BASTION_HOGLIN_STABLE)
			tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(hoglinStable)));
		if (key == BuiltInLootTables.BASTION_TREASURE)
			tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(treasure)));
	}

	private static void replaceLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder) {
		if (key == ENTITIES_HOGLIN) {
			replaceItems(tableBuilder, Map.of(
					Items.PORKCHOP, MNDItems.HOGLIN_LOIN,
					Items.COOKED_PORKCHOP, MNDItems.COOKED_LOIN));
		}
	}

	public static void replaceItems(LootTable.Builder tableBuilder, Map<Item, Item> items) {
		Map<LootPool.Builder, LootPool.Builder> toAdd = Maps.newHashMap();
		tableBuilder.modifyPools(builder -> {
			LootPool oldLootPool = builder.build();
			boolean replaced = false;
			LootPool.Builder newPool = LootPool.lootPool();
			newPool.setRolls(oldLootPool.rolls);
			newPool.setBonusRolls(oldLootPool.bonusRolls);
			//? if >=26.3 {
			/*oldLootPool.condition.ifPresent(newPool::when);
			oldLootPool.modifier.ifPresent(newPool::apply);
			*///?} else {
			newPool.conditionally(oldLootPool.conditions);
			newPool.apply(oldLootPool.functions);
			//?}
			for (LootPoolEntryContainer container : oldLootPool.entries) {
				if (container instanceof LootItem && container instanceof LootItemAccessor accessor &&
						items.containsKey(accessor.mynethersdelightrefabricated$getItem().value())) {
					newPool.add(LootItem.lootTableItem(items.get(accessor.mynethersdelightrefabricated$getItem().value())));
					replaced = true;
				} else {
					newPool.with(container);
				}
			}
			if (replaced) {
				builder.conditionally(new AlwaysFalseCondition());
				toAdd.put(builder, newPool);
			}
		});
		for (LootPool.Builder builder : toAdd.values()) {
			tableBuilder.withPool(builder);
		}
	}

	private static void scavengingLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, HolderLookup.Provider registries) {
		HolderLookup<Enchantment> enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);
		ItemPredicate.Builder knife = knifePredicate(registries);
		if (key == ENTITIES_GHAST) {
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.GHASTA))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
							entityOf(registries, EntityType.GHAST)))
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.25F, 0.1F)));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.GHASTA).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F))))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.GHAST)))));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.GHASTA))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
							entityOf(registries, EntityType.GHAST)))
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.5F, 0.1F)));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(ModItems.ONION.get()))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
							entityOf(registries, EntityType.GHAST)))
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.025F, 0.01F)));
		}
		if (key == ENTITIES_HOGLIN) {
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.HOGLIN_HIDE))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(false))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.HOGLIN)),
							LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.35F, 0.1F))));
		}
		if (key == ENTITIES_STRIDER) {
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_ROCK))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.STRIDER)),
							LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.2F, 0.1F))));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_ROCK))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.STRIDER)),
							LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.25F, 0.1F))));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_ROCK))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.STRIDER)),
							LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.3F, 0.1F))));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_SLICE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
							entityOf(registries, EntityType.STRIDER)))
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.25F, 0.1F)));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_SLICE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.STRIDER)),
							LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.25F, 0.1F))));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_ROCK))
					.when(AllOfCondition.allOf(
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
									EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(knife))),
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
									entityOf(registries, EntityType.STRIDER)),
							LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.5F, 0.1F))));
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(MNDItems.STRIDER_SLICE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
							entityOf(registries, EntityType.STRIDER))));
		}
	}

	private static void slicingLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, HolderLookup.Provider registries) {
		ItemPredicate.Builder knife = knifePredicate(registries);
		if (key == BLOCKS_MAGMA_CAKE_BLOCK) {
			tableBuilder.modifyPools(builder -> builder.when(InvertedLootItemCondition.invert(MatchTool.toolMatches(knife))));
			for (int bites : MagmaCakeBlock.BITES.getPossibleValues()) {
				for (boolean second : MagmaCakeBlock.SECOND_CAKE.getPossibleValues()) {
					int amount = (second ? 14 : 7) - bites;
					//? if >=26.3 {
					/*LootItemCondition.Builder cakeState = MatchBlock.blockMatches(registries.lookupOrThrow(Registries.BLOCK), MNDBlocks.MAGMA_CAKE_BLOCK,
							StatePropertiesPredicate.Builder.properties()
									.hasProperty(MagmaCakeBlock.BITES, bites)
									.hasProperty(MagmaCakeBlock.SECOND_CAKE, second));
					*///?} else {
					LootItemCondition.Builder cakeState = LootItemBlockStatePropertyCondition.hasBlockStateProperties(MNDBlocks.MAGMA_CAKE_BLOCK)
							.setProperties(StatePropertiesPredicate.Builder.properties()
									.hasProperty(MagmaCakeBlock.BITES, bites)
									.hasProperty(MagmaCakeBlock.SECOND_CAKE, second));
					//?}
					tableBuilder.withPool(LootPool.lootPool()
							.add(LootItem.lootTableItem(MNDItems.MAGMA_CAKE_SLICE)
									.apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount))))
							.when(AllOfCondition.allOf(
									MatchTool.toolMatches(knife),
									cakeState)));
				}
			}
		}
		if (key == BLOCKS_BREAD_LOAF_BLOCK) {
			pastrySlicing(tableBuilder, MNDBlocks.BREAD_LOAF_BLOCK, MNDItems.SLICES_OF_BREAD, BreadLoafBlock.BITES, 5, registries);
		}
	}

	public static void pastrySlicing(LootTable.Builder tableBuilder, Block block, ItemLike slice, IntegerProperty property, int maxValue, HolderLookup.Provider registries) {
		ItemPredicate.Builder knife = knifePredicate(registries);
		tableBuilder.modifyPools(builder -> builder.when(InvertedLootItemCondition.invert(MatchTool.toolMatches(knife))));
		for (int value : property.getPossibleValues()) {
			//? if >=26.3 {
			/*LootItemCondition.Builder blockState = MatchBlock.blockMatches(registries.lookupOrThrow(Registries.BLOCK), block,
					StatePropertiesPredicate.Builder.properties().hasProperty(property, value));
			*///?} else {
			LootItemCondition.Builder blockState = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
					.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value));
			//?}
			tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(slice).apply(SetItemCountFunction.setCount(ConstantValue.exactly(maxValue - value))))
					.when(AllOfCondition.allOf(
							MatchTool.toolMatches(knife),
							blockState)));
		}
	}



	private static ResourceKey<LootTable> vanillaKey(String path) {
		return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace(path));
	}

	//? if >=26.3 {
	
	/*private static Holder<LootTable> lootTable(HolderLookup.Provider registries, ResourceKey<LootTable> key) {
		return registries.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(key);
	}
	*///?}

	private static ResourceKey<LootTable> key(String path) {
		return ResourceKey.create(Registries.LOOT_TABLE, MyNethersDelight.id(path));
	}

	private static ItemPredicate.Builder knifePredicate(HolderLookup.Provider registries) {
		//? if >=1.21.11 {
		//return ItemPredicate.Builder.item().of(registries.lookupOrThrow(Registries.ITEM), MNDModTags.KNIVES);
		//?} else {
		return ItemPredicate.Builder.item().of(MNDModTags.KNIVES);
		//?}
	}

	private static EntityPredicate.Builder entityOf(HolderLookup.Provider registries, EntityType<?> type) {
		//? if >=1.21.11 {
		//return EntityPredicate.Builder.entity().of(registries.lookupOrThrow(Registries.ENTITY_TYPE), type);
		//?} else {
		return EntityPredicate.Builder.entity().of(type);
		//?}
	}
}
