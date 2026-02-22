package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;
import net.thefluffycart.litavis.util.ModTags;

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

        getOrCreateTagBuilder(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.TERRA_POTTERY_SHERD)
                .add(ModItems.TOMB_POTTERY_SHERD);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.EUCALYPTUS_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(ModBlocks.EUCALYPTUS_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.EUCALYPTUS_TRAPDOOR.asItem());

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.TERRAFORMER);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModItems.TERRAFORMER);

        getOrCreateTagBuilder(ModTags.Items.PLATYPUS_FOOD)
                .add(Items.PUFFERFISH, Items.PUFFERFISH_BUCKET, Items.TROPICAL_FISH, Items.TROPICAL_FISH_BUCKET)
                .add(Items.SALMON, Items.SALMON_BUCKET, Items.COD, Items.COD_BUCKET);
    }
}