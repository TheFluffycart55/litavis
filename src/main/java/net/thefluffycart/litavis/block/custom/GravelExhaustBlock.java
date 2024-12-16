package net.thefluffycart.litavis.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.WindChargeItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public class GravelExhaustBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape
            (0, 0, 0, 16, 12, 16);
    public GravelExhaustBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        GravelExhaustBlock.spawnSmokeParticle(world, pos, true, true);
        super.randomTick(state, world, pos, random);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        randomTick(state, (ServerWorld) world, pos, Random.create());
        entity.handleFallDamage(fallDistance, 0.05f, world.getDamageSources().fall());
    }

    public static void spawnSmokeParticle(World world, BlockPos pos, boolean isSignal, boolean lotsOfSmoke) {
        Random random = world.getRandom();
        SimpleParticleType simpleParticleType = isSignal ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        world.addImportantParticle(simpleParticleType, true, (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
        if (lotsOfSmoke) {
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4, (double)pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
        }
    }

    @Override
    protected int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return super.getStrongRedstonePower(state, world, pos, direction);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {

        int signalStrength = world.getReceivedRedstonePower(pos);

        if (signalStrength > 0 && signalStrength < 16)
        {
            if (!entity.isSneaky())
            {
                if (world.isReceivingRedstonePower(pos))
                {
                    world.addParticle(ParticleTypes.GUST, (double)pos.getX() + 0.5, (double)pos.getY() + 2, (double)pos.getZ() + 0.5, 1,1,1);
                    //FOLLOWING MATH BY HOLLOWEDWANDERER
                    //SETS POWER OF 1 TO 3 BLOCK BOOST, AND POWER OF 15 TO 20
                    Vec3d currentMotion = entity.getVelocity();
                    double boostHeight = 1.214*signalStrength+1.786;
                    entity.setVelocity(currentMotion.x, Math.sqrt(2*0.115*boostHeight), currentMotion.z);
                    entity.velocityModified = true;
                }

            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
