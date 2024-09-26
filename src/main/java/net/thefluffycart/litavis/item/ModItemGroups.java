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
                        entries.add(ModItems.EARTH_CHARGE);
                        entries.add(ModItems.BURROW_ROD);
                        entries.add(ModItems.TERRAFORMER);
                        entries.add(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModItems.MOLE_SPAWN_EGG);
                        entries.add(ModItems.ECHOING_HALLS_MUSIC_DISC);

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
                        entries.add(ModBlocks.TRIPSLATE);
                        entries.add(ModBlocks.EUCALYPTUS_LOG);
                        entries.add(ModBlocks.EUCALYPTUS_WOOD);
                        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
                        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
                        entries.add(ModBlocks.EUCALYPTUS_PLANKS);
                        entries.add(ModBlocks.EUCALYPTUS_SAPLING);
                    }).build());

    public static void registerItemGroups() {
        Litavis.LOGGER.info("Registering Item Groups for " + Litavis.MOD_ID);
    }
}
