package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.MoleEntity;

public class MoleRenderer extends MobEntityRenderer<MoleEntity, MoleModel>
{
    public MoleRenderer(EntityRendererFactory.Context context) {
        super(context, new MoleModel(context.getPart(ModEntityModelLayers.MOLE)), .3f);
    }

    @Override
    public Identifier getTexture(MoleEntity entity) {
        return Identifier.of(Litavis.MOD_ID, "textures/entity/mole.png");
    }

    @Override
    public void render(MoleEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby())
        {
            matrixStack.scale(0.75f, 0.75f, 0.75f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
