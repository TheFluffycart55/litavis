package net.thefluffycart.litavis.world.tree.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.thefluffycart.litavis.world.tree.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class EucalyptusTrunkPlacer extends TrunkPlacer {
    //I DO NOT REMEMBER HOW THIS WORKS, AND I AM SCARED TO POKE THIS BEAR WITH A STICK
    public static final MapCodec<EucalyptusTrunkPlacer> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
            fillTrunkPlacerFields(instance).apply(instance, EucalyptusTrunkPlacer::new));

    public EucalyptusTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerTypes.EUCALYPTUS_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {

        List<FoliagePlacer.TreeNode> foliageNodes = Lists.newArrayList();
        setDirtAt(replacer, startPos.down());
        for (int i = 0; i < 7; i++) {
            BlockPos trunkPos = startPos.up(i);
            placeLog(world, replacer, random, trunkPos, config);
        }
        BlockPos branchStartPos = startPos.up(7);
        placeDirectionalLog(world, replacer, random, branchStartPos, config, Direction.EAST.getAxis()); // East
        placeDirectionalLog(world, replacer, random, branchStartPos, config, Direction.WEST.getAxis()); // West
        placeDirectionalLog(world, replacer, random, branchStartPos, config, Direction.SOUTH.getAxis()); // South
        placeDirectionalLog(world, replacer, random, branchStartPos, config, Direction.NORTH.getAxis()); // North
        return List.of(
                new FoliagePlacer.TreeNode(branchStartPos.east(2).up(1), 0, false),
                new FoliagePlacer.TreeNode(branchStartPos.west(2).up(1), 0, false),
                new FoliagePlacer.TreeNode(branchStartPos.south(2).up(1), 0, false),
                new FoliagePlacer.TreeNode(branchStartPos.north(2).up(1), 0, false)
        );
    }


    private void setDirtAt(BiConsumer<BlockPos, BlockState> replacer, BlockPos pos) {
        replacer.accept(pos, Blocks.DIRT.getDefaultState());
    }

    private void placeDirectionalLog(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, BlockPos pos, TreeFeatureConfig config, Direction.Axis axis) {
        if (TreeFeature.isAirOrLeaves(world, pos)) {
            replacer.accept(pos, config.trunkProvider.get(random, pos).with(PillarBlock.AXIS, axis));
        }
    }

    private void placeLog(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, BlockPos pos, TreeFeatureConfig config) {
        if (TreeFeature.isAirOrLeaves(world, pos)) {
            this.getAndSetState(world, replacer, random, pos, config);
        }
    }
}
