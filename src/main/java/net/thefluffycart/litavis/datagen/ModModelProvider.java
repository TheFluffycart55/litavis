package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool graniteBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GRANITE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool crackedGraniteBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_GRANITE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool mossyGraniteBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_GRANITE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool crackedMossyGraniteBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS);

        graniteBrickTexturePool.stairs(ModBlocks.GRANITE_BRICK_STAIRS);
        graniteBrickTexturePool.slab(ModBlocks.GRANITE_BRICK_SLAB);
        graniteBrickTexturePool.wall(ModBlocks.GRANITE_BRICK_WALL);

        crackedGraniteBrickTexturePool.stairs(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
        crackedGraniteBrickTexturePool.slab(ModBlocks.CRACKED_GRANITE_BRICK_SLAB);
        crackedGraniteBrickTexturePool.wall(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

        mossyGraniteBrickTexturePool.stairs(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
        mossyGraniteBrickTexturePool.slab(ModBlocks.MOSSY_GRANITE_BRICK_SLAB);
        mossyGraniteBrickTexturePool.wall(ModBlocks.MOSSY_GRANITE_BRICK_WALL);

        crackedMossyGraniteBrickTexturePool.stairs(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS);
        crackedMossyGraniteBrickTexturePool.slab(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB);
        crackedMossyGraniteBrickTexturePool.wall(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

        blockStateModelGenerator.registerLog(ModBlocks.EUCALYPTUS_LOG).log(ModBlocks.EUCALYPTUS_LOG).wood(ModBlocks.EUCALYPTUS_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_EUCALYPTUS_LOG).log(ModBlocks.STRIPPED_EUCALYPTUS_LOG).wood(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EUCALYPTUS_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.EUCALYPTUS_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.EUCALYPTUS_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BURROW_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_CHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ECHOING_HALLS_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOLE_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.BURROW_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
