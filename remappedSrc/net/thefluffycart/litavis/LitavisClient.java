package net.thefluffycart.litavis;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.client.*;

public class LitavisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_TRAPDOOR, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MOLE, MoleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MOLE, MoleRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BURROW, BurrowModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BURROW, BurrowRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_SAPLING, RenderLayer.getCutout());
    }
}
