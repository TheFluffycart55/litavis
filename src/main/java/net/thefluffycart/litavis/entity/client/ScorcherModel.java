package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.thefluffycart.litavis.entity.client.animation.PlatypusAnimations;
import net.thefluffycart.litavis.entity.client.animation.ScorcherAnimations;
import net.thefluffycart.litavis.entity.custom.PlatypusEntity;
import net.thefluffycart.litavis.entity.custom.ScorcherEntity;

public class ScorcherModel extends SinglePartEntityModel<ScorcherEntity> {
    private final ModelPart scorcher;

    public ScorcherModel(ModelPart root) {
        this.scorcher = root.getChild("scorcher");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData scorcher = modelPartData.addChild("scorcher", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData leg_frontL = scorcher.addChild("leg_frontL", ModelPartBuilder.create().uv(32, 50).cuboid(-4.0F, -13.0F, -4.0F, 8.0F, 26.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(16.0F, -13.0F, -12.0F));

        ModelPartData leg_backL = scorcher.addChild("leg_backL", ModelPartBuilder.create().uv(0, 50).cuboid(-4.0F, -13.0F, -4.0F, 8.0F, 26.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(16.0F, -13.0F, 12.0F));

        ModelPartData leg_frontR = scorcher.addChild("leg_frontR", ModelPartBuilder.create().uv(32, 50).mirrored().cuboid(-4.0F, -13.0F, -4.0F, 8.0F, 26.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-16.0F, -13.0F, -12.0F));

        ModelPartData leg_backR = scorcher.addChild("leg_backR", ModelPartBuilder.create().uv(0, 50).mirrored().cuboid(-20.0F, -0.5F, 8.0F, 8.0F, 26.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -25.5F, 0.0F));

        ModelPartData head = scorcher.addChild("head", ModelPartBuilder.create().uv(0, 27).cuboid(-8.5F, -5.5F, -6.0F, 17.0F, 11.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -25.5F, 0.0F));

        ModelPartData body = scorcher.addChild("body", ModelPartBuilder.create().uv(64, 51).cuboid(5.5F, -8.5F, -11.5F, 4.0F, 16.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-14.5F, -8.5F, -2.5F, 24.0F, 16.0F, 11.0F, new Dilation(0.0F))
                .uv(64, 51).mirrored().cuboid(-14.5F, -8.5F, -11.5F, 4.0F, 16.0F, 9.0F, new Dilation(0.0F)).mirrored(false)
                .uv(58, 27).cuboid(-10.5F, -8.5F, -11.5F, 16.0F, 3.0F, 9.0F, new Dilation(0.0F))
                .uv(64, 39).cuboid(-10.5F, 4.5F, -11.5F, 16.0F, 3.0F, 9.0F, new Dilation(0.0F))
                .uv(70, 18).cuboid(-10.5F, -4.5F, -7.5F, 16.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, -11.5F, 1.5F));

        ModelPartData hatch = body.addChild("hatch", ModelPartBuilder.create().uv(70, 0).cuboid(-8.0F, 0.0F, -1.0F, 16.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -5.5F, -10.5F));

        ModelPartData eyes = body.addChild("eyes", ModelPartBuilder.create(), ModelTransform.pivot(-2.5F, 0.0F, -3.5F));

        ModelPartData eye_l = eyes.addChild("eye_l", ModelPartBuilder.create().uv(58, 39).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, 0.0F, 0.0F));

        ModelPartData eye_r = eyes.addChild("eye_r", ModelPartBuilder.create().uv(58, 39).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void setAngles(ScorcherEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(ScorcherAnimations.SCORCHER_WALK, limbSwing, limbSwingAmount, 1f, 1.5f);
        this.updateAnimation(entity.idleAnimationState, ScorcherAnimations.SCORCHER_IDLE, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        scorcher.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return scorcher;
    }
}