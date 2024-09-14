package net.thefluffycart.litavis.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.custom.ScatterstoneBlock;

public class ModBlocks {
    public static final Block SCATTERSTONE = registerBlock("scatterstone",
            new ScatterstoneBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.DRIPSTONE_BLOCK)));
    public static final Block SCATTERSTONE_PILLAR = registerBlock("scatterstone_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).sounds(BlockSoundGroup.DRIPSTONE_BLOCK)));

//test
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Litavis.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(Litavis.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Litavis.LOGGER.info("Registering ModBlocks for " + Litavis.MOD_ID);
    }
}