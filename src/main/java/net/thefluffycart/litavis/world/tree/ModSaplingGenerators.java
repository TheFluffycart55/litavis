package net.thefluffycart.litavis.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator EUCALYPTUS = new SaplingGenerator(Litavis.MOD_ID + ":eucalyptus",
            Optional.empty(), Optional.of(ModConfiguredFeatures.EUCALYPTUS_KEY), Optional.empty());
}