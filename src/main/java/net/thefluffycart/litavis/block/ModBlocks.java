package net.thefluffycart.litavis.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
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

    //Granite Variants
    public static final Block GRANITE_PILLAR = registerBlock("granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block GRANITE_BRICKS = registerBlock("granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block GRANITE_BRICK_STAIRS = registerBlock("granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create()));
    public static final Block GRANITE_BRICK_SLAB = registerBlock("granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create()));
    public static final Block GRANITE_BRICK_WALL = registerBlock("granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.create()));

    //Cracked Granite Variants
    public static final Block CRACKED_GRANITE_PILLAR = registerBlock("cracked_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICKS = registerBlock("cracked_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICK_STAIRS = registerBlock("cracked_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create()));
    public static final Block CRACKED_GRANITE_BRICK_SLAB = registerBlock("cracked_granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create()));
    public static final Block CRACKED_GRANITE_BRICK_WALL = registerBlock("cracked_granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.create()));

    //Mossy Granite Variants
    public static final Block MOSSY_GRANITE_PILLAR = registerBlock("mossy_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICKS = registerBlock("mossy_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICK_STAIRS = registerBlock("mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create()));
    public static final Block MOSSY_GRANITE_BRICK_SLAB = registerBlock("mossy_granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create()));
    public static final Block MOSSY_GRANITE_BRICK_WALL = registerBlock("mossy_granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.create()));

    //Cracked Mossy Granite Variants
    public static final Block CRACKED_MOSSY_GRANITE_PILLAR = registerBlock("cracked_mossy_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICKS = registerBlock("cracked_mossy_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_STAIRS = registerBlock("cracked_mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create()));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_SLAB = registerBlock("cracked_mossy_granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create()));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_WALL = registerBlock("cracked_mossy_granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.create()));

    public static final Block CHISELED_GRANITE = registerBlock("chiseled_granite",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));

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