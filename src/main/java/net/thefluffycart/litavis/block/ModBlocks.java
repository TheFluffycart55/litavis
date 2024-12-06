package net.thefluffycart.litavis.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.custom.*;
import net.thefluffycart.litavis.world.tree.ModSaplingGenerators;

public class ModBlocks {

    public static final Block TRIPSLATE = registerBlock("tripslate",
            new TripslateBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block TRIPSLATE_BRICKS = registerBlock("tripslate_bricks",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block POLISHED_TRIPSLATE = registerBlock("polished_tripslate",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block CRACKED_TRIPSLATE_BRICKS = registerBlock("mossy_tripslate_bricks",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block MOSSY_TRIPSLATE_BRICKS = registerBlock("cracked_tripslate_bricks",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block CHISELED_TRIPSLATE = registerBlock("chiseled_tripslate",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block CALIBRATED_TRIPSLATE = registerBlock("calibrated_tripslate",
            new CalibratedTripslateBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block TRIPSLATE_BRICK_STAIRS = registerBlock("tripslate_brick_stairs",
            new StairsBlock(ModBlocks.TRIPSLATE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block TRIPSLATE_BRICK_SLAB = registerBlock("tripslate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block TRIPSLATE_BRICK_WALL = registerBlock("tripslate_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block CRACKED_TRIPSLATE_BRICK_STAIRS = registerBlock("cracked_tripslate_brick_stairs",
            new StairsBlock(ModBlocks.TRIPSLATE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block CRACKED_TRIPSLATE_BRICK_SLAB = registerBlock("cracked_tripslate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block CRACKED_TRIPSLATE_BRICK_WALL = registerBlock("cracked_tripslate_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block MOSSY_TRIPSLATE_BRICK_STAIRS = registerBlock("mossy_tripslate_brick_stairs",
            new StairsBlock(ModBlocks.TRIPSLATE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block MOSSY_TRIPSLATE_BRICK_SLAB = registerBlock("mossy_tripslate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));
    public static final Block MOSSY_TRIPSLATE_BRICK_WALL = registerBlock("mossy_tripslate_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.PALE_PURPLE).requiresTool().strength(1.5f, 6.0f)));



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
    public static final Block EUCALYPTUS_STAIRS = registerBlock("eucalyptus_stairs",
            new StairsBlock(ModBlocks.EUCALYPTUS_PLANKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block EUCALYPTUS_SLAB = registerBlock("eucalyptus_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block EUCALYPTUS_FENCE = registerBlock("eucalyptus_fence",
            new FenceBlock(AbstractBlock.Settings.create()));
    public static final Block EUCALYPTUS_FENCE_GATE = registerBlock("eucalyptus_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create()));

    public static final Block EUCALYPTUS_PRESSURE_PLATE = registerBlock("eucalyptus_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create()));
    public static final Block EUCALYPTUS_BUTTON = registerBlock("eucalyptus_button",
            new ButtonBlock(BlockSetType.IRON, 10, AbstractBlock.Settings.create()));

    public static final Block EUCALYPTUS_DOOR = registerBlock("eucalyptus_door",
            new DoorBlock(BlockSetType.DARK_OAK, AbstractBlock.Settings.create().nonOpaque()));
    public static final Block EUCALYPTUS_TRAPDOOR = registerBlock("eucalyptus_trapdoor",
            new TrapdoorBlock(BlockSetType.DARK_OAK, AbstractBlock.Settings.create().nonOpaque()));



    public static final Block EUCALYPTUS_LEAVES = registerBlock("eucalyptus_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block EUCALYPTUS_SAPLING = registerBlock("eucalyptus_sapling",
            new SaplingBlock(ModSaplingGenerators.EUCALYPTUS,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    //Granite Variants
    public static final Block GRANITE_PILLAR = registerBlock("granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block GRANITE_BRICKS = registerBlock("granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block GRANITE_BRICK_STAIRS = registerBlock("granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block GRANITE_BRICK_SLAB = registerBlock("granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));
    public static final Block GRANITE_BRICK_WALL = registerBlock("granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE)));


    //Cracked Granite Variants
    public static final Block CRACKED_GRANITE_PILLAR = registerBlock("cracked_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_GRANITE_BRICKS = registerBlock("cracked_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_GRANITE_BRICK_STAIRS = registerBlock("cracked_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_GRANITE_BRICK_SLAB = registerBlock("cracked_granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_GRANITE_BRICK_WALL = registerBlock("cracked_granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

    //Mossy Granite Variants
    public static final Block MOSSY_GRANITE_PILLAR = registerBlock("mossy_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_GRANITE_BRICKS = registerBlock("mossy_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_GRANITE_BRICK_STAIRS = registerBlock("mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_GRANITE_BRICK_SLAB = registerBlock("mossy_granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_GRANITE_BRICK_WALL = registerBlock("mossy_granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

    //Cracked Mossy Granite Variants
    public static final Block CRACKED_MOSSY_GRANITE_PILLAR = registerBlock("cracked_mossy_granite_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICKS = registerBlock("cracked_mossy_granite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_STAIRS = registerBlock("cracked_mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_SLAB = registerBlock("cracked_mossy_granite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_MOSSY_GRANITE_BRICK_WALL = registerBlock("cracked_mossy_granite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

    public static final Block CHISELED_GRANITE = registerBlock("chiseled_granite",
            new Block(FabricBlockSettings.copyOf(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

    public static final Block GRAVEL_EXHAUST = registerBlock("gravel_exhaust",
            new GravelExhaustBlock(AbstractBlock.Settings.create().strength(3.0f, 6.0f).sounds(BlockSoundGroup.COPPER_GRATE).mapColor(MapColor.ORANGE).requiresTool().nonOpaque()));

    public static final Block SCULPTED_CORE = registerBlock("sculpted_core",
            new SculptedCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.SNARE).sounds(BlockSoundGroup.DECORATED_POT).strength(10.0F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));



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