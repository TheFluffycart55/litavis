package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;

public class ModEntityModelLayers {
    public static final EntityModelLayer BURROW =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "burrow"), "main");
    public static final EntityModelLayer PLATYPUS =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "platypus"), "main");
    public static final EntityModelLayer SCORCHER =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "scorcher"), "main");
    public static final EntityModelLayer EARTH_CHARGE =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "earth_charge"), "main");
    public static final EntityModelLayer BURROW_HEAD =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "burrow_head"), "main");
}
