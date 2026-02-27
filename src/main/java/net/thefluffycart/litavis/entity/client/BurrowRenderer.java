package net.thefluffycart.litavis.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.variant.BurrowVariant;

import java.util.Map;

public class BurrowRenderer extends MobEntityRenderer<BurrowEntity, BurrowModel>
{
    private static final Map<BurrowVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BurrowVariant.class), map -> {
                map.put(BurrowVariant.BURROW_BASE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/burrow.png"));
                map.put(BurrowVariant.BLACK, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/black_burrow.png"));
                map.put(BurrowVariant.BLUE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/blue_burrow.png"));
                map.put(BurrowVariant.BROWN, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/brown_burrow.png"));
                map.put(BurrowVariant.CYAN, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/cyan_burrow.png"));
                map.put(BurrowVariant.GRAY, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/gray_burrow.png"));
                map.put(BurrowVariant.GREEN, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/green_burrow.png"));
                map.put(BurrowVariant.LIGHT_BLUE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/light_blue_burrow.png"));
                map.put(BurrowVariant.LIGHT_GRAY, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/light_gray_burrow.png"));
                map.put(BurrowVariant.LIME, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/lime_burrow.png"));
                map.put(BurrowVariant.MAGENTA, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/magenta_burrow.png"));
                map.put(BurrowVariant.ORANGE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/orange_burrow.png"));
                map.put(BurrowVariant.PINK, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/pink_burrow.png"));
                map.put(BurrowVariant.PURPLE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/purple_burrow.png"));
                map.put(BurrowVariant.RED, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/red_burrow.png"));
                map.put(BurrowVariant.WHITE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/white_burrow.png"));
                map.put(BurrowVariant.YELLOW, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/yellow_burrow.png"));
                map.put(BurrowVariant.LAYERED, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/layered_burrow.png"));
            });
    public BurrowRenderer(EntityRendererFactory.Context context) {
        super(context, new BurrowModel(context.getPart(ModEntityModelLayers.BURROW)), .2f);
    }

    @Override
    public Identifier getTexture(BurrowEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(BurrowEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public static BurrowModel updatePartVisibility(BurrowModel model, ModelPart... modelParts) {
        model.getPart().visible = false;

        for (ModelPart modelPart : modelParts) {
            modelPart.visible = true;
        }

        return model;
    }
}
