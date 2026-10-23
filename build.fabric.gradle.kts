plugins {
	id("mod-platform")
	id("dev.kikugie.loom-back-compat")
}

stonecutter {
	replacements.string(current.parsed >= "1.21.11") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
		replace("net.minecraft.advancements.critereon", "net.minecraft.advancements.criterion")
		replace("net.minecraft.data.models", "net.minecraft.client.data.models")
		replace("net.minecraft.util.FastColor", "net.minecraft.util.ARGB")
		replace("FastColor.ARGB32", "ARGB")
		replace("net.minecraft.world.entity.animal.Sheep", "net.minecraft.world.entity.animal.sheep.Sheep")
		replace("net.minecraft.world.entity.animal.Wolf", "net.minecraft.world.entity.animal.wolf.Wolf")
		replace("net.minecraft.world.entity.animal.horse.AbstractHorse", "net.minecraft.world.entity.animal.equine.AbstractHorse")
		replace("net.minecraft.world.entity.monster.ZombieVillager", "net.minecraft.world.entity.monster.zombie.ZombieVillager")
		replace("net.minecraft.world.entity.npc.Villager", "net.minecraft.world.entity.npc.villager.Villager")
		replace("net.minecraft.world.entity.projectile.ThrowableItemProjectile", "net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile")
		replace("net.minecraft.world.entity.projectile.ThrownEgg", "net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEgg")
		replace("MobEffects.DAMAGE_RESISTANCE", "MobEffects.RESISTANCE")
		replace("MobEffects.CONFUSION", "MobEffects.NAUSEA")
		replace("MobEffects.MOVEMENT_SPEED", "MobEffects.SPEED")
		replace("net.minecraft.world.level.block.PinkPetalsBlock", "net.minecraft.world.level.block.FlowerBedBlock")
		replace("instanceof PinkPetalsBlock", "instanceof FlowerBedBlock")
		replace("instanceof TomatoBlock", "instanceof BuddingTomatoBlock")
		replace("vectorwing.farmersdelight.common.block.TomatoBlock", "vectorwing.farmersdelight.common.block.BuddingTomatoBlock")
		replace("vectorwing.farmersdelight.common.block.AbstractStoveBlock", "vectorwing.farmersdelight.common.block.StoveBlock")
		replace("extends AbstractStoveBlock", "extends StoveBlock")
		replace("extends AbstractStoveBlockEntity", "extends StoveBlockEntity")
		replace("vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity", "vectorwing.farmersdelight.common.block.entity.StoveBlockEntity")
		replace("vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab", "vectorwing.farmersdelight.common.crafting.CookingPotBookCategory")
		replace(".setRecipeBookTab(CookingPotRecipeBookTab.", ".setRecipeBookCategory(CookingPotBookCategory.")
		replace("Configuration.ENABLE_FOOD_EFFECT_TOOLTIP", "Configuration.FOOD_EFFECT_TOOLTIP")
		replace("Items.CHAIN", "Items.IRON_CHAIN")
		replace("BuiltInRegistries.BLOCK.getTag(", "BuiltInRegistries.BLOCK.get(")
		replace("ModBlocks.BAMBOO_BASKET", "ModBlocks.BASKET")
		replace(".getHolder(Enchantments.FIRE_PROTECTION)", ".get(Enchantments.FIRE_PROTECTION)")
		replace(".isClientSide", ".isClientSide()")
		replace("getMaxBuildHeight()", "getMaxY()")
		replace("this.tag(", "this.builder(")
		replace(".addTag(", ".addOptionalTag(")
		replace(".noCollission()", ".noCollision()")
		replace("SoundEvents.GENERIC_EAT,", "SoundEvents.GENERIC_EAT.value(),")
		replace("SoundEvents.HONEY_DRINK", "SoundEvents.HONEY_DRINK.value()")
		replace("net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider", "net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider")
	}
	replacements.string(current.parsed >= "1.21.11" && current.parsed < "26.1.2") {
		replace("DefaultStoveRenderer", "StoveRenderer")
	}
	replacements.string(current.parsed >= "26.1.2") {
		replace("FabricDataOutput", "FabricPackOutput")
		replace("net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry", "net.fabricmc.fabric.api.registry.LandPathTypeRegistry")
		replace("LandPathNodeTypesRegistry", "LandPathTypeRegistry")
		replace("net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup", "net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab")
		replace("net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider", "net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider")
		replace("FabricTagProvider.BlockTagProvider", "FabricTagsProvider.BlockTagsProvider")
		replace("FabricTagProvider.ItemTagProvider", "FabricTagsProvider.ItemTagsProvider")
		replace("PathType.DAMAGE_FIRE", "PathType.FIRE")
		replace("PathType.DAMAGE_OTHER", "PathType.DAMAGING")
		replace(".getType().is(", ".getType().builtInRegistryHolder().is(")
		replace("net.minecraft.client.renderer.blockentity.SignRenderer", "net.minecraft.client.renderer.blockentity.StandingSignRenderer")
		replace(" SignRenderer::new", " StandingSignRenderer::new")
		replace("SoundSet.CLASSIC).whineSound()", "SoundSet.CLASSIC).adultSounds().whineSound()")
		replace("SoundSet.CLASSIC).growlSound()", "SoundSet.CLASSIC).adultSounds().growlSound()")
		replace("FabricItemGroup", "FabricCreativeModeTab")
		replace("extends FarmBlock", "extends FarmlandBlock")
		replace("GuiGraphics guiGraphics", "GuiGraphicsExtractor guiGraphics")
		replace(".addSupportedBlock(", ".addValidBlock(")
		replace("CommonTags.TOOLS_KNIFE", "CommonTags.Items.TOOLS_KNIFE")
		replace("context.getBiomeRegistryEntry()", "context.getBiomeHolder()")
		replace("BlockTags.BAMBOO_PLANTABLE_ON", "BlockTags.SUPPORTS_BAMBOO")
		replace("BlockTags.MUSHROOM_GROW_BLOCK", "BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT")
		replace("newPool.conditionally(oldLootPool.conditions)", "newPool.when(oldLootPool.conditions)")
		replace("builder.conditionally(new AlwaysFalseCondition())", "builder.when(new AlwaysFalseCondition())")
		replace("newPool.with(container)", "newPool.add(container)")
		replace("net.minecraft.client.renderer.state.CameraRenderState", "net.minecraft.client.renderer.state.level.CameraRenderState")
		replace("net.minecraft.world.level.block.FarmBlock", "net.minecraft.world.level.block.FarmlandBlock")
		replace("net.minecraft.world.level.block.FungusBlock", "net.minecraft.world.level.block.NetherFungusBlock")
		replace("instanceof FungusBlock", "instanceof NetherFungusBlock")
		replace("net.minecraft.client.gui.GuiGraphics", "net.minecraft.client.gui.GuiGraphicsExtractor")
		replace("net.minecraft.client.renderer.block.model.VariantMutator", "net.minecraft.client.renderer.block.dispatch.VariantMutator")
		replace("net.minecraft.client.renderer.block.model.Variant", "net.minecraft.client.renderer.block.dispatch.Variant")
		replace(".soundGroup(", ".soundType(")
		replace(".hangingSignSoundGroup(", ".hangingSignSoundType(")
	}
	replacements.string(current.parsed >= "26.2") {
		replace("import net.minecraft.world.entity.EntityType;\n", "import net.minecraft.world.entity.EntityType;\nimport net.minecraft.world.entity.EntityTypes;\n")
		listOf(
			"ALLAY", "BAT", "BEE", "CAVE_SPIDER", "FROG", "GHAST", "HOGLIN", "HORSE",
			"MAGMA_CUBE", "PANDA", "PIGLIN", "PIGLIN_BRUTE", "SHEEP", "SKELETON",
			"SKELETON_HORSE", "SPIDER", "STRIDER", "VEX", "VILLAGER", "WITCH", "WOLF",
			"ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN"
		).forEach {
			replace("(EntityType.$it", "(EntityTypes.$it")
			replace(" EntityType.$it", " EntityTypes.$it")
		}
		replace("net.minecraft.world.entity.monster.MagmaCube", "net.minecraft.world.entity.monster.cubemob.MagmaCube")
		replace("net.minecraft.world.entity.monster.Slime", "net.minecraft.world.entity.monster.cubemob.Slime")
		replace("BlockTags.SAPLINGS", "BlockItemTags.SAPLINGS.block()")
		replace("ItemTags.SLABS", "BlockItemTags.SLABS.item()")
		replace("ItemTags.STAIRS", "BlockItemTags.STAIRS.item()")
	}
	replacements.string(current.parsed >= "26.2") {
		replace("import net.minecraft.world.entity.EntityType;\n", "import net.minecraft.world.entity.EntityType;\nimport net.minecraft.world.entity.EntityTypes;\n")
		listOf(
			"ALLAY", "BAT", "BEE", "CAVE_SPIDER", "FROG", "GHAST", "HOGLIN", "HORSE",
			"MAGMA_CUBE", "PANDA", "PIGLIN", "PIGLIN_BRUTE", "SHEEP", "SKELETON",
			"SKELETON_HORSE", "SPIDER", "STRIDER", "VEX", "VILLAGER", "WITCH", "WOLF",
			"ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN"
		).forEach {
			replace("(EntityType.$it", "(EntityTypes.$it")
			replace(" EntityType.$it", " EntityTypes.$it")
		}
		replace("net.minecraft.world.entity.monster.MagmaCube", "net.minecraft.world.entity.monster.cubemob.MagmaCube")
		replace("net.minecraft.world.entity.monster.Slime", "net.minecraft.world.entity.monster.cubemob.Slime")
		replace("BlockTags.SAPLINGS", "BlockItemTags.SAPLINGS.block()")
		replace("ItemTags.SLABS", "BlockItemTags.SLABS.item()")
		replace("ItemTags.STAIRS", "BlockItemTags.STAIRS.item()")
	}
}

platform {
	loader = "fabric"
	dependencies {
		required("minecraft") {
			fabricLikeVersionRange = prop("deps.minecraft")
		}
		required("fabric-api") {
			slug("fabric-api")
			fabricLikeVersionRange = ">=${prop("deps.fabric-api")}"
		}
		required("fabricloader") {
			fabricLikeVersionRange = ">=${prop("deps.fabric-loader")}"
		}
		required("farmersdelight") {
			slug("farmers-delight-refabricated")
			fabricLikeVersionRange = ">=${prop("deps.farmers-delight")}+refabricated"
		}
		optional("modmenu") {
			slug("modmenu")
		}
		optional("jei") {
			slug("jei")
		}
		optional("roughlyenoughitems") {
			slug("rei", "roughly-enough-items")
		}
		if (sc.current.parsed <= "1.21.1") {
			optional("emi") {
				slug("emi")
			}
		}
		if (sc.current.parsed >= "1.21.11") {
			optional("rrv") {
				slug("rrv")
			}
		}
	}
}

loom {
	accessWidenerPath = rootProject.file("src/main/resources/aw/${sc.current.version}.accesswidener")
	val devJvmArgs = propsList("mod", "dev_jvm_args") +
		propsList("mod", "dev_jvm_args_mixin_debug")
	runs.named("client") {
		client()
		generateRunConfig.set(true)
		runDirectory.set(layout.projectDirectory.dir("run"))
		programArguments.add("--username=Dev")
		jvmArguments.addAll(devJvmArgs)
		displayName.set("Fabric Client")
	}
	runs.named("server") {
		server()
		generateRunConfig.set(true)
		runDirectory.set(layout.projectDirectory.dir("run"))
		jvmArguments.addAll(devJvmArgs)
		displayName.set("Fabric Server")
	}
}

if (sc.active?.project == project.path) {
	sourceSets.main {
		java.exclude("**/generated/stonecutter/**")
	}
}

fabricApi {
	configureDataGeneration {
		outputDirectory = file("${rootDir}/versions/datagen/${sc.current.version}/src/main/generated")
		client = true
		modId = "mynethersdelight"
	}
}

repositories {
	mavenCentral()
	strictMaven("https://maven.terraformersmc.com/", "com.terraformersmc") { name = "TerraformersMC" }
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
	strictMaven("https://repo.sleeping.town/", "folk.sisby") { name = "Sleeping Town" }
	strictMaven("https://repo.greenhouse.house/releases/", "vectorwing") { name = "Greenhouse Maven" }
	strictMaven("https://maven.blamejared.com/", "mezz.jei", "net.mezzdev.config") { name = "BlameJared" }
	strictMaven("https://jitpack.io", "com.github.Chocohead") { name = "JitPack" }
}

configurations.all {
	resolutionStrategy {
		force("net.fabricmc:fabric-loader:${prop("deps.fabric-loader")}")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
	if (sc.current.parsed < "26") {
		mappings(loom.layered {
			officialMojangMappings()
			if (sc.properties.contains("deps.parchment"))
				parchment("org.parchmentmc.data:parchment-${prop("deps.parchment")}@zip")
		})
	}
	modImplementation("net.fabricmc:fabric-loader:${prop("deps.fabric-loader")}")
	// implementation(libs.moulberry.mixinconstraints)
	// include(libs.moulberry.mixinconstraints)
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("deps.fabric-api")}")
	modLocalRuntime("com.terraformersmc:modmenu:${prop("deps.modmenu")}")
	implementation("folk.sisby:kaleido-config:0.3.3+1.3.2")
	include("folk.sisby:kaleido-config:0.3.3+1.3.2")

	modImplementation("vectorwing:FarmersDelight:${prop("deps.farmers-delight")}+refabricated") {
		exclude(group = "net.fabricmc")
	}

	modCompileOnly("mezz.jei:jei-${prop("deps.minecraft")}-common-api:${prop("deps.jei")}")
	modCompileOnly("mezz.jei:jei-${prop("deps.minecraft")}-fabric-api:${prop("deps.jei")}")

	// modLocalRuntime("mezz.jei:jei-${prop("deps.minecraft")}-fabric:${prop("deps.jei")}")
}


afterEvaluate {
	tasks.register("printResDirs") {
		doLast {
			project.extensions.getByType<SourceSetContainer>().named("main").get().resources.srcDirs.forEach {
				println("RES: $it")
			}
		}
	}
}
