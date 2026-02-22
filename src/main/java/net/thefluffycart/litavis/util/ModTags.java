package net.thefluffycart.litavis.util;

import net.minecraft.block.BellBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.thefluffycart.litavis.Litavis;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Litavis.MOD_ID, name));
        }
        public static final TagKey<Block> EARTH_CHARGE_THROWABLE =
                createTag("earth_charge_throwable");
        public static final TagKey<Block> EARTH_CHARGE_RESTRICTED =
                createTag("earth_charge_restricted");
        public static final TagKey<Block> PLATYPUS_SPAWNABLE =
                createTag("platypus_spawnable");
    }

    public static class Items {
        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Litavis.MOD_ID, name));
        }
        public static final TagKey<Item> EARTH_CHARGE_THROWABLE =
                createTag("earth_charge_throwable");
        public static final TagKey<Item> PLATYPUS_FOOD =
                createTag("platypus_food");

    }

    public static class Maps
    {
        private static TagKey<Structure> of(String id) {
            return TagKey.of(RegistryKeys.STRUCTURE, Identifier.ofVanilla(id));
        }
        TagKey<Structure> ON_ENTOMBED_RUINS_MAPS = of("on_entombed_ruins_maps");
    }
}