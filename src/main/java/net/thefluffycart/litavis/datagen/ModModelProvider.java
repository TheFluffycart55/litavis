package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerRotatable(ModBlocks.SCATTERSTONE_PILLAR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SCATTERSTONE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BURROW_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASSAVA, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_CHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
    }
}
