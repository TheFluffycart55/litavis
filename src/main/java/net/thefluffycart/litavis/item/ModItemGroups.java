package net.thefluffycart.litavis.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.ModCompatBlocks;

public class ModItemGroups {
    public static final ItemGroup LITAVIS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Litavis.MOD_ID, "litavis"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.litavis"))
                    .icon(()-> new ItemStack(ModItems.ARCHAIC_KEY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.BURROW_SPAWN_EGG);
                        entries.add(ModItems.PLATYPUS_SPAWN_EGG);
                        entries.add(ModItems.PLATYPUS_BUCKET);
                        entries.add(ModItems.EARTH_CHARGE);
                        entries.add(ModItems.ARCHAIC_KEY);
                        entries.add(ModItems.BURROW_ROD);
                        entries.add(ModItems.TERRAFORMER);
                        entries.add(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModItems.DRIP_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModItems.TERRA_POTTERY_SHERD);
                        entries.add(ModItems.TOMB_POTTERY_SHERD);
                        entries.add(ModItems.BURIED_DISC_FRAGMENT);
                        entries.add(ModItems.RELIC_DISC_FRAGMENT);
                        entries.add(ModItems.ECHOES_MUSIC_DISC);
                        entries.add(ModBlocks.POWDER_KEG);
                        entries.add(ModItems.TIRIM_BERRIES);
                        entries.add(ModBlocks.EUCALYPTUS_SAPLING);
                        entries.add(ModItems.EUCALYPTUS_OIL_VIAL);
//                        entries.add(ModItems.EUCALYPTUS_BOAT);
//                        entries.add(ModItems.EUCALYPTUS_CHEST_BOAT);
                    }).build());

    public static final ItemGroup LITAVIS_GRANITE = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Litavis.MOD_ID, "litavis_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.litavis_blocks"))
                    .icon(()-> new ItemStack(ModBlocks.GRANITE_BRICKS)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.GRANITE_BRICKS);
                        entries.add(ModBlocks.GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.GRANITE_BRICK_WALL);
                        entries.add(ModBlocks.GRANITE_PILLAR);
                        entries.add(ModBlocks.CHISELED_GRANITE);

                        entries.add(ModBlocks.CRACKED_GRANITE_BRICKS);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_WALL);
                        entries.add(ModBlocks.CRACKED_GRANITE_PILLAR);

                        entries.add(ModBlocks.MOSSY_GRANITE_BRICKS);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_WALL);
                        entries.add(ModBlocks.MOSSY_GRANITE_PILLAR);

                        entries.add(ModBlocks.TRIPSLATE);
                        entries.add(ModBlocks.CALIBRATED_TRIPSLATE);
                        entries.add(ModBlocks.POLISHED_TRIPSLATE);
                        entries.add(ModBlocks.TRIPSLATE_BRICKS);
                        entries.add(ModBlocks.TRIPSLATE_BRICK_STAIRS);
                        entries.add(ModBlocks.TRIPSLATE_BRICK_SLAB);
                        entries.add(ModBlocks.TRIPSLATE_BRICK_WALL);

                        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICKS);
                        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS);
                        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB);
                        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL);

                        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICKS);
                        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);
                        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);
                        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);
                        entries.add(ModBlocks.CHISELED_TRIPSLATE);

                        entries.add(ModBlocks.EUCALYPTUS_LOG);
                        entries.add(ModBlocks.EUCALYPTUS_WOOD);
                        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
                        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
                        entries.add(ModBlocks.EUCALYPTUS_PLANKS);
                        entries.add(ModBlocks.EUCALYPTUS_STAIRS);
                        entries.add(ModBlocks.EUCALYPTUS_SLAB);
                        entries.add(ModBlocks.EUCALYPTUS_DOOR);
                        entries.add(ModBlocks.EUCALYPTUS_TRAPDOOR);
                        entries.add(ModBlocks.EUCALYPTUS_FENCE);
                        entries.add(ModBlocks.EUCALYPTUS_FENCE_GATE);
                        entries.add(ModBlocks.EUCALYPTUS_BUTTON);
                        entries.add(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
                        entries.add(ModBlocks.EUCALYPTUS_LEAVES);
//                        entries.add(ModItems.EUCALYPTUS_SIGN);
//                        entries.add(ModItems.HANGING_EUCALYPTUS_SIGN);
                        entries.add(ModBlocks.BURROW_HEAD);
                        entries.add(ModItems.SAFETY_ROPE);

                        entries.add(ModBlocks.CRISPEN_LOG);
                        entries.add(ModBlocks.CRISPEN_WOOD);
                        entries.add(ModBlocks.STRIPPED_CRISPEN_LOG);
                        entries.add(ModBlocks.STRIPPED_CRISPEN_WOOD);
                        entries.add(ModBlocks.CRISPEN_PLANKS);
                        entries.add(ModBlocks.CRISPEN_STAIRS);
                        entries.add(ModBlocks.CRISPEN_SLAB);
                        entries.add(ModBlocks.CRISPEN_FENCE);
                        entries.add(ModBlocks.CRISPEN_FENCE_GATE);
                        entries.add(ModBlocks.CRISPEN_BUTTON);
                        entries.add(ModBlocks.CRISPEN_PRESSURE_PLATE);

                        if (FabricLoader.getInstance().isModLoaded("zeldacraft"))
                        {
                            entries.add(ModCompatBlocks.secretCrackedGraniteBricks);
                            entries.add(ModCompatBlocks.secretCrackedMossyGraniteBricks);
                            entries.add(ModCompatBlocks.secretCrackedTripslateBricks);


                        }
                    }).build());
    public static void registerItemGroups() {
        Litavis.LOGGER.info("Registering Item Groups for " + Litavis.MOD_ID);
    }
}
