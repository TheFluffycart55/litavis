package net.thefluffycart.litavis.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.item.custom.CopperGripItem;
import net.thefluffycart.litavis.item.custom.EarthChargeItem;

public class ModItems {
    public static final Item BURROW_ROD = registerItem("burrow_rod", new Item(new Item.Settings()));
    public static final Item CASSAVA = registerItem("cassava", new Item(new Item.Settings().food(ModFoodComponents.CASSAVA)));
    public static final Item ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("entombed_armor_trim_smithing_template", new Item(new Item.Settings()));

    public static final Item COPPER_GRIP = registerItem("copper_grip", new CopperGripItem(new Item.Settings().maxCount(1).maxDamage(500)));

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
