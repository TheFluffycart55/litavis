package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.thefluffycart.litavis.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TRIPSLATE);

        addDrop(ModBlocks.GRANITE_BRICKS);
        addDrop(ModBlocks.GRANITE_PILLAR);
        addDrop(ModBlocks.GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.GRANITE_BRICK_SLAB, slabDrops(ModBlocks.GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.GRANITE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_GRANITE);

        addDrop(ModBlocks.CRACKED_GRANITE_BRICKS);
        addDrop(ModBlocks.CRACKED_GRANITE_PILLAR);
        addDrop(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_GRANITE_BRICK_SLAB, slabDrops(ModBlocks.CRACKED_GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

        addDrop(ModBlocks.MOSSY_GRANITE_BRICKS);
        addDrop(ModBlocks.MOSSY_GRANITE_PILLAR);
        addDrop(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.MOSSY_GRANITE_BRICK_SLAB, slabDrops(ModBlocks.MOSSY_GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.MOSSY_GRANITE_BRICK_WALL);

        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS);
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR);
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB, slabDrops(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

        addDrop(ModBlocks.EUCALYPTUS_LOG);
        addDrop(ModBlocks.EUCALYPTUS_WOOD);
        addDrop(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
        addDrop(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
        addDrop(ModBlocks.EUCALYPTUS_PLANKS);
        addDrop(ModBlocks.EUCALYPTUS_DOOR, doorDrops(ModBlocks.EUCALYPTUS_DOOR));
        addDrop(ModBlocks.EUCALYPTUS_SAPLING);

        addDrop(ModBlocks.EUCALYPTUS_LEAVES, leavesDrops(ModBlocks.EUCALYPTUS_LEAVES, ModBlocks.EUCALYPTUS_SAPLING, 0.0625f));
    }
}
