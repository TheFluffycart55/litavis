package net.thefluffycart.litavis.world;

import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.custom.BurningLogBlock;
import net.thefluffycart.litavis.block.custom.OilyLeavesBlock;
import net.thefluffycart.litavis.world.tree.custom.EucalyptusTrunkPlacer;

import java.util.List;
import java.util.Random;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> EUCALYPTUS_KEY = registerKey("eucalyptus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TRIPSLATE_ROCK = registerKey("tripslate_rock");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        Random leavesRandom = new Random();
        boolean hasOil = leavesRandom.nextBoolean();
        register(context, EUCALYPTUS_KEY, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.EUCALYPTUS_LOG.getDefaultState().with(BurningLogBlock.AXIS, Direction.Axis.Y)),
                new ForkingTrunkPlacer(5, 2, 4), BlockStateProvider.of(ModBlocks.EUCALYPTUS_LEAVES.getDefaultState().with(OilyLeavesBlock.HAS_OIL, true)),
                new CherryFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1), ConstantIntProvider.create(5),
                        0.25f, 0.5f, 0.15f, 0.05f),
                new TwoLayersFeatureSize(3, 2, 3)).dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT)).build()
        );

        register(context, TRIPSLATE_ROCK, Feature.FOREST_ROCK, new SingleStateFeatureConfig(ModBlocks.TRIPSLATE.getDefaultState()));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Litavis.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
