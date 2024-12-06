package net.thefluffycart.litavis.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.DragonFireballEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.WindChargeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.EarthChargeEntity;

@Environment(EnvType.CLIENT)
public class EarthChargeRenderer extends EntityRenderer<EarthChargeEntity> {
    private static final float field_52258 = MathHelper.square(3.5F);
    private static final Identifier TEXTURE = Identifier.of(Litavis.MOD_ID, "textures/entity/earth_charge.png");
    private final EarthChargeModel model;

    public EarthChargeRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new EarthChargeModel(context.getPart(ModEntityModelLayers.EARTH_CHARGE));
    }


    public void render(EarthChargeEntity earthChargeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (earthChargeEntity.age >= 2 || !(this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(earthChargeEntity) < (double)field_52258)) {
            float h = (float)earthChargeEntity.age + g;
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TEXTURE));
            matrixStack.push();
            matrixStack.translate(0.0, -1, 0.0);
            this.model.setAngles(earthChargeEntity, 0.0F, 0.0F, h, 0.0F, 0.0F);
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();
            super.render(earthChargeEntity, f, g, matrixStack, vertexConsumerProvider, i);
        }
    }

    protected float getXOffset(float tickDelta) {
        return tickDelta * 0.03F;
    }

    public Identifier getTexture(EarthChargeEntity earthChargeEntity) {
        return TEXTURE;
    }
}