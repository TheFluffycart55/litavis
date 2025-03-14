package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.EUCALYPTUS_LOG.asItem(), ModBlocks.EUCALYPTUS_WOOD.asItem(),
                        ModBlocks.STRIPPED_EUCALYPTUS_LOG.asItem(), ModBlocks.STRIPPED_EUCALYPTUS_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.EUCALYPTUS_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(ModBlocks.EUCALYPTUS_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.EUCALYPTUS_TRAPDOOR.asItem());

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ModItems.EUCALYPTUS_SIGN);

        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ModItems.HANGING_EUCALYPTUS_SIGN);

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ModItems.EUCALYPTUS_BOAT);

        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.EUCALYPTUS_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.TERRAFORMER);
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModItems.TERRAFORMER);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);
    }
}