package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.util.ModTags;

public class TripslateBlock extends Block {
    public static final BooleanProperty FALLING = BooleanProperty.of("falling");
    public static final MapCodec<PillarBlock> CODEC = PillarBlock.createCodec(PillarBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;

    public TripslateBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(AXIS, Direction.Axis.Y));
        this.setDefaultState(this.getDefaultState().with(FALLING, false));

    }

    public MapCodec<? extends PillarBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return PillarBlock.changeRotation(state, rotation);
    }

    protected int getFallDelay()
    {
        return 200;
    }

    //DROP ON HIT
    @Override
    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos blockPos = hit.getBlockPos();
        if (!world.isClient && state.isOf(this)) {
            blockDrop(state, (ServerWorld) world, blockPos);
        }
    }

    //DESPITE ALREADY BEING TOLD IT'S FALLING, DOUBLE CHECK TO MAKE SURE WE'RE UPDATED, AND ACTUALLY FALLING
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.isOf(this) && neighborState.get(FALLING)) {
            this.blockDrop(state, (ServerWorld) world, pos);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    //UPDATE NEIGHBORING TRIPSLATE
    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) {
            return;
        }
        boolean bl = state.get(FALLING);
        if (bl != FALLING.equals(Boolean.FALSE)) {
            if (bl) {
                world.scheduleBlockTick(pos, this, 4);
            } else {
                world.setBlockState(pos, (BlockState)state.cycle(FALLING), Block.NOTIFY_LISTENERS);
            }
        }
    }

    public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingAnvil(attacker);
    }

    //THIS CODE HAUNTS MY NIGHTMARES, I DO NOT KNOW HOW IT WORKS, OR WHY THIS STOPS THE ITEM FROM DROPPING
    //BUT IT DOES AND I WILL NOT TOUCH IT
    public void blockDrop(BlockState state, ServerWorld world, BlockPos pos) {
        //MAKE SURE YOU CAN FALL
        if (!TripslateBlock.canFallThrough(world.getBlockState(pos.down())) || pos.getY() < world.getBottomY()) {
            return;
        }
        //CHECK IF NEIGHBOR IS TRIPSLATE
        if (state.isOf(this)) {
            //REMOVE SELF
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
            //SETUP FALLING BLOCK ENTITY
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
            fallingBlockEntity.handleFallDamage(1f, 5f, getDamageSource(fallingBlockEntity));
            this.configureFallingBlockEntity(fallingBlockEntity);
            //UPDATE NEIGHBORING TRIPSLATE TO DROP
            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = pos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);
                if (neighborState.isOf(this)) {
                    blockDrop(neighborState, world, neighborPos);
                } else {
                    world.updateNeighbors(neighborPos, this);
                }
            }
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(1.5F, 40);
    }

    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isLiquid() || state.isReplaceable();
    }

    //PILLAR BLOCK
    public static BlockState changeRotation(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90: {
                switch (state.get(AXIS)) {
                    case X: {
                        return (BlockState)state.with(AXIS, Direction.Axis.Z);
                    }
                    case Z: {
                        return (BlockState)state.with(AXIS, Direction.Axis.X);
                    }
                }
                return state;
            }
        }
        return state;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
        builder.add(FALLING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        this.getDefaultState().with(FALLING, Boolean.FALSE);
        return this.getDefaultState().with(AXIS, ctx.getSide().getAxis());
    }
}
