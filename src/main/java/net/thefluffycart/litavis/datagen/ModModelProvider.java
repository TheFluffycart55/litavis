package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.custom.TirimBerryBushBlock;
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
        BlockStateModelGenerator.BlockTexturePool tripslateBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TRIPSLATE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool mossyTripslateBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_TRIPSLATE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool crackedTripslateBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_TRIPSLATE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool eucalyptusTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.EUCALYPTUS_PLANKS);
        BlockStateModelGenerator.BlockTexturePool crispenTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRISPEN_PLANKS);

        graniteBrickTexturePool.stairs(ModBlocks.GRANITE_BRICK_STAIRS);
        graniteBrickTexturePool.slab(ModBlocks.GRANITE_BRICK_SLAB);
        graniteBrickTexturePool.wall(ModBlocks.GRANITE_BRICK_WALL);

        crackedGraniteBrickTexturePool.stairs(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
        crackedGraniteBrickTexturePool.slab(ModBlocks.CRACKED_GRANITE_BRICK_SLAB);
        crackedGraniteBrickTexturePool.wall(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

        mossyGraniteBrickTexturePool.stairs(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
        mossyGraniteBrickTexturePool.slab(ModBlocks.MOSSY_GRANITE_BRICK_SLAB);
        mossyGraniteBrickTexturePool.wall(ModBlocks.MOSSY_GRANITE_BRICK_WALL);


        tripslateBrickTexturePool.stairs(ModBlocks.TRIPSLATE_BRICK_STAIRS);
        tripslateBrickTexturePool.slab(ModBlocks.TRIPSLATE_BRICK_SLAB);
        tripslateBrickTexturePool.wall(ModBlocks.TRIPSLATE_BRICK_WALL);

        crackedTripslateBrickTexturePool.stairs(ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS);
        crackedTripslateBrickTexturePool.slab(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB);
        crackedTripslateBrickTexturePool.wall(ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL);

        mossyTripslateBrickTexturePool.stairs(ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);
        mossyTripslateBrickTexturePool.slab(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);
        mossyTripslateBrickTexturePool.wall(ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);

        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_EUCALYPTUS_LOG).log(ModBlocks.STRIPPED_EUCALYPTUS_LOG).wood(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.EUCALYPTUS_SAPLING, ModBlocks.POTTED_EUCALYPTUS_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        eucalyptusTexturePool.stairs(ModBlocks.EUCALYPTUS_STAIRS);
        eucalyptusTexturePool.slab(ModBlocks.EUCALYPTUS_SLAB);
        eucalyptusTexturePool.button(ModBlocks.EUCALYPTUS_BUTTON);
        eucalyptusTexturePool.pressurePlate(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
        eucalyptusTexturePool.fence(ModBlocks.EUCALYPTUS_FENCE);
        eucalyptusTexturePool.fenceGate(ModBlocks.EUCALYPTUS_FENCE_GATE);

        blockStateModelGenerator.registerLog(ModBlocks.CRISPEN_LOG).log(ModBlocks.CRISPEN_LOG).wood(ModBlocks.CRISPEN_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_CRISPEN_LOG).log(ModBlocks.STRIPPED_CRISPEN_LOG).wood(ModBlocks.STRIPPED_CRISPEN_WOOD);

        crispenTexturePool.stairs(ModBlocks.CRISPEN_STAIRS);
        crispenTexturePool.slab(ModBlocks.CRISPEN_SLAB);
        crispenTexturePool.button(ModBlocks.CRISPEN_BUTTON);
        crispenTexturePool.pressurePlate(ModBlocks.CRISPEN_PRESSURE_PLATE);
        crispenTexturePool.fence(ModBlocks.CRISPEN_FENCE);
        crispenTexturePool.fenceGate(ModBlocks.CRISPEN_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.EUCALYPTUS_DOOR);
        blockStateModelGenerator.registerOrientableTrapdoor(ModBlocks.EUCALYPTUS_TRAPDOOR);

        eucalyptusTexturePool.family(ModBlocks.EUCALYPTUS_FAMILY);


        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.TIRIM_BERRY_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED,
                TirimBerryBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BURIED_DISC_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RELIC_DISC_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ECHOES_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURROW_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TERRA_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMB_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(ModItems.HANGING_EUCALYPTUS_SIGN, Models.GENERATED);
        itemModelGenerator.register(ModItems.EUCALYPTUS_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.EUCALYPTUS_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARCHAIC_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_CHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIP_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EUCALYPTUS_OIL_VIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATYPUS_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURROW_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.PLATYPUS_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
