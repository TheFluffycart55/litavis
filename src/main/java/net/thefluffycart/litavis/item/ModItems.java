package net.thefluffycart.litavis.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.trim.ArmorTrimPatterns;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.item.custom.TerraformerItem;
import net.thefluffycart.litavis.item.custom.EarthChargeItem;
import net.thefluffycart.litavis.sound.ModSounds;

public class ModItems {
    public static final Item BURROW_ROD = registerItem("burrow_rod", new Item(new Item.Settings()));
    public static final Item ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("entombed_armor_trim_smithing_template", SmithingTemplateItem.of(Identifier.of("litavis", "entombed_armor_trim")));
    public static final Item MOLE_SPAWN_EGG = registerItem("mole_spawn_egg",
            new SpawnEggItem(ModEntities.MOLE, 0x59443c, 0xdbd895, new Item.Settings()));
    public static final Item BURROW_SPAWN_EGG = registerItem("burrow_spawn_egg",
            new SpawnEggItem(ModEntities.BURROW, 0xada07d, 0x4f4433, new Item.Settings()));

    public static final Item TERRAFORMER = registerItem("terraformer", new TerraformerItem(new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(5, 2.5f)).maxDamage(200)));

    public static final Item ECHOING_HALLS_MUSIC_DISC = registerItem("echoing_halls_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.ECHOING_HALLS_KEY)));
    public static final Item EARTH_CHARGE = registerItem("earth_charge",
            new EarthChargeItem(new Item.Settings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BURROW_ROD);
        entries.add(ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);
        entries.add(EARTH_CHARGE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Litavis.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Litavis.LOGGER.info("Registering Mod Items for " + Litavis.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
