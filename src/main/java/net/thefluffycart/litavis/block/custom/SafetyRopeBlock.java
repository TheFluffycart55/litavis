package net.thefluffycart.litavis.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.thefluffycart.litavis.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class SafetyRopeBlock extends Block implements Waterloggable{
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty BOTTOM = BooleanProperty.of("bottom");
    public static final IntProperty DISTANCE;
    protected static final VoxelShape SHAPE = Block.createCuboidShape((double)6.0F, (double)0.0F, (double)6.0F, (double)10.0F, (double)16.0F, (double)10.0F);

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public SafetyRopeBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BOTTOM, false)).with(WATERLOGGED, false));

    }

    public static int calculateDistance(BlockView world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy().move(Direction.UP);
        BlockState blockState = world.getBlockState(mutable);
        int i = 7;
        if (blockState.isOf(ModBlocks.SAFETY_ROPE)) {
            return 0;
        } else if (blockState.isSideSolidFullSquare(world, mutable, Direction.DOWN) || blockState.isIn(BlockTags.FENCES)) {
            return 0;
        }

        return i;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BOTTOM, WATERLOGGED});
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = super.getPlacementState(ctx);
        if (blockState != null) {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            return (BlockState)blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        } else {
            return null;
        }
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !this.canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if ((Boolean)state.get(WATERLOGGED)) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        boolean isBottom = isBottom(world, pos);
        if (state.get(BOTTOM) != isBottom) {
            world.setBlockState(pos, state.with(BOTTOM, isBottom), Block.NOTIFY_ALL);
        }

        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);

        BlockPos above = pos.down();
        BlockState aboveState = world.getBlockState(below);
        if (aboveState.getBlock() == this)
        {
            boolean bottom = isBottom(world, above);
            if (aboveState.get(BOTTOM) != bottom) {
                world.setBlockState(below, belowState.with(BOTTOM, bottom), Block.NOTIFY_ALL);
            }
        }
    }

    protected VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getStack().isOf(this.asItem());
    }

    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 1);
        }

    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        Direction direction = attachedDirection(state).getOpposite();
        return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()) || blockState.isOf(this) || blockState.isIn(BlockTags.FENCES);
    }

    protected static Direction attachedDirection(BlockState state) {
        return Direction.DOWN;
    }

    private boolean isBottom(World world, BlockPos pos) {
        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);
        return belowState.getBlock() != this;
    }
    static {
        WATERLOGGED = Properties.WATERLOGGED;
        DISTANCE = Properties.DISTANCE_0_7;
    }

}
