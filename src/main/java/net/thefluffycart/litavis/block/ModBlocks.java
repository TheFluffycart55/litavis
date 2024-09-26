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
import net.thefluffycart.litavis.block.custom.ModSaplingBlock;
import net.thefluffycart.litavis.block.custom.ScatterstoneBlock;
import net.thefluffycart.litavis.block.custom.TripslateBlock;
import net.thefluffycart.litavis.world.tree.ModSaplingGenerators;

public class ModBlocks {
    public static final Block TRIPSLATE = registerBlock("tripslate",
            new TripslateBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.DRIPSTONE_BLOCK)));

    public static final Block EUCALYPTUS_LOG = registerBlock("eucalyptus_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block EUCALYPTUS_WOOD = registerBlock("eucalyptus_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_EUCALYPTUS_LOG = registerBlock("stripped_eucalyptus_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_EUCALYPTUS_WOOD = registerBlock("stripped_eucalyptus_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));

    public static final Block EUCALYPTUS_PLANKS = registerBlock("eucalyptus_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block EUCALYPTUS_LEAVES = registerBlock("eucalyptus_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block EUCALYPTUS_SAPLING = registerBlock("eucalyptus_sapling",
            new ModSaplingBlock(ModSaplingGenerators.EUCALYPTUS,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), Blocks.RED_SAND));

    //Granite Variants
    public static final Block GRANITE_PILLAR = registerBlock("granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block GRANITE_BRICKS = registerBlock("granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block GRANITE_BRICK_STAIRS = registerBlock("granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block GRANITE_BRICK_SLAB = registerBlock("granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block GRANITE_BRICK_WALL = registerBlock("granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));


    //Cracked Granite Variants
    public static final Block CRACKED_GRANITE_PILLAR = registerBlock("cracked_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICKS = registerBlock("cracked_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICK_STAIRS = registerBlock("cracked_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block CRACKED_GRANITE_BRICK_SLAB = registerBlock("cracked_granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block CRACKED_GRANITE_BRICK_WALL = registerBlock("cracked_granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));

    //Mossy Granite Variants
    public static final Block MOSSY_GRANITE_PILLAR = registerBlock("mossy_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICKS = registerBlock("mossy_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICK_STAIRS = registerBlock("mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block MOSSY_GRANITE_BRICK_SLAB = registerBlock("mossy_granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block MOSSY_GRANITE_BRICK_WALL = registerBlock("mossy_granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));

    //Cracked Mossy Granite Variants
    public static final Block CRACKED_MOSSY_GRANITE_PILLAR = registerBlock("cracked_mossy_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICKS = registerBlock("cracked_mossy_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_STAIRS = registerBlock("cracked_mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_SLAB = registerBlock("cracked_mossy_granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_WALL = registerBlock("cracked_mossy_granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));

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