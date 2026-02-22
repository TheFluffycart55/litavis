package net.thefluffycart.litavis.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.custom.PlatypusEntity;
import net.thefluffycart.litavis.entity.variant.PlatypusVariant;

import java.util.Map;

public class PlatypusRenderer extends MobEntityRenderer<PlatypusEntity, PlatypusModel>
{
    private static final Map<PlatypusVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PlatypusVariant.class), map -> {
                map.put(PlatypusVariant.BASE, Identifier.of(Litavis.MOD_ID, "textures/entity/platypus/platypus.png"));
                map.put(PlatypusVariant.PERRY, Identifier.of(Litavis.MOD_ID, "textures/entity/platypus/platypus_agent.png"));
            });
    public PlatypusRenderer(EntityRendererFactory.Context context) {
        super(context, new PlatypusModel(context.getPart(ModEntityModelLayers.PLATYPUS)), .2f);
    }

    @Override
    public Identifier getTexture(PlatypusEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(PlatypusEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}