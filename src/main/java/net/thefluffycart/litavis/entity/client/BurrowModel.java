package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.thefluffycart.litavis.entity.client.animation.BurrowAnimations;
import net.thefluffycart.litavis.entity.client.animation.MoleAnimations;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.custom.MoleEntity;

public class BurrowModel extends SinglePartEntityModel<BurrowEntity>{
    private final ModelPart burrow;
    private final ModelPart head;

    public BurrowModel(ModelPart root) {
        this.burrow = root.getChild("burrow");
        this.head = this.burrow.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData burrow = modelPartData.addChild("burrow", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = burrow.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

        ModelPartData HeadBase = head.addChild("HeadBase", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-4.5F, -4.0F, -4.5F, 9.0F, 6.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

        ModelPartData grass = HeadBase.addChild("grass", ModelPartBuilder.create().uv(32, -5).cuboid(0.0F, 4.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData grass2_r1 = grass.addChild("grass2_r1", ModelPartBuilder.create().uv(32, -5).cuboid(0.0F, -8.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData rod = head.addChild("rod", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 0.0F));

        ModelPartData rod1 = rod.addChild("rod1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -6.0F));

        ModelPartData rod1_r1 = rod1.addChild("rod1_r1", ModelPartBuilder.create().uv(0, 31).cuboid(-1.0F, -8.0F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        ModelPartData rod2 = rod.addChild("rod2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

        ModelPartData rod2_r1 = rod2.addChild("rod2_r1", ModelPartBuilder.create().uv(18, 31).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        ModelPartData rod3 = rod.addChild("rod3", ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, 0.0F, 0.0F));

        ModelPartData rod3_r1 = rod3.addChild("rod3_r1", ModelPartBuilder.create().uv(9, 31).cuboid(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        ModelPartData rod4 = rod.addChild("rod4", ModelPartBuilder.create(), ModelTransform.pivot(6.0F, 0.0F, 0.0F));

        ModelPartData rod4_r1 = rod4.addChild("rod4_r1", ModelPartBuilder.create().uv(27, 31).cuboid(0.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        ModelPartData base = head.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 0.0F));

        ModelPartData top_base = base.addChild("top_base", ModelPartBuilder.create().uv(35, 19).cuboid(-2.9F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, 0.0F));

        ModelPartData bottom_base = base.addChild("bottom_base", ModelPartBuilder.create().uv(40, 31).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 0.0F));

        ModelPartData dust_trail = burrow.addChild("dust_trail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(BurrowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(BurrowAnimations.BURROW_WALK, limbSwing, limbSwingAmount, 6f, 6.5f);
        this.updateAnimation(entity.idleAnimationState, BurrowAnimations.BURROW_DRILLREADY, ageInTicks, 1f);
        this.updateAnimation(entity.diggingAnimationState, BurrowAnimations.BURROW_DRILLCHARGE, ageInTicks, 1f);
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
