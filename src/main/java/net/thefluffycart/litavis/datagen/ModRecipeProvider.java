package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.BURROW_ROD, RecipeCategory.DECORATIONS
                , ModBlocks.SCATTERSTONE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE, 1)
                .pattern("STS")
                .pattern("SRS")
                .pattern("SSS")
                .input('T', ModItems.ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE)
                .input('R', ModBlocks.SCATTERSTONE)
                .input('S', Items.AMETHYST_SHARD)
                .criterion(hasItem(ModBlocks.SCATTERSTONE), conditionsFromItem(ModBlocks.SCATTERSTONE))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, Identifier.of(getRecipeName(Items.AMETHYST_SHARD)));
    }
}
