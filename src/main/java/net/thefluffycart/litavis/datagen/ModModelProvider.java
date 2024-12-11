package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.entity.BrushableBlockEntity;
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
        BlockStateModelGenerator.BlockTexturePool tripslateBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TRIPSLATE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool mossyTripslateBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_TRIPSLATE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool crackedTripslateBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_TRIPSLATE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool eucalyptusTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.EUCALYPTUS_PLANKS);

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

        tripslateBrickTexturePool.stairs(ModBlocks.TRIPSLATE_BRICK_STAIRS);
        tripslateBrickTexturePool.slab(ModBlocks.TRIPSLATE_BRICK_SLAB);
        tripslateBrickTexturePool.wall(ModBlocks.TRIPSLATE_BRICK_WALL);

        crackedTripslateBrickTexturePool.stairs(ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS);
        crackedTripslateBrickTexturePool.slab(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB);
        crackedTripslateBrickTexturePool.wall(ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL);

        mossyTripslateBrickTexturePool.stairs(ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);
        mossyTripslateBrickTexturePool.slab(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);
        mossyTripslateBrickTexturePool.wall(ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);

        blockStateModelGenerator.registerLog(ModBlocks.EUCALYPTUS_LOG).log(ModBlocks.EUCALYPTUS_LOG).wood(ModBlocks.EUCALYPTUS_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_EUCALYPTUS_LOG).log(ModBlocks.STRIPPED_EUCALYPTUS_LOG).wood(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);

        blockStateModelGenerator.registerSingleton(ModBlocks.EUCALYPTUS_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.EUCALYPTUS_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        eucalyptusTexturePool.stairs(ModBlocks.EUCALYPTUS_STAIRS);
        eucalyptusTexturePool.slab(ModBlocks.EUCALYPTUS_SLAB);
        eucalyptusTexturePool.button(ModBlocks.EUCALYPTUS_BUTTON);
        eucalyptusTexturePool.pressurePlate(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
        eucalyptusTexturePool.fence(ModBlocks.EUCALYPTUS_FENCE);
        eucalyptusTexturePool.fenceGate(ModBlocks.EUCALYPTUS_FENCE_GATE);

        eucalyptusTexturePool.family(ModBlocks.EUCALYPTUS_FAMILY);

        blockStateModelGenerator.registerDoor(ModBlocks.EUCALYPTUS_DOOR);
        blockStateModelGenerator.registerOrientableTrapdoor(ModBlocks.EUCALYPTUS_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BURROW_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.EUCALYPTUS_SIGN, Models.GENERATED);
        itemModelGenerator.register(ModItems.HANGING_EUCALYPTUS_SIGN, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.EUCALYPTUS_SAPLING.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModItems.ENTOMBED_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_CHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURROW_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
