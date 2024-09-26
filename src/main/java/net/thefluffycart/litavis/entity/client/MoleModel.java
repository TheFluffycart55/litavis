package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.thefluffycart.litavis.entity.client.animation.MoleAnimations;
import net.thefluffycart.litavis.entity.custom.MoleEntity;

public class MoleModel extends SinglePartEntityModel<MoleEntity> {
    private final ModelPart body;

    public MoleModel(ModelPart root) {
        this.body = root.getChild("body");

    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData paw_l1 = body.addChild("paw_l1", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -1.5F, -2.5F));

        ModelPartData cube_r1 = paw_l1.addChild("cube_r1", ModelPartBuilder.create().uv(41, 0).cuboid(-2.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r2 = paw_l1.addChild("cube_r2", ModelPartBuilder.create().uv(30, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, -2.5F, 0.0F, 1.5708F, 1.5708F));

        ModelPartData paw_l2 = body.addChild("paw_l2", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -1.5F, 3.5F));

        ModelPartData cube_r3 = paw_l2.addChild("cube_r3", ModelPartBuilder.create().uv(41, 0).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData paw_r1 = body.addChild("paw_r1", ModelPartBuilder.create().uv(41, 0).cuboid(-3.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -1.5F, -2.5F));

        ModelPartData cube_r4 = paw_r1.addChild("cube_r4", ModelPartBuilder.create().uv(1, 24).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, -2.5F, -1.5708F, -1.5708F, 0.0F));

        ModelPartData paw_r2 = body.addChild("paw_r2", ModelPartBuilder.create().uv(41, 0).cuboid(-2.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -1.5F, 3.5F));

        ModelPartData nose = body.addChild("nose", ModelPartBuilder.create().uv(1, 18).cuboid(-2.5F, -1.5F, -1.0F, 5.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.5F, -6.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(MoleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(MoleAnimations.MOLE_WALK, limbSwing, limbSwingAmount, 6f, 6.5f);
        this.updateAnimation(entity.idleAnimationState, MoleAnimations.MOLE_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.diggingAnimationState, MoleAnimations.MOLE_DIG, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }
}
