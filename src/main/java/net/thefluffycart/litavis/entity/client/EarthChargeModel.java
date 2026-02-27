package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.thefluffycart.litavis.entity.custom.EarthChargeEntity;

public class EarthChargeModel extends SinglePartEntityModel<EarthChargeEntity>{
    private final ModelPart earth_charge;
    private final ModelPart pebbles;

    public EarthChargeModel(ModelPart root) {
        this.earth_charge = root.getChild("earth_charge");
        this.pebbles = earth_charge.getChild("pebbles");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData earth_charge = modelPartData.addChild("earth_charge", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

        ModelPartData cube_r1 = earth_charge.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        ModelPartData pebbles = earth_charge.addChild("pebbles", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        ModelPartData big1 = pebbles.addChild("big1", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -1.0F, 6.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData med1 = pebbles.addChild("med1", ModelPartBuilder.create().uv(12, 20).cuboid(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData med2 = pebbles.addChild("med2", ModelPartBuilder.create().uv(12, 16).cuboid(7.0F, -3.0F, -4.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData small2 = pebbles.addChild("small2", ModelPartBuilder.create().uv(20, 16).cuboid(7.0F, 2.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData small1 = pebbles.addChild("small1", ModelPartBuilder.create().uv(20, 18).cuboid(-8.0F, 2.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    public void setAngles(EarthChargeEntity earthChargeEntity, float f, float g, float h, float i, float j) {
        this.earth_charge.yaw = -h * 16.0F * 0.017453292F;
        this.pebbles.yaw = h * 16.0F * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        earth_charge.render(matrices, vertexConsumer, light, overlay, color);
        //pebbles.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return earth_charge;
    }
}
