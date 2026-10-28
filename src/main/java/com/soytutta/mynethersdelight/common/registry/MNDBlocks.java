package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.block.BlazierBlock;
import com.soytutta.mynethersdelight.common.block.LetiosCompostBlock;
import com.soytutta.mynethersdelight.common.block.MNDHangingSignBlock;
import com.soytutta.mynethersdelight.common.block.MNDStandingSignBlock;
import com.soytutta.mynethersdelight.common.block.MNDWallHangingSignBlock;
import com.soytutta.mynethersdelight.common.block.MNDWallSignBlock;
import com.soytutta.mynethersdelight.common.block.NetherStoveBlock;
import com.soytutta.mynethersdelight.common.block.PepperCrateBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import com.soytutta.mynethersdelight.common.block.StrippableBlock;
import com.soytutta.mynethersdelight.common.block.crops.PowderyCaneBlock;
import com.soytutta.mynethersdelight.common.block.crops.PowderyCannonBlock;
import com.soytutta.mynethersdelight.common.block.crops.PowderyCannonSaplingBlock;
import com.soytutta.mynethersdelight.common.block.crops.PowderyFlowerBlock;
import com.soytutta.mynethersdelight.common.block.feasts.BreadLoafBlock;
import com.soytutta.mynethersdelight.common.block.feasts.GhastaWithCreamBlock;
import com.soytutta.mynethersdelight.common.block.feasts.MagmaCakeBlock;
import com.soytutta.mynethersdelight.common.block.feasts.StriderloafBlock;
import com.soytutta.mynethersdelight.common.block.feasts.StuffedHoglinBlock;
import com.soytutta.mynethersdelight.common.block.trophies.GoldenTrophyBlock;
import com.soytutta.mynethersdelight.common.block.trophies.TrophyBlock;
import com.soytutta.mynethersdelight.common.block.utility.MNDWoodTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class MNDBlocks {
	public static final Block BLAZIER_BLOCK = register("blazier_block", Properties.of().mapColor(MapColor.NETHER).strength(0.75F, 3.0F).lightLevel(state -> {
		if (!state.getValue(BlockStateProperties.LIT)) return 0;
		return switch (state.getValue(BlazierBlock.HEAT)) {
			case SMELTING -> 15;
			case BAKING -> 12;
			case CAMPFIRE -> 8;
			case SMOKING -> 4;
		};
	}), BlazierBlock::new);
	public static final Block NETHER_BRICKS_CABINET = register("nether_bricks_cabinet", Properties.ofFullCopy(Blocks.NETHER_BRICKS), CabinetBlock::new);
	public static final Block RED_NETHER_BRICKS_CABINET = register("red_nether_bricks_cabinet", Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS), CabinetBlock::new);
	public static final Block BLACKSTONE_BRICKS_CABINET = register("blackstone_bricks_cabinet", Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS), CabinetBlock::new);
	public static final Block NETHER_STOVE = register("nether_stove", Properties.ofFullCopy(Blocks.NETHER_BRICKS).lightLevel(litBlockEmission(13)), NetherStoveBlock::new);
	public static final Block BULLET_PEPPER_CRATE = register("bullet_pepper_crate", Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.FIRE).strength(2.0F, 3.0F).sound(SoundType.BAMBOO_WOOD).lightLevel((light) -> 15), PepperCrateBlock::new);
	public static final Block LETIOS_COMPOST = register("letios_compost", Properties.ofFullCopy(Blocks.SOUL_SOIL).mapColor(MapColor.COLOR_BROWN).strength(1.2F).speedFactor(0.4F), LetiosCompostBlock::new);
	public static final Block RESURGENT_SOIL = register("resurgent_soil", Properties.ofFullCopy(Blocks.SOUL_SOIL).sound(SoundType.SOUL_SAND).mapColor(MapColor.COLOR_BROWN).speedFactor(0.4F).randomTicks(), ResurgentSoilBlock::new);
	public static final Block RESURGENT_SOIL_FARMLAND = register("resurgent_soil_farmland", Properties.ofFullCopy(Blocks.FARMLAND).sound(SoundType.SOUL_SAND).mapColor(MapColor.COLOR_BROWN).speedFactor(0.4F).lightLevel(flameBlockEmission(10)), ResurgentSoilFarmlandBlock::new);
	public static final Block WARPED_FUNGUS_COLONY = register("warped_fungus_colony", Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).sound(SoundType.FUNGUS), p -> new MushroomColonyBlock(Items.WARPED_FUNGUS.builtInRegistryHolder(), p));
	public static final Block CRIMSON_FUNGUS_COLONY = register("crimson_fungus_colony", Properties.ofFullCopy(Blocks.RED_MUSHROOM).sound(SoundType.FUNGUS), p -> new MushroomColonyBlock(Items.CRIMSON_FUNGUS.builtInRegistryHolder(), p));
	public static final Block POWDERY_CHUBBY_SAPLING = register("powdery_chubby_sapling", Properties.ofFullCopy(Blocks.BAMBOO_SAPLING).mapColor(MapColor.TERRACOTTA_BLACK).strength(2.0F, 15.0F).sound(SoundType.BAMBOO_SAPLING), PowderyCannonSaplingBlock::new);
	public static final Block POWDERY_CANNON = register("powdery_cannon", Properties.ofFullCopy(Blocks.BAMBOO).mapColor(MapColor.TERRACOTTA_BLACK).strength(30.0F, 3.0F).sound(SoundType.BAMBOO).lightLevel(litBlockEmission(12)), PowderyCannonBlock::new);
	public static final Block POWDERY_CANE = register("powdery_cane", Properties.ofFullCopy(Blocks.BAMBOO).mapColor(MapColor.TERRACOTTA_BLACK).noCollission().instabreak().sound(SoundType.BAMBOO).lightLevel(litBlockEmission(12)), PowderyCaneBlock::new);
	public static final Block BULLET_PEPPER = register("bullet_pepper", Properties.ofFullCopy(Blocks.BAMBOO_SAPLING).mapColor(MapColor.TERRACOTTA_BLACK).noCollission().instabreak().sound(SoundType.BAMBOO_SAPLING).lightLevel(litBlockEmission(8)), PowderyFlowerBlock::new);
	public static final Block POWDERY_TORCH = register("powdery_torch", Properties.of().noCollission().instabreak().lightLevel((light) -> 8).sound(SoundType.BAMBOO), p -> new TorchBlock(ParticleTypes.FLAME, p));
	public static final Block WALL_POWDERY_TORCH = register("wall_powdery_torch", dropsLike(Properties.of(), POWDERY_TORCH).noCollission().instabreak().lightLevel((light) -> 8).sound(SoundType.BAMBOO), p -> new WallTorchBlock(ParticleTypes.FLAME, p));
	public static final Block POTTED_POWDERY_CANNON = register("potted_powdery_cannon", Properties.of().instabreak().noOcclusion(), p -> new FlowerPotBlock(POWDERY_CANNON, p));
	public static final Block POTTED_BULLET_PEPPER = register("potted_bullet_pepper", Properties.of().instabreak().noOcclusion().lightLevel((light) -> 8), p -> new FlowerPotBlock(BULLET_PEPPER, p));
	public static final Block POWDERY_CABINET = register("powdery_cabinet", Properties.ofFullCopy(Blocks.BARREL).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), CabinetBlock::new);
	public static final Block BLOCK_OF_POWDERY_CANNON = register("powdery_block", Properties.ofFullCopy(Blocks.CRIMSON_STEM).sound(SoundType.BAMBOO_WOOD).instrument(NoteBlockInstrument.BASS).explosionResistance(180.0F).mapColor(MapColor.TERRACOTTA_BLACK), StrippableBlock::new);
	public static final Block BLOCK_OF_STRIPPED_POWDERY_CANNON = register("stripped_powdery_block", Properties.ofFullCopy(Blocks.CRIMSON_STEM).sound(SoundType.BAMBOO_WOOD).instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_GRAY), RotatedPillarBlock::new);
	public static final Block POWDERY_PLANKS = register("powdery_planks", Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), Block::new);
	public static final Block POWDERY_PLANKS_STAIRS = register("powdery_stairs", Properties.ofFullCopy(Blocks.CRIMSON_STAIRS).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), p -> new StairBlock(POWDERY_PLANKS.defaultBlockState(), p));
	public static final Block POWDERY_PLANKS_SLAB = register("powdery_slab", Properties.ofFullCopy(Blocks.CRIMSON_SLAB).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), SlabBlock::new);
	public static final Block POWDERY_MOSAIC = register("powdery_mosaic", Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), Block::new);
	public static final Block POWDERY_MOSAIC_STAIRS = register("powdery_mosaic_stairs", Properties.ofFullCopy(Blocks.CRIMSON_STAIRS).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), p -> new StairBlock(POWDERY_PLANKS.defaultBlockState(), p));
	public static final Block POWDERY_MOSAIC_SLAB = register("powdery_mosaic_slab", Properties.ofFullCopy(Blocks.CRIMSON_SLAB).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), SlabBlock::new);
	public static final Block POWDERY_FENCE = register("powdery_fence", Properties.ofFullCopy(Blocks.CRIMSON_FENCE).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY), FenceBlock::new);
	public static final Block POWDERY_FENCE_GATE = register("powdery_fence_gate", Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE).mapColor(MapColor.TERRACOTTA_GRAY), p -> new FenceGateBlock(WoodType.BAMBOO, p));
	public static final Block POWDERY_DOOR = register("powdery_door", Properties.ofFullCopy(Blocks.CRIMSON_DOOR).mapColor(MapColor.TERRACOTTA_GRAY), p -> new DoorBlock(BlockSetType.BAMBOO, p));
	public static final Block POWDERY_TRAPDOOR = register("powdery_trapdoor", Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).mapColor(MapColor.TERRACOTTA_GRAY), p -> new TrapDoorBlock(BlockSetType.BAMBOO, p));
	public static final Block POWDERY_BUTTON = register("powdery_button", Properties.ofFullCopy(POWDERY_PLANKS).noCollission().strength(0.5F).mapColor(MapColor.TERRACOTTA_GRAY), p -> new ButtonBlock(BlockSetType.BAMBOO, 30, p));
	public static final Block POWDERY_PRESSURE_PLATE = register("powdery_pressure_plate", Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE).mapColor(MapColor.TERRACOTTA_GRAY), p -> new PressurePlateBlock(BlockSetType.BAMBOO, p));
	public static final Block POWDERY_SIGN = register("powdery_sign", Properties.ofFullCopy(Blocks.CRIMSON_SIGN), p -> new MNDStandingSignBlock(p, MNDWoodTypes.POWDERY));
	public static final Block POWDERY_WALL_SIGN = register("powdery_wall_sign", dropsLike(Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN), POWDERY_SIGN), p -> new MNDWallSignBlock(p, MNDWoodTypes.POWDERY));
	public static final Block POWDERY_HANGING_SIGN = register("powdery_hanging_sign", Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN), p -> new MNDHangingSignBlock(p, MNDWoodTypes.POWDERY));
	public static final Block POWDERY_WALL_HANGING_SIGN = register("powdery_wall_hanging_sign", dropsLike(Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN), POWDERY_HANGING_SIGN), p -> new MNDWallHangingSignBlock(p, MNDWoodTypes.POWDERY));
	public static final Block STRIDERLOAF_BLOCK = register("striderloaf_block", Properties.ofFullCopy(Blocks.CAKE), p -> new StriderloafBlock(p, () -> MNDItems.STRIDERLOAF, true));
	public static final Block COLD_STRIDERLOAF_BLOCK = register("cold_striderloaf_block", Properties.ofFullCopy(Blocks.CAKE), p -> new StriderloafBlock(p, () -> MNDItems.COLD_STRIDERLOAF, true));
	public static final Block GHASTA_WITH_CREAM_BLOCK = register("ghasta_with_cream_block", Properties.ofFullCopy(Blocks.CAKE).lightLevel((light) -> 3), p -> new GhastaWithCreamBlock(p, () -> MNDItems.GHASTA_WITH_CREAM, true));
	public static final Block BREAD_LOAF_BLOCK = register("bread_loaf_block", Properties.ofFullCopy(Blocks.CAKE), p -> new BreadLoafBlock(p, () -> MNDItems.SLICES_OF_BREAD));
	public static final Block MAGMA_CAKE_BLOCK = register("magma_cake_block", Properties.ofFullCopy(Blocks.CAKE).lightLevel((light) -> 3), p -> new MagmaCakeBlock(p, () -> MNDItems.MAGMA_CAKE_SLICE));
	public static final Block STUFFED_HOGLIN = register("stuffed_hoglin", Properties.ofFullCopy(Blocks.CAKE).mapColor(MapColor.TERRACOTTA_PINK).pushReaction(PushReaction.DESTROY), StuffedHoglinBlock::new);
	public static final Block GOLDEN_TROPHY = register("golden_trophy", Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).strength(3.0F, 6.0F).sound(SoundType.METAL), p -> new GoldenTrophyBlock(p, 1.2D, 0.57D));
	public static final Block HOGLIN_TROPHY = register("hoglin_trophy", Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.TERRACOTTA_PINK), p -> new TrophyBlock(p, 0.5D, 0.24D));
	public static final Block WAXED_HOGLIN_TROPHY = register("waxed_hoglin_trophy", Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.TERRACOTTA_PINK), p -> new TrophyBlock(p, 0.5D, 0.24D));
	public static final Block ZOGLIN_TROPHY = register("zoglin_trophy", Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.TERRACOTTA_GREEN), p -> new TrophyBlock(p, 0.7D, 0.33D));
	public static final Block SKOGLIN_TROPHY = register("skoglin_trophy", Properties.ofFullCopy(Blocks.BONE_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE), p -> new TrophyBlock(p, 0.3D, 0.14D));

	private static <B extends Block> B register(String name, Properties properties, Function<Properties, B> factory) {
		ResourceLocation id = MyNethersDelight.id(name);
		//? if >=1.21.11 {
		/*ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
		B block = factory.apply(properties.setId(key));
		Registry.register(BuiltInRegistries.BLOCK, key, block);
		*///?} else {
		B block = factory.apply(properties);
		Registry.register(BuiltInRegistries.BLOCK, id, block);
		//?}
		return block;
	}

	private static Properties dropsLike(Properties properties, Block block) {
		//? if >=1.21.11 {
		//return properties.overrideLootTable(block.getLootTable());
		//?} else {
		return properties.dropsLike(block);
		//?}
	}

	private static ToIntFunction<BlockState> flameBlockEmission(int lightValue) {
		return (state) -> state.getValue(BlockStateProperties.MOISTURE) == 7 ? lightValue : 0;
	}

	private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
		return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
	}

	public static void initialize() {
	}
}
