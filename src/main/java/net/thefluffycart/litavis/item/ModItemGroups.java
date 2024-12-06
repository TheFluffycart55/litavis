package net.thefluffycart.litavis.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup LITAVIS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Litavis.MOD_ID, "litavis"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.litavis"))
                    .icon(()-> new ItemStack(ModItems.EARTH_CHARGE)).entries((displayContext, entries) -> {

                        entries.add(ModItems.BURROW_SPAWN_EGG);
                        entries.add(ModItems.EARTH_CHARGE);
                        entries.add(ModItems.ENTOMBED_KEY);
                        entries.add(ModItems.BURROW_ROD);
                        entries.add(ModBlocks.GRAVEL_EXHAUST);
                        entries.add(ModBlocks.SCULPTED_CORE);
                        entries.add(ModItems.TERRAFORMER);
                        entries.add(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);

                        entries.add(ModBlocks.EUCALYPTUS_LOG);
                        entries.add(ModBlocks.EUCALYPTUS_WOOD);
                        entries.add(ModBlocks.EUCALYPTUS_STAIRS);
                        entries.add(ModBlocks.EUCALYPTUS_SLAB);
                        entries.add(ModBlocks.EUCALYPTUS_FENCE);
                        entries.add(ModBlocks.EUCALYPTUS_FENCE_GATE);
                        entries.add(ModBlocks.EUCALYPTUS_BUTTON);
                        entries.add(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
                        entries.add(ModBlocks.EUCALYPTUS_DOOR);
                        entries.add(ModBlocks.EUCALYPTUS_TRAPDOOR);
                        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
                        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
                        entries.add(ModBlocks.EUCALYPTUS_PLANKS);
                        entries.add(ModBlocks.EUCALYPTUS_SAPLING);
                        entries.add(ModBlocks.EUCALYPTUS_LEAVES);
                    }).build());

    public static final ItemGroup LITAVIS_GRANITE = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Litavis.MOD_ID, "litavis_granite"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.litavis_granite"))
                    .icon(()-> new ItemStack(ModBlocks.GRANITE_BRICKS)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.GRANITE_BRICKS);
                        entries.add(ModBlocks.GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.GRANITE_BRICK_WALL);
                        entries.add(ModBlocks.GRANITE_PILLAR);

                        entries.add(ModBlocks.CRACKED_GRANITE_PILLAR);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICKS);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

                        entries.add(ModBlocks.MOSSY_GRANITE_PILLAR);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICKS);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_WALL);


                        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR);
                        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS);
                        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

                        entries.add(ModBlocks.CHISELED_GRANITE);
                    }).build());

    public static final ItemGroup TRIPSLATES = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Litavis.MOD_ID, "tripslate_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.litavis_tripslates"))
                    .icon(()-> new ItemStack(ModBlocks.TRIPSLATE)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.TRIPSLATE);
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
                        entries.add(ModBlocks.CALIBRATED_TRIPSLATE);
                    }).build());
    public static void registerItemGroups() {
        Litavis.LOGGER.info("Registering Item Groups for " + Litavis.MOD_ID);
    }
}
