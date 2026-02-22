package net.thefluffycart.litavis.block;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.enums.VaultState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.custom.*;
import net.thefluffycart.litavis.world.tree.ModSaplingGenerators;

public class ModBlocks {
    //TRIPSLATE BLOCKS
    public static final Block TRIPSLATE = registerBlock("tripslate",
            new TripslateBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block TRIPSLATE_BRICKS = registerBlock("tripslate_bricks",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block POLISHED_TRIPSLATE = registerBlock("polished_tripslate",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_TRIPSLATE_BRICKS = registerBlock("mossy_tripslate_bricks",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_TRIPSLATE_BRICKS = registerBlock("cracked_tripslate_bricks",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CHISELED_TRIPSLATE = registerBlock("chiseled_tripslate",
            new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CALIBRATED_TRIPSLATE = registerBlock("calibrated_tripslate",
            new CalibratedTripslateBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f)));
    public static final Block TRIPSLATE_BRICK_STAIRS = registerBlock("tripslate_brick_stairs",
            new StairsBlock(ModBlocks.TRIPSLATE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block TRIPSLATE_BRICK_SLAB = registerBlock("tripslate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block TRIPSLATE_BRICK_WALL = registerBlock("tripslate_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));

    public static final Block CRACKED_TRIPSLATE_BRICK_STAIRS = registerBlock("cracked_tripslate_brick_stairs",
            new StairsBlock(ModBlocks.TRIPSLATE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_TRIPSLATE_BRICK_SLAB = registerBlock("cracked_tripslate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block CRACKED_TRIPSLATE_BRICK_WALL = registerBlock("cracked_tripslate_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));

    public static final Block MOSSY_TRIPSLATE_BRICK_STAIRS = registerBlock("mossy_tripslate_brick_stairs",
            new StairsBlock(ModBlocks.TRIPSLATE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_TRIPSLATE_BRICK_SLAB = registerBlock("mossy_tripslate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block MOSSY_TRIPSLATE_BRICK_WALL = registerBlock("mossy_tripslate_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.MUD_BRICKS)));

    //EUCALYPTUS BLOCKS
    public static final Block EUCALYPTUS_LOG = registerBlock("eucalyptus_log",
            new BurningLogBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block EUCALYPTUS_WOOD = registerBlock("eucalyptus_wood",
            new BurningLogBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_EUCALYPTUS_LOG = registerBlock("stripped_eucalyptus_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_EUCALYPTUS_WOOD = registerBlock("stripped_eucalyptus_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block EUCALYPTUS_PLANKS = registerBlock("eucalyptus_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block EUCALYPTUS_STAIRS = registerBlock("eucalyptus_stairs",
            new StairsBlock(ModBlocks.EUCALYPTUS_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.ACACIA_STAIRS)));
    public static final Block EUCALYPTUS_SLAB = registerBlock("eucalyptus_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB)));

    public static final Block EUCALYPTUS_FENCE = registerBlock("eucalyptus_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_FENCE)));
    public static final Block EUCALYPTUS_FENCE_GATE = registerBlock("eucalyptus_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.copy(Blocks.ACACIA_FENCE_GATE)));

    public static final Block EUCALYPTUS_PRESSURE_PLATE = registerBlock("eucalyptus_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, AbstractBlock.Settings.copy(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final Block EUCALYPTUS_BUTTON = registerBlock("eucalyptus_button",
            new ButtonBlock(BlockSetType.ACACIA, 10, AbstractBlock.Settings.copy(Blocks.ACACIA_BUTTON)));

    //EUCALYPTUS DOORS
    public static final Block EUCALYPTUS_DOOR = registerBlock("eucalyptus_door",
            new DoorBlock(BlockSetType.DARK_OAK, AbstractBlock.Settings.copy(Blocks.ACACIA_DOOR).nonOpaque()));
    public static final Block EUCALYPTUS_TRAPDOOR = registerBlock("eucalyptus_trapdoor",
            new TrapdoorBlock(BlockSetType.DARK_OAK, AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR).nonOpaque()));

    //EUCALYPTUS TREE BLOCKS
    public static final Block EUCALYPTUS_LEAVES = registerBlock("eucalyptus_leaves",
            new OilyLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block EUCALYPTUS_SAPLING = registerBlock("eucalyptus_sapling",
            new SaplingBlock(ModSaplingGenerators.EUCALYPTUS,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block EUCALYPTUS_SIGN = registerBlock("eucalyptus_sign",
            new SignBlock(WoodType.ACACIA, AbstractBlock.Settings.create().mapColor(MapColor.DARK_CRIMSON).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f)));


    //EUCALYPTUS SIGNS

    public static final Identifier EUCALYPTUS_SIGN_TEXTURE = Identifier.of(Litavis.MOD_ID, "entity/signs/eucalyptus_sign");
    public static final Identifier EUCALYPTUS_HANGING_SIGN_TEXTURE = Identifier.of(Litavis.MOD_ID, "entity/signs/hanging/hanging_eucalyptus_sign");
    public static final Identifier EUCALYPTUS_HANGING_GUI_SIGN_TEXTURE = Identifier.of(Litavis.MOD_ID, "textures/gui/hanging_signs/eucalyptus_sign");

    public static final Block STANDING_EUCALYPTUS_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Litavis.MOD_ID, "eucalyptus_standing_sign"),
            new TerraformSignBlock(EUCALYPTUS_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));

    public static final Block WALL_EUCALYPTUS_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Litavis.MOD_ID, "eucalyptus_wall_sign"),
            new TerraformWallSignBlock(EUCALYPTUS_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).dropsLike(STANDING_EUCALYPTUS_SIGN)));

    public static final Block HANGING_EUCALYPTUS_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Litavis.MOD_ID, "eucalyptus_hanging_sign"),
            new TerraformHangingSignBlock(EUCALYPTUS_HANGING_SIGN_TEXTURE, EUCALYPTUS_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));

    public static final Block WALL_HANGING_EUCALYPTUS_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Litavis.MOD_ID, "eucalyptus_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(EUCALYPTUS_HANGING_SIGN_TEXTURE, EUCALYPTUS_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(HANGING_EUCALYPTUS_SIGN)));

    public static final BlockFamily EUCALYPTUS_FAMILY = BlockFamilies.register(ModBlocks.EUCALYPTUS_PLANKS)
            .sign(ModBlocks.STANDING_EUCALYPTUS_SIGN, ModBlocks.WALL_EUCALYPTUS_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block CRISPEN_LOG = registerBlock("crispen_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block CRISPEN_WOOD = registerBlock("crispen_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_CRISPEN_LOG = registerBlock("stripped_crispen_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_CRISPEN_WOOD = registerBlock("stripped_crispen_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block CRISPEN_PLANKS = registerBlock("crispen_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CRISPEN_STAIRS = registerBlock("crispen_stairs",
            new StairsBlock(ModBlocks.CRISPEN_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.ACACIA_STAIRS)));
    public static final Block CRISPEN_SLAB = registerBlock("crispen_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB)));

    public static final Block CRISPEN_FENCE = registerBlock("crispen_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_FENCE)));
    public static final Block CRISPEN_FENCE_GATE = registerBlock("crispen_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.copy(Blocks.ACACIA_FENCE_GATE)));

    public static final Block CRISPEN_PRESSURE_PLATE = registerBlock("crispen_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, AbstractBlock.Settings.copy(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final Block CRISPEN_BUTTON = registerBlock("crispen_button",
            new ButtonBlock(BlockSetType.ACACIA, 10, AbstractBlock.Settings.copy(Blocks.ACACIA_BUTTON)));

    //Granite Variants
    public static final Block GRANITE_PILLAR = registerBlock("granite_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block GRANITE_BRICKS = registerBlock("granite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block GRANITE_BRICK_STAIRS = registerBlock("granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.GRANITE)));
    public static final Block GRANITE_BRICK_SLAB = registerBlock("granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
    public static final Block GRANITE_BRICK_WALL = registerBlock("granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
    public static final Block CHISELED_GRANITE = registerBlock("chiseled_granite",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

    public static final Block POWDER_KEG = registerBlock("powder_keg",
            new PowderKegBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.SPRUCE_BROWN).strength(0.25f, 1.0f)));

    //Cracked Granite Variants
    public static final Block CRACKED_GRANITE_PILLAR = registerBlock("cracked_granite_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICKS = registerBlock("cracked_granite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICK_STAIRS = registerBlock("cracked_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICK_SLAB = registerBlock("cracked_granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_GRANITE_BRICK_WALL = registerBlock("cracked_granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));

    //Mossy Granite Variants
    public static final Block MOSSY_GRANITE_PILLAR = registerBlock("mossy_granite_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICKS = registerBlock("mossy_granite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICK_STAIRS = registerBlock("mossy_granite_brick_stairs",
            new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICK_SLAB = registerBlock("mossy_granite_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));
    public static final Block MOSSY_GRANITE_BRICK_WALL = registerBlock("mossy_granite_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.STONE)));

    public static final Block TIRIM_BERRY_BUSH = registerBlockWithoutBlockItem("tirim_berry_bush",
            new TirimBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).luminance(TirimBerryBushBlock.getLuminanceSupplier(5))));

    public static final Block POTTED_EUCALYPTUS_SAPLING = registerBlockWithoutBlockItem("potted_eucalyptus_sapling",
            new FlowerPotBlock(EUCALYPTUS_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING)));

    public static final Block BURROW_HEAD = registerBlock("burrow_head", new BurrowSkullBlock(AbstractBlock.Settings.create().strength(1.0F).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SAFETY_ROPE = registerBlockWithoutBlockItem("safety_rope", new SafetyRopeBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).noCollision().breakInstantly().strength(0.5F).sounds(BlockSoundGroup.WOOL).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Litavis.MOD_ID, name), block);
    }

    public static Block registerBlock(String name, Block block) {
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