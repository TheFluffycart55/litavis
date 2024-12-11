package net.thefluffycart.litavis.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.item.custom.TerraformerItem;
import net.thefluffycart.litavis.item.custom.EarthChargeItem;
import net.thefluffycart.litavis.sound.ModSounds;

public class ModItems {
    public static final Item BURROW_ROD = registerItem("burrow_rod", new Item(new Item.Settings()));
    public static final Item ENTOMBED_KEY = registerItem("entombed_key", new Item(new Item.Settings()));
    public static final Item ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("entombed_armor_trim_smithing_template", SmithingTemplateItem.of(Identifier.of("litavis", "entombed_armor_trim")));
    public static final Item BURROW_SPAWN_EGG = registerItem("burrow_spawn_egg",
            new SpawnEggItem(ModEntities.BURROW, 0xffc8a1, 0xa16133, new Item.Settings()));
    public static final Item TERRAFORMER = registerItem("terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200)));

    public static final Item EARTH_CHARGE = registerItem("earth_charge",
            new EarthChargeItem(new Item.Settings()));

    public static final Item EUCALYPTUS_SIGN = registerItem("chestnut_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.STANDING_EUCALYPTUS_SIGN, ModBlocks.WALL_EUCALYPTUS_SIGN));
    public static final Item HANGING_EUCALYPTUS_SIGN = registerItem("chestnut_hanging_sign",
            new HangingSignItem(ModBlocks.HANGING_EUCALYPTUS_SIGN, ModBlocks.WALL_HANGING_EUCALYPTUS_SIGN, new Item.Settings().maxCount(16)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BURROW_ROD);
        entries.add(ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);
        entries.add(EARTH_CHARGE);
    }

    private static void addItemsToBuildingBlocksGroup(FabricItemGroupEntries entries)
    {
        entries.add(ModBlocks.GRANITE_BRICKS);
        entries.add(ModBlocks.GRANITE_PILLAR);
        entries.add(ModBlocks.GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.GRANITE_BRICK_WALL);

        entries.add(ModBlocks.CRACKED_GRANITE_BRICKS);
        entries.add(ModBlocks.CRACKED_GRANITE_PILLAR);
        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

        entries.add(ModBlocks.MOSSY_GRANITE_BRICKS);
        entries.add(ModBlocks.MOSSY_GRANITE_PILLAR);
        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_WALL);

        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

        entries.add(ModBlocks.EUCALYPTUS_LOG);
        entries.add(ModBlocks.EUCALYPTUS_WOOD);
        entries.add(ModBlocks.EUCALYPTUS_STAIRS);
        entries.add(ModBlocks.EUCALYPTUS_SLAB);
        entries.add(ModBlocks.EUCALYPTUS_FENCE);
        entries.add(ModBlocks.EUCALYPTUS_FENCE_GATE);
        entries.add(ModBlocks.EUCALYPTUS_BUTTON);
        entries.add(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
        entries.add(ModBlocks.EUCALYPTUS_DOOR);
        entries.add(ModBlocks.EUCALYPTUS_TRAPDOOR);
        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
        entries.add(ModBlocks.EUCALYPTUS_PLANKS);
        entries.add(ModBlocks.EUCALYPTUS_SAPLING);
        entries.add(ModBlocks.EUCALYPTUS_LEAVES);

        entries.add(ModBlocks.TRIPSLATE);
        entries.add(ModBlocks.POLISHED_TRIPSLATE);
        entries.add(ModBlocks.TRIPSLATE_BRICKS);
        entries.add(ModBlocks.TRIPSLATE_BRICK_STAIRS);
        entries.add(ModBlocks.TRIPSLATE_BRICK_SLAB);
        entries.add(ModBlocks.TRIPSLATE_BRICK_WALL);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICKS);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICKS);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);
        entries.add(ModBlocks.CHISELED_TRIPSLATE);
        entries.add(ModBlocks.CALIBRATED_TRIPSLATE);

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Litavis.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Litavis.LOGGER.info("Registering Mod Items for " + Litavis.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingBlocksGroup);
    }
}
