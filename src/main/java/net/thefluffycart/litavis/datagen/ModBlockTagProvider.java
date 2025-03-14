package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
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
                .add(ModBlocks.GRANITE_PILLAR, ModBlocks.CHISELED_GRANITE, ModBlocks.GRANITE_BRICKS,
                        ModBlocks.GRANITE_BRICK_STAIRS, ModBlocks.GRANITE_BRICK_SLAB, ModBlocks.GRANITE_BRICK_WALL)

                .add(ModBlocks.MOSSY_GRANITE_BRICKS, ModBlocks.MOSSY_GRANITE_BRICK_STAIRS,
                        ModBlocks.MOSSY_GRANITE_BRICK_SLAB, ModBlocks.MOSSY_GRANITE_BRICK_WALL, ModBlocks.MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_GRANITE_BRICKS, ModBlocks.CRACKED_GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_GRANITE_BRICK_SLAB,
                        ModBlocks.CRACKED_GRANITE_BRICK_WALL, ModBlocks.CRACKED_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB,
                        ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL, ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.TRIPSLATE_BRICKS, ModBlocks.TRIPSLATE_BRICK_STAIRS, ModBlocks.TRIPSLATE_BRICK_SLAB,
                        ModBlocks.TRIPSLATE_BRICK_WALL)

                .add(ModBlocks.MOSSY_TRIPSLATE_BRICKS, ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS,
                        ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB, ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL)

                .add(ModBlocks.CRACKED_TRIPSLATE_BRICKS, ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS,
                        ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB, ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL)
                .add(ModBlocks.POLISHED_TRIPSLATE)

                .add(ModBlocks.TRIPSLATE)
                .add(ModBlocks.CALIBRATED_TRIPSLATE);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_GRANITE_BRICK_STAIRS,
                        ModBlocks.MOSSY_GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS,
                        ModBlocks.EUCALYPTUS_STAIRS, ModBlocks.TRIPSLATE_BRICK_STAIRS,
                        ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS, ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.GRANITE_BRICK_SLAB, ModBlocks.CRACKED_GRANITE_BRICK_SLAB,
                        ModBlocks.MOSSY_GRANITE_BRICK_SLAB, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB,
                        ModBlocks.EUCALYPTUS_SLAB, ModBlocks.TRIPSLATE_BRICK_SLAB,
                        ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB, ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.GRANITE_BRICK_WALL, ModBlocks.MOSSY_GRANITE_BRICK_WALL,
                        ModBlocks.CRACKED_GRANITE_BRICK_WALL, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL,
                        ModBlocks.TRIPSLATE_BRICK_WALL, ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL,
                        ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.SIGNS)
                .add(ModBlocks.EUCALYPTUS_SIGN);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.EUCALYPTUS_BUTTON);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.EUCALYPTUS_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.EUCALYPTUS_TRAPDOOR);

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
    }
}
