package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.thefluffycart.litavis.util.ModTags;

public class TripslateBrickBlock extends Block {
    public static final BooleanProperty FALLING = BooleanProperty.of("falling");

    public TripslateBrickBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FALLING, false));

    }

    @Override
    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos blockPos = hit.getBlockPos();
        if (!world.isClient && state.isOf(this) || state.isIn(ModTags.Blocks.TRIPSLATE_BRICK_VARIANTS)) {
            blockDrop(state, (ServerWorld) world, blockPos);
        }
    }

    protected int getFallDelay()
    {
        return 200;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.isOf(this) || neighborState.isIn(ModTags.Blocks.TRIPSLATE_BRICK_VARIANTS))
        {
            if (neighborState.get(FALLING))
            {
                this.blockDrop(state, (ServerWorld) world, pos);

            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingAnvil(attacker);
    }


    public void blockDrop(BlockState state, ServerWorld world, BlockPos pos) {
        if (!TripslateBrickBlock.canFallThrough(world.getBlockState(pos.down())) || pos.getY() < world.getBottomY() ) {
            return;
        }
        if (state.isOf(this) || state.isIn(ModTags.Blocks.TRIPSLATE_BRICK_VARIANTS))
        {
            if(canFallThrough(world.getBlockState(pos.down())))
            {
                world.scheduleBlockTick(pos, this, this.getFallDelay());
                world.setBlockState(pos, state.cycle(FALLING));
                FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
                fallingBlockEntity.handleFallDamage(1f, 5f, getDamageSource(fallingBlockEntity));
                world.breakBlock(new BlockPos(pos), false);
                this.configureFallingBlockEntity(fallingBlockEntity);
            }
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
    }

    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isLiquid() || state.isReplaceable();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FALLING);
    }
}