package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.thefluffycart.litavis.entity.client.animation.CacklewaryAnimations;
import net.thefluffycart.litavis.entity.client.animation.PlatypusAnimations;
import net.thefluffycart.litavis.entity.custom.CacklewaryEntity;
import net.thefluffycart.litavis.entity.custom.PlatypusEntity;

public class PlatypusModel extends SinglePartEntityModel<PlatypusEntity> {
    private final ModelPart platypus;

    public PlatypusModel(ModelPart root) {
        this.platypus = root.getChild("platypus");

    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData platypus = modelPartData.addChild("platypus", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 19.75F, -1.75F));

        ModelPartData head = platypus.addChild("head", ModelPartBuilder.create().uv(18, 27).cuboid(-1.5F, 0.5F, -8.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 24).cuboid(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.25F, -2.25F));

        ModelPartData fedora = head.addChild("fedora", ModelPartBuilder.create().uv(1, 1).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 17).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -2.0F));

        ModelPartData body = platypus.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.25F, 2.25F));

        ModelPartData leg_frontL = body.addChild("leg_frontL", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(0.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 1.5F, -2.5F));

        ModelPartData leg_backL = body.addChild("leg_backL", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(0.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 1.5F, 2.5F));

        ModelPartData leg_frontR = body.addChild("leg_frontR", ModelPartBuilder.create().uv(24, 0).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 1.5F, -2.5F));

        ModelPartData leg_backR = body.addChild("leg_backR", ModelPartBuilder.create().uv(24, 0).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 1.5F, 2.5F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 16).cuboid(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 4.5F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(PlatypusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(PlatypusAnimations.PLATYPUS_WALK, limbSwing, limbSwingAmount, 1f, 1.5f);
        this.updateAnimation(entity.idleAnimationState, PlatypusAnimations.PLATYPUS_IDLE, ageInTicks, 0.0125f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        platypus.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return platypus;
    }
}
