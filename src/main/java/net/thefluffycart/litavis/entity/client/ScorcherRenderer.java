package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.ScorcherEntity;

public class ScorcherRenderer extends MobEntityRenderer<ScorcherEntity, ScorcherModel> {

    private static final Identifier TEXTURE = Identifier.of(Litavis.MOD_ID, "textures/entity/scorcher.png");

    public ScorcherRenderer(EntityRendererFactory.Context context) {
        super(context, new ScorcherModel(context.getPart(ModEntityModelLayers.SCORCHER)), 1.5f);
    }

    @Override
    public Identifier getTexture(ScorcherEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ScorcherEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}