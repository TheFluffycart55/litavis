package net.thefluffycart.litavis.util;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Litavis.MOD_ID, name));
        }
        //UNUSED TAG TO MAKE TRIPSLATE VARIANTS FALL TOGETHER. NEEDS WORK DUE TO THE WAY THE WALLS, STAIRS, AND SLABS ARE IMPLEMENTED
        public static final TagKey<Block> TRIPSLATE_BRICK_VARIANTS =
                createTag("tripslate_brick_variants");
    }

    public static class Items {

    }
}