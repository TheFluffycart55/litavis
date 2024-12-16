package net.thefluffycart.litavis;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.entity.ModBoats;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.client.*;

public class LitavisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //RENDER CUTOUT FOR DOOR, TRAPDOOR, AND SAPLING
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_SAPLING, RenderLayer.getCutout());

        //RENDER THE BOAT, EARTH CHARGE, AND BURROW
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BURROW, BurrowModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BURROW, BurrowRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.EARTH_CHARGE, EarthChargeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.EARTH_CHARGE, EarthChargeRenderer::new);

        TerraformBoatClientHelper.registerModelLayers(ModBoats.EUCALYPTUS_BOAT_ID, false);
    }
}
