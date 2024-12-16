package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;

public class BurrowRenderer extends MobEntityRenderer<BurrowEntity, BurrowModel>
{
    public BurrowRenderer(EntityRendererFactory.Context context) {
        super(context, new BurrowModel(context.getPart(ModEntityModelLayers.BURROW)), .3f);
    }

    @Override
    public Identifier getTexture(BurrowEntity entity) {
        return Identifier.of(Litavis.MOD_ID, "textures/entity/burrow.png");
    }

    @Override
    public void render(BurrowEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
