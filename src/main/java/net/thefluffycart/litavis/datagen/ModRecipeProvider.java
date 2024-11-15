package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
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
                , ModBlocks.TRIPSLATE);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRIPSLATE, 4)
                .pattern("TT")
                .pattern("TT")
                .input('T', ModBlocks.TRIPSLATE_BRICKS)
                .criterion(hasItem(ModBlocks.TRIPSLATE), conditionsFromItem(ModBlocks.TRIPSLATE))
                .offerTo(exporter, Identifier.of(Litavis.MOD_ID, "tripslate_bricks_crafting"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRIPSLATE, 4)
                .pattern("GM")
                .pattern("MG")
                .input('M', Blocks.MUD)
                .input('G', Blocks.GRAVEL)
                .criterion(hasItem(Blocks.GRAVEL), conditionsFromItem(Blocks.GRAVEL))
                .criterion(hasItem(Blocks.MUD), conditionsFromItem(Blocks.MUD))
                .offerTo(exporter, Identifier.of(Litavis.MOD_ID, "tripslate_crafting"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.TERRAFORMER, 1)
                .pattern("EGI")
                .input('E', ModItems.EARTH_CHARGE)
                .input('G', ModItems.GRANITE_TABLET)
                .input('I', Blocks.IRON_BLOCK)
                .criterion(hasItem(ModItems.EARTH_CHARGE), conditionsFromItem(ModItems.EARTH_CHARGE))
                .criterion(hasItem(ModItems.GRANITE_TABLET), conditionsFromItem(ModItems.GRANITE_TABLET))
                .offerTo(exporter, Identifier.of(Litavis.MOD_ID, "terraformer_crafting"));
    }
}
