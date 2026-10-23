package com.soytutta.mynethersdelight;

import com.soytutta.mynethersdelight.client.RenderLayers;
import com.soytutta.mynethersdelight.client.renderer.BlazeFireRenderer;
import com.soytutta.mynethersdelight.common.MNDConfiguration;
import com.soytutta.mynethersdelight.common.block.utility.MNDWoodTypes;
import com.soytutta.mynethersdelight.common.data.MNDDataMapTypes;
import com.soytutta.mynethersdelight.common.enchantment.PoachingFailureRegistry;
import com.soytutta.mynethersdelight.common.events.CommonEvent;
import com.soytutta.mynethersdelight.common.events.StriderFoodEvent;
import com.soytutta.mynethersdelight.common.registry.MNDBiomeFeatures;
import com.soytutta.mynethersdelight.common.registry.MNDBiomeModifiers;
import com.soytutta.mynethersdelight.common.registry.MNDBlockEntityTypes;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDCreativeTab;
import com.soytutta.mynethersdelight.common.registry.MNDEffects;
import com.soytutta.mynethersdelight.common.registry.MNDEnchantmentComponents;
import com.soytutta.mynethersdelight.common.registry.MNDEntityTypes;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.registry.MNDLootModifiers;
import com.soytutta.mynethersdelight.common.registry.MNDRecipeSerializers;
import com.soytutta.mynethersdelight.platform.Platform;
import com.soytutta.mynethersdelight.platform.fabric.FabricPlatform;
import com.soytutta.mynethersdelight.common.loot.MNDRefabricatedLootModificationEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
//? if >=26.1.2
//import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.block.DispenserBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vectorwing.farmersdelight.client.renderer.DefaultStoveRenderer;

import static folk.sisby.kaleido.api.WrappedConfig.createToml;

@SuppressWarnings("LoggingSimilarMessage")
public class MyNethersDelight {

	public static final String MOD_ID = /*$ mod_id*/"mynethersdelight";
	public static final String MOD_VERSION = /*$ mod_version*/"3.0.0-1.10.4.1";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/"My Nether's Delight Refabricated";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Platform PLATFORM = createPlatformInstance();
	public static final MNDConfiguration CONFIG = createToml(xplat().getConfigDir(), "", MOD_ID, MNDConfiguration.class);

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, MyNethersDelight.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);

		MNDItems.initialize();
		MNDBlocks.initialize();
		MNDEffects.initialize();
		MNDBlockEntityTypes.initialize();
		MNDEntityTypes.initialize();
		MNDCreativeTab.initialize();
		MNDBiomeFeatures.initialize();
		MNDLootModifiers.initialize();
		MNDRecipeSerializers.initialize();
		MNDEnchantmentComponents.initialize();
		MNDWoodTypes.initialize();
		MNDBiomeModifiers.initialize();

		MNDRefabricatedLootModificationEvents.initialize();

		MNDDataMapTypes.register();

		UseEntityCallback.EVENT.register(CommonEvent::onFrogMagmaCakeInteraction);
		UseEntityCallback.EVENT.register(StriderFoodEvent::onStriderFoodApplied);

		//? if >=26.1.2 {
		/*RecipeSynchronization.synchronizeRecipeSerializer(MNDRecipeSerializers.BLAZIER_HEATING);
		RecipeSynchronization.synchronizeRecipeSerializer(MNDRecipeSerializers.BLAZIER_COOLING);
		*///?}

		DispenserBlock.registerBehavior(MNDItems.STRIDER_ROCK, new ProjectileDispenseBehavior(MNDItems.STRIDER_ROCK));

		ServerLifecycleEvents.SERVER_STARTING.register(server -> {
			PoachingFailureRegistry.registerAll();
		});

		StrippableBlockRegistry.register(MNDBlocks.BLOCK_OF_POWDERY_CANNON, MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON);
		TillableBlockRegistry.register(MNDBlocks.RESURGENT_SOIL, HoeItem::onlyIfAirAbove,
				MNDBlocks.RESURGENT_SOIL_FARMLAND.defaultBlockState());
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, MyNethersDelight.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);

		EntityRendererRegistry.register(MNDEntityTypes.STRIDER_ROCK, ThrownItemRenderer::new);

		//? if <1.21.11 {
		BlockEntityRenderers.register(MNDBlockEntityTypes.NETHER_STOVE, DefaultStoveRenderer::new);
		//?}
		BlockEntityRenderers.register(MNDBlockEntityTypes.BLAZIER, BlazeFireRenderer::new);
		BlockEntityRenderers.register(MNDBlockEntityTypes.MND_SIGN, SignRenderer::new);
		BlockEntityRenderers.register(MNDBlockEntityTypes.MND_HSIGN, HangingSignRenderer::new);

		RenderLayers.cutout(MNDBlocks.POWDERY_DOOR);
		RenderLayers.cutout(MNDBlocks.POWDERY_TRAPDOOR);
		RenderLayers.cutout(MNDBlocks.CRIMSON_FUNGUS_COLONY);
		RenderLayers.cutout(MNDBlocks.WARPED_FUNGUS_COLONY);
		RenderLayers.cutout(MNDBlocks.POWDERY_CHUBBY_SAPLING);
		RenderLayers.cutout(MNDBlocks.POWDERY_CANNON);
		RenderLayers.cutout(MNDBlocks.POWDERY_CANE);
		RenderLayers.cutout(MNDBlocks.BLAZIER_BLOCK);
		RenderLayers.cutout(MNDBlocks.BULLET_PEPPER);
		RenderLayers.cutout(MNDBlocks.POTTED_BULLET_PEPPER);
		RenderLayers.cutout(MNDBlocks.POTTED_POWDERY_CANNON);
		RenderLayers.cutout(MNDBlocks.POWDERY_TORCH);
		RenderLayers.cutout(MNDBlocks.WALL_POWDERY_TORCH);
		RenderLayers.cutout(MNDBlocks.HOGLIN_TROPHY);
		RenderLayers.cutout(MNDBlocks.WAXED_HOGLIN_TROPHY);
		RenderLayers.cutout(MNDBlocks.STUFFED_HOGLIN);
		RenderLayers.cutout(MNDBlocks.STRIDERLOAF_BLOCK);
		RenderLayers.cutout(MNDBlocks.COLD_STRIDERLOAF_BLOCK);
		RenderLayers.cutout(MNDBlocks.GOLDEN_TROPHY);
	}

	public static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		return new FabricPlatform();
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public static ResourceLocation id(String namespace, String path) {
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
	}
}
