package net.thefluffycart.litavis;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.entity.LitavisBlockEntityType;
import net.thefluffycart.litavis.block.render.BurrowHeadModel;
import net.thefluffycart.litavis.block.render.BurrowHeadRenderer;
import net.thefluffycart.litavis.block.render.SusRedSandRenderer;
import net.thefluffycart.litavis.entity.ModBoats;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.client.*;

public class LitavisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SAFETY_ROPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TIRIM_BERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_EUCALYPTUS_SAPLING, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(LitavisBlockEntityType.BURROW_HEAD, BurrowHeadRenderer::new
        );

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BURROW, BurrowModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BURROW, BurrowRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.COPPER_GOLEM, CopperGolemModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.COPPER_GOLEM, CopperGolemRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PLATYPUS, PlatypusModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PLATYPUS, PlatypusRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.EARTH_CHARGE, EarthChargeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.EARTH_CHARGE, EarthChargeRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BURROW_HEAD, BurrowHeadModel::getTexturedModelData);

        TerraformBoatClientHelper.registerModelLayers(ModBoats.EUCALYPTUS_BOAT_ID, false);

    }
}
