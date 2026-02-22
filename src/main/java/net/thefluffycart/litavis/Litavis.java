package net.thefluffycart.litavis;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.world.GameRules;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.ModCompatBlocks;
import net.thefluffycart.litavis.block.entity.LitavisBlockEntityType;
import net.thefluffycart.litavis.effect.ModEffects;
import net.thefluffycart.litavis.entity.ModBoats;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.custom.CacklewaryEntity;
import net.thefluffycart.litavis.entity.custom.CopperGolemEntity;
import net.thefluffycart.litavis.entity.custom.PlatypusEntity;
import net.thefluffycart.litavis.item.ModItemGroups;
import net.thefluffycart.litavis.item.ModItems;
import net.thefluffycart.litavis.potion.ModPotions;
import net.thefluffycart.litavis.sound.ModSounds;
import net.thefluffycart.litavis.world.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Litavis implements ModInitializer {

	public static final String MOD_ID = "litavis";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TIRIM_BERRIES, 0.25f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.EUCALYPTUS_LEAVES.asItem(), 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.EUCALYPTUS_SAPLING.asItem(), 0.35f);

		registerStrippables();

		ModSounds.registerSounds();
		ModEntities.registerModEntities();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBoats.registerBoats();
		ModTrunkPlacerTypes.register();
		ModCompatBlocks.register();
		LitavisBlockEntityType.register();
		ModEffects.registerEffects();
		ModPotions.registerPotions();

		DispenserBlock.registerBehavior(ModBlocks.POWDER_KEG, new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ModBlocks.SAFETY_ROPE, new BlockPlacementDispenserBehavior());

		FabricDefaultAttributeRegistry.register(ModEntities.BURROW, BurrowEntity.createburrowAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.COPPER_GOLEM, CopperGolemEntity.createCopperGolemAttributes());
//		FabricDefaultAttributeRegistry.register(ModEntities.CACKLEWARY, CacklewaryEntity.createDodoAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PLATYPUS, PlatypusEntity.createPlatypusAttribute());

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, ModItems.EUCALYPTUS_OIL_VIAL, ModPotions.IGNITION_POTION);
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(ModPotions.IGNITION_POTION, Items.REDSTONE, ModPotions.LONG_IGNITION_POTION);
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(ModPotions.IGNITION_POTION, Items.GLOWSTONE, ModPotions.STRONG_IGNITION_POTION);
		});
	}

	public static final GameRules.Key<GameRules.BooleanRule> EARTH_CHARGE_GRIEFING =
			GameRuleRegistry.register("earthChargeGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanRule> EARTH_CHARGE_RESTRICTED =
			GameRuleRegistry.register("earthChargeRestricted", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

	public static final GameRules.Key<GameRules.IntRule> EARTH_CHARGE_COOLDOWN =
			GameRuleRegistry.register("earthChargeCooldown", GameRules.Category.MISC, GameRuleFactory.createIntRule(300));

	public static final GameRules.Key<GameRules.IntRule> CAPSIZER_COOLDOWN =
			GameRuleRegistry.register("capsizerCooldown", GameRules.Category.MISC, GameRuleFactory.createIntRule(200));

	private static void registerStrippables(){
		StrippableBlockRegistry.register(ModBlocks.EUCALYPTUS_LOG, ModBlocks.STRIPPED_EUCALYPTUS_LOG);
		StrippableBlockRegistry.register(ModBlocks.EUCALYPTUS_WOOD, ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
	}
}


