package net.thefluffycart.litavis.world.tree;

import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.mixin.TrunkPlacerTypeInvoker;
import net.thefluffycart.litavis.world.tree.custom.EucalyptusTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<?> EUCALYPTUS_TRUNK_PLACER = TrunkPlacerTypeInvoker.register("eucalyptus_trunk_placer", EucalyptusTrunkPlacer.MAP_CODEC);

    public static void register() {
        Litavis.LOGGER.info("Registering Trunk Placers for " + Litavis.MOD_ID);
    }
}