package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.GRANITE_PILLAR, ModBlocks.GRANITE_BRICKS,
                        ModBlocks.GRANITE_BRICK_STAIRS, ModBlocks.GRANITE_BRICK_SLAB, ModBlocks.GRANITE_BRICK_WALL, ModBlocks.CHISELED_GRANITE)

                .add(ModBlocks.MOSSY_GRANITE_BRICKS, ModBlocks.MOSSY_GRANITE_BRICK_STAIRS,
                        ModBlocks.MOSSY_GRANITE_BRICK_SLAB, ModBlocks.MOSSY_GRANITE_BRICK_WALL, ModBlocks.MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_GRANITE_BRICKS, ModBlocks.CRACKED_GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_GRANITE_BRICK_SLAB,
                        ModBlocks.CRACKED_GRANITE_BRICK_WALL, ModBlocks.CRACKED_GRANITE_PILLAR)


                .add(ModBlocks.TRIPSLATE_BRICKS, ModBlocks.TRIPSLATE_BRICK_STAIRS, ModBlocks.TRIPSLATE_BRICK_SLAB,
                        ModBlocks.TRIPSLATE_BRICK_WALL)

                .add(ModBlocks.MOSSY_TRIPSLATE_BRICKS, ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS,
                        ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB, ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL)

                .add(ModBlocks.CRACKED_TRIPSLATE_BRICKS, ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS,
                        ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB, ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL)
                .add(ModBlocks.POLISHED_TRIPSLATE)

                .add(ModBlocks.TRIPSLATE);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_GRANITE_BRICK_STAIRS,
                        ModBlocks.MOSSY_GRANITE_BRICK_STAIRS,
                        ModBlocks.EUCALYPTUS_STAIRS, ModBlocks.TRIPSLATE_BRICK_STAIRS,
                        ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS, ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.GRANITE_BRICK_SLAB, ModBlocks.CRACKED_GRANITE_BRICK_SLAB,
                        ModBlocks.MOSSY_GRANITE_BRICK_SLAB,
                        ModBlocks.EUCALYPTUS_SLAB, ModBlocks.TRIPSLATE_BRICK_SLAB,
                        ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB, ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.GRANITE_BRICK_WALL, ModBlocks.MOSSY_GRANITE_BRICK_WALL,
                        ModBlocks.CRACKED_GRANITE_BRICK_WALL,
                        ModBlocks.TRIPSLATE_BRICK_WALL, ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL,
                        ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);

//        getOrCreateTagBuilder(BlockTags.SIGNS)
//                .add(ModBlocks.STANDING_CRISPEN_SIGN, ModBlocks.STANDING_EUCALYPTUS_SIGN);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.EUCALYPTUS_BUTTON);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.EUCALYPTUS_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.EUCALYPTUS_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.EUCALYPTUS_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCES).add(ModBlocks.EUCALYPTUS_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.EUCALYPTUS_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.MOSS_REPLACEABLE)
                .add(ModBlocks.TRIPSLATE);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.EUCALYPTUS_PLANKS);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.EUCALYPTUS_SAPLING);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.EUCALYPTUS_LEAVES);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.EUCALYPTUS_LOG, ModBlocks.EUCALYPTUS_WOOD, ModBlocks.STRIPPED_EUCALYPTUS_LOG, ModBlocks.STRIPPED_EUCALYPTUS_WOOD);

        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_LOGS)
                .add(ModBlocks.STRIPPED_EUCALYPTUS_LOG);

        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_WOODS)
                .add(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.EARTH_CHARGE_RESTRICTED)
                .add(ModBlocks.TRIPSLATE, ModBlocks.POLISHED_TRIPSLATE);

        getOrCreateTagBuilder(ModTags.Blocks.EARTH_CHARGE_THROWABLE)
                .add(Blocks.COAL_ORE, Blocks.COPPER_ORE, Blocks.IRON_ORE,
                        Blocks.GOLD_ORE, Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE,
                        Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.ANCIENT_DEBRIS)

                .add(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT_PATH, Blocks.ROOTED_DIRT,
                        Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK,
                        Blocks.SOUL_SAND, Blocks.SOUL_SOIL, Blocks.SCULK, Blocks.SUSPICIOUS_SAND, Blocks.SUSPICIOUS_GRAVEL)

                .add(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE,
                        Blocks.WAXED_COPPER_GRATE, Blocks.WAXED_EXPOSED_COPPER_GRATE, Blocks.WAXED_OXIDIZED_COPPER_GRATE, Blocks.WAXED_WEATHERED_COPPER_GRATE)

                .add(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT_PATH,
                        Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK,
                        Blocks.SAND, Blocks.GRAVEL, Blocks.RED_SAND)

                .add(Blocks.STONE, Blocks.COBBLESTONE, Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, Blocks.NETHERRACK,
                        Blocks.BLACKSTONE, Blocks.GILDED_BLACKSTONE, Blocks.SHROOMLIGHT, Blocks.DRIPSTONE_BLOCK, Blocks.TUFF, Blocks.END_STONE, Blocks.GLOWSTONE,
                        Blocks.CALCITE, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, ModBlocks.TRIPSLATE, Blocks.BONE_BLOCK, Blocks.AMETHYST_BLOCK, Blocks.AMETHYST_CLUSTER, Blocks.CLAY, Blocks.SNOW_BLOCK,
                        Blocks.BASALT, Blocks.SMOOTH_BASALT, Blocks.ICE)

                .add(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.BAMBOO, Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG,
                        Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.CHERRY_LOG,
                        Blocks.STRIPPED_CHERRY_LOG, Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM,
                        Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG,
                        Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, ModBlocks.EUCALYPTUS_LOG, ModBlocks.STRIPPED_EUCALYPTUS_LOG)

                .add(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD, Blocks.BAMBOO, Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD,
                        Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.CHERRY_WOOD,
                        Blocks.STRIPPED_CHERRY_WOOD, Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE,
                        Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD,
                        Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD, ModBlocks.EUCALYPTUS_WOOD, ModBlocks.STRIPPED_EUCALYPTUS_WOOD)

                .add(Blocks.TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA,Blocks.LIME_TERRACOTTA,Blocks.GREEN_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.BLUE_TERRACOTTA,
                        Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.PINK_TERRACOTTA,Blocks.WHITE_TERRACOTTA,Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.BROWN_TERRACOTTA)

                .add(Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE_IRON_ORE,
                        Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_REDSTONE_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
                        Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE);

        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(ModBlocks.SAFETY_ROPE);

        getOrCreateTagBuilder(ModTags.Blocks.PLATYPUS_SPAWNABLE)
                .add(Blocks.DIRT, Blocks.WATER, Blocks.GRAVEL);
    }
}
