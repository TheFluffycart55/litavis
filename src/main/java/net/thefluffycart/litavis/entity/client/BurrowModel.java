package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import net.thefluffycart.litavis.entity.client.animation.BurrowAnimations;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;

public class BurrowModel extends SinglePartEntityModel<BurrowEntity>{
    private final ModelPart burrow;
    private final ModelPart body;
    private final ModelPart rods;
    private final ModelPart core;
    private final ModelPart peenits;
    private final ModelPart pebbles;
    public BurrowModel(ModelPart root) {
        this.burrow = root.getChild("burrow");
        this.body = burrow.getChild("body");
        this.rods = body.getChild("rods");
        this.core = body.getChild("core");
        this.peenits = body.getChild("peenits");
        this.pebbles = body.getChild("pebbles");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData burrow = modelPartData.addChild("burrow", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = burrow.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -20.0F, 0.0F));

        ModelPartData browL = head.addChild("browL", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, 0.0F, -4.1F));

        ModelPartData browR = head.addChild("browR", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-2.5F, 0.0F, -4.1F));

        ModelPartData grass = head.addChild("grass", ModelPartBuilder.create().uv(20, 20).cuboid(0.0F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData grass2_r1 = grass.addChild("grass2_r1", ModelPartBuilder.create().uv(20, 20).cuboid(0.0F, -8.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData body = burrow.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

        ModelPartData core = body.addChild("core", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

        ModelPartData peenits = body.addChild("peenits", ModelPartBuilder.create().uv(0, 24).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData rods = body.addChild("rods", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData rod1 = rods.addChild("rod1", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 5.0F, -0.3927F, 0.0F, 0.0F));

        ModelPartData rod2 = rods.addChild("rod2", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.6194F, -1.0F, -1.9134F, 2.7489F, 1.1781F, 3.1416F));

        ModelPartData rod3 = rods.addChild("rod3", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.6194F, -1.0F, -1.9134F, 2.7489F, -1.1781F, 3.1416F));

        ModelPartData pebbles = body.addChild("pebbles", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

        ModelPartData med1 = pebbles.addChild("med1", ModelPartBuilder.create().uv(0, 28).cuboid(-1.0F, -2.0F, -8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData med2 = pebbles.addChild("med2", ModelPartBuilder.create().uv(0, 28).cuboid(-8.0F, -2.0F, 2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData large1 = pebbles.addChild("large1", ModelPartBuilder.create().uv(8, 26).cuboid(5.0F, -1.0F, -4.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData large2 = pebbles.addChild("large2", ModelPartBuilder.create().uv(8, 26).cuboid(-1.0F, -4.0F, 6.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData small1 = pebbles.addChild("small1", ModelPartBuilder.create().uv(12, 24).cuboid(-6.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData small2 = pebbles.addChild("small2", ModelPartBuilder.create().uv(12, 24).cuboid(6.0F, -2.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(BurrowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.peenits.pitch = ageInTicks * 16.0F * 0.017453292F;
        this.core.pitch = ageInTicks * -32.0F * 0.017453292F;
        this.core.yaw = ageInTicks * 8.0F * 0.017453292F;
        this.peenits.yaw = ageInTicks * -16.0F * 0.017453292F;
        this.rods.yaw = ageInTicks * 16.0F * 0.017453292F;
        this.pebbles.yaw = -ageInTicks * 16.0F * 0.017453292F;
        this.updateAnimation(entity.idleAnimationState, BurrowAnimations.BURROW_IDLE, ageInTicks);
        this.updateAnimation(entity.shootingAnimationState, BurrowAnimations.BURROW_SHOOT, ageInTicks);
        this.updateAnimation(entity.burrowingAnimationState, BurrowAnimations.BURROW_BURROWING, ageInTicks);
        this.updateAnimation(entity.unburrowingAnimationState, BurrowAnimations.BURROW_UNBURROWING, ageInTicks);
        this.updateAnimation(entity.whileburrowingAnimationState, BurrowAnimations.BURROW_WHILEBURROWING, ageInTicks);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        burrow.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return burrow;
    }
}
