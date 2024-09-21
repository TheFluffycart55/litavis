package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.thefluffycart.litavis.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)

                .add(ModBlocks.GRANITE_PILLAR)
                .add(ModBlocks.CHISELED_GRANITE)

                .add(ModBlocks.GRANITE_BRICKS)
                .add(ModBlocks.GRANITE_BRICK_STAIRS)
                .add(ModBlocks.GRANITE_BRICK_SLAB)
                .add(ModBlocks.GRANITE_BRICK_WALL)

                .add(ModBlocks.MOSSY_GRANITE_BRICKS)
                .add(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS)
                .add(ModBlocks.MOSSY_GRANITE_BRICK_SLAB)
                .add(ModBlocks.MOSSY_GRANITE_BRICK_WALL)
                .add(ModBlocks.MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_GRANITE_BRICKS)
                .add(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS)
                .add(ModBlocks.CRACKED_GRANITE_BRICK_SLAB)
                .add(ModBlocks.CRACKED_GRANITE_BRICK_WALL)
                .add(ModBlocks.CRACKED_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS)
                .add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS)
                .add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB)
                .add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL)
                .add(ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.SCATTERSTONE);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.GRANITE_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.MOSSY_GRANITE_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.CRACKED_GRANITE_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);
    }
}
