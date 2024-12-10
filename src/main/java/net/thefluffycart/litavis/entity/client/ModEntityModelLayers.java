package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;

public class ModEntityModelLayers {
    public static final EntityModelLayer BURROW =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "burrow"), "main");
    public static final EntityModelLayer EARTH_CHARGE =
            new EntityModelLayer(Identifier.of(Litavis.MOD_ID, "earth_charge"), "main");
}
