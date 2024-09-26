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

        for (int i = 0; i < height; ++i) {
            BlockPos currentPos = startPos.up(i);
            placeLog(world, replacer, random, currentPos, config);
            if (i > height / 2) {
                if (random.nextFloat() >= 0.2) {
                    boolean branchAdded = false;
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        if (!branchAdded && random.nextBoolean()) {
                            BlockPos branchBasePos = currentPos.offset(direction);
                            if (TreeFeature.isAirOrLeaves(world, branchBasePos))
                            {
                                foliageNodes.addAll(generateBranch(world, replacer, random, branchBasePos, config, direction));
                                branchAdded = true;
                            }
                        }
                    }
                }
            }
        }

        return foliageNodes;
    }

    private void thickenTrunkTop(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                 Random random, BlockPos currentPos, TreeFeatureConfig config) {
        // Method to place a log, extend up, and optionally down
        BiConsumer<BlockPos, BlockState> placeLogWithExtension = (pos, state) -> {
            // Place log at current position
            placeLog(world, replacer, random, pos, config);

            // Always extend one block upwards
            placeLog(world, replacer, random, pos.up(), config);

            // Randomly extend downwards (up to 2 blocks)
            if (random.nextFloat() < 0.4) {
                placeLog(world, replacer, random, pos.down(1), config);
                if (random.nextFloat() < 0.5) {
                    placeLog(world, replacer, random, pos.down(2), config);
                }
            }
        };

        // Randomly thicken only certain sides to make the top uneven
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.north(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.west(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.north().west(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.east().north(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.east(2).north(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.east(2), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.south().west(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.south(2).west(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.south(2), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.south(2).east(), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.south(2).east(2), Blocks.OAK_LOG.getDefaultState());
        if (random.nextFloat() < 0.7) placeLogWithExtension.accept(currentPos.south().east(2), Blocks.OAK_LOG.getDefaultState());
    }

    private List<FoliagePlacer.TreeNode> generateBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                        Random random, BlockPos trunkPos, TreeFeatureConfig config, Direction direction)
    {
        List<FoliagePlacer.TreeNode> branchNodes = Lists.newArrayList();
        int branchLength = random.nextInt(3) + 2;
        BlockPos.Mutable branchPos = trunkPos.mutableCopy();

        if (!TreeFeature.isAirOrLeaves(world, branchPos))
        {
            return branchNodes;
        }

        placeDirectionalLog(world, replacer, random, branchPos, config, direction.getAxis());

        for (int i = 1; i < branchLength; i++)
        {
            if (i > 1 && random.nextFloat() < 0.6)
            {
                direction = Direction.Type.HORIZONTAL.random(random); // Randomize direction after the first segment
            }
            branchPos.move(direction);
            if (!TreeFeature.isAirOrLeaves(world, branchPos))
            {
                break;
            }
            placeDirectionalLog(world, replacer, random, branchPos, config, direction.getAxis());

            if (i == branchLength - 1)
            {
                branchNodes.add(new FoliagePlacer.TreeNode(branchPos.up(2),0, false));
            }
        }
            return branchNodes;
    }



    private void thickenBaseAndGenerateRoots(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                             Random random, BlockPos basePos, TreeFeatureConfig config) {
        // Define the corners of the 2x2 base
        BlockPos[] baseCorners = new BlockPos[] {
                basePos, // base position itself (e.g., bottom-left corner)
                basePos.east(), // base + 1 east (right side of the 2x2)
                basePos.south(), // base + 1 south (bottom side of the 2x2)
                basePos.south().east() // base + 1 south + 1 east (bottom-right corner)
        };

        // Randomized thickening around the entire 2x2 base
        for (BlockPos corner : baseCorners) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                if (random.nextFloat() > 0.6) {
                    placeLog(world, replacer, random, corner.offset(direction), config);
                    placeLog(world, replacer, random, corner.offset(direction).down(), config);
                    if (random.nextFloat() > 0.4) {
                        placeLog(world, replacer, random, corner.offset(direction).down(2), config);
                        if (random.nextFloat() > 0.5) {
                            placeLog(world, replacer, random, corner.offset(direction).down(3), config);
                            if (random.nextFloat() > 0.6) {
                                placeLog(world, replacer, random, corner.offset(direction).down(4), config);
                            }
                        }
                    }
                    if (random.nextBoolean()) {
                        placeLog(world, replacer, random, corner.offset(direction).up(), config);
                    }
                }
            }
        }

        // Randomized root generation with a chance to spawn
        for (BlockPos corner : baseCorners) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                if (random.nextFloat() > 0.6) {  // 60% chance for each root to spawn
                    BlockPos rootPos = corner.offset(direction);
                    generateRandomizedRoots(world, replacer, random, rootPos, config);
                }
            }
        }
    }



    private void generateRandomizedRoots(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                         Random random, BlockPos rootPos, TreeFeatureConfig config) {
        // Always place the root log at the current position
        placeLog(world, replacer, random, rootPos, config);

        // Check if the block below is dirt and replace with rooted dirt
        if (world.testBlockState(rootPos.down(), state -> state.isOf(Blocks.DIRT))) {
            replacer.accept(rootPos.down(), Blocks.ROOTED_DIRT.getDefaultState());

            // If air is below the rooted dirt, place hanging roots
            if (world.testBlockState(rootPos.down(2), BlockState::isAir)) {
                replacer.accept(rootPos.down(2), Blocks.HANGING_ROOTS.getDefaultState());
            }
        }

        // Always extend at least one block downwards
        placeLog(world, replacer, random, rootPos.down(1), config);

        // Randomly extend further downwards by up to 2 more blocks (70% chance per block)
        if (random.nextFloat() < 0.4) {
            placeLog(world, replacer, random, rootPos.down(3), config);
            if (random.nextFloat() < 0.3) {
                placeLog(world, replacer, random, rootPos.down(4), config);
            }
        }

        // Rare chance to extend upwards (20% chance)
        if (random.nextFloat() < 0.2) {
            placeLog(world, replacer, random, rootPos.up(1), config);
        }
    }

    private void placeDirectionalLog(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, BlockPos pos, TreeFeatureConfig config, Direction.Axis axis) {
        if (TreeFeature.isAirOrLeaves(world, pos)) {
            replacer.accept(pos, config.trunkProvider.get(random, pos).with(PillarBlock.AXIS, axis)); // Place log with config's trunk provider
        }
    }

    private void placeLog(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, BlockPos pos, TreeFeatureConfig config) {
        if (TreeFeature.isAirOrLeaves(world, pos)) {
            this.getAndSetState(world, replacer, random, pos, config);
        }
    }
}
