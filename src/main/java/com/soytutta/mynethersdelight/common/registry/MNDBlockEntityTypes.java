package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.block.entity.BlazierBlockEntity;
import com.soytutta.mynethersdelight.common.block.entity.MNDHangingSignBlockEntity;
import com.soytutta.mynethersdelight.common.block.entity.MNDSignBlockEntity;
//? if <1.21.11 {
import com.soytutta.mynethersdelight.common.block.entity.NetherStoveBlockEntity;
//?}
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class MNDBlockEntityTypes {

	public static final BlockEntityType<MNDSignBlockEntity> MND_SIGN = register("mnd_sign", FabricBlockEntityTypeBuilder.create(MNDSignBlockEntity::new, MNDBlocks.POWDERY_SIGN, MNDBlocks.POWDERY_WALL_SIGN).build());
	public static final BlockEntityType<MNDHangingSignBlockEntity> MND_HSIGN = register("mnd_hsign", FabricBlockEntityTypeBuilder.create(MNDHangingSignBlockEntity::new, MNDBlocks.POWDERY_HANGING_SIGN, MNDBlocks.POWDERY_WALL_HANGING_SIGN).build());
	//? if <1.21.11 {
	public static final BlockEntityType<NetherStoveBlockEntity> NETHER_STOVE = register("nether_stove", FabricBlockEntityTypeBuilder.create(NetherStoveBlockEntity::new, MNDBlocks.NETHER_STOVE).build());
	//?}

	private static <T extends BlockEntityType<?>> T register(String name, T type) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MyNethersDelight.id(name), type);
	}

	public static void addCabinetsBlockEntities() {
		FabricBlockEntityType cabinet = ModBlockEntityTypes.CABINET.get();
		cabinet.addSupportedBlock(MNDBlocks.NETHER_BRICKS_CABINET);
		cabinet.addSupportedBlock(MNDBlocks.RED_NETHER_BRICKS_CABINET);
		cabinet.addSupportedBlock(MNDBlocks.BLACKSTONE_BRICKS_CABINET);
		cabinet.addSupportedBlock(MNDBlocks.POWDERY_CABINET);
	}

	public static void initialize() {
		addCabinetsBlockEntities();
		//? if >=1.21.11 {
		/*FabricBlockEntityType stove = ModBlockEntityTypes.STOVE.get();
		stove.addSupportedBlock(MNDBlocks.NETHER_STOVE);
		*///?}
	}

	public static final BlockEntityType<BlazierBlockEntity> BLAZIER = register("blazier", FabricBlockEntityTypeBuilder.create(BlazierBlockEntity::new, MNDBlocks.BLAZIER_BLOCK).build());



}
