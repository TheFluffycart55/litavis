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

                .add(ModBlocks.GRANITE_PILLAR, ModBlocks.CHISELED_GRANITE, ModBlocks.GRANITE_BRICKS,
                        ModBlocks.GRANITE_BRICK_STAIRS, ModBlocks.GRANITE_BRICK_SLAB, ModBlocks.GRANITE_BRICK_WALL)

                .add(ModBlocks.MOSSY_GRANITE_BRICKS, ModBlocks.MOSSY_GRANITE_BRICK_STAIRS,
                        ModBlocks.MOSSY_GRANITE_BRICK_SLAB, ModBlocks.MOSSY_GRANITE_BRICK_WALL, ModBlocks.MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_GRANITE_BRICKS, ModBlocks.CRACKED_GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_GRANITE_BRICK_SLAB,
                        ModBlocks.CRACKED_GRANITE_BRICK_WALL, ModBlocks.CRACKED_GRANITE_PILLAR)

                .add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS, ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB,
                        ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL, ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR)

                .add(ModBlocks.TRIPSLATE);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.GRANITE_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.MOSSY_GRANITE_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.CRACKED_GRANITE_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.EUCALYPTUS_LOG, ModBlocks.EUCALYPTUS_WOOD, ModBlocks.STRIPPED_EUCALYPTUS_LOG, ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
    }
}
