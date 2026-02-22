package net.thefluffycart.litavis.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.custom.SafetyRopeBlock;
import org.jetbrains.annotations.Nullable;

public class SafetyRopeItem extends BlockItem {
    private static final BooleanProperty WATERLOGGED;

    public SafetyRopeItem(Block block, Settings settings) {
        super(block, settings);
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

    @Nullable
    public ItemPlacementContext getPlacementContext(ItemPlacementContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = this.getBlock();
        if (!blockState.isOf(block)) {
            return SafetyRopeBlock.calculateDistance(world, blockPos) == 7 ? null : context;
        } else {
            Direction direction = Direction.DOWN;

            int i = 0;
            BlockPos.Mutable mutable = blockPos.mutableCopy().move(direction);

            while(i < 7) {
                if (!world.isClient && !world.isInBuildLimit(mutable)) {
                    PlayerEntity playerEntity = context.getPlayer();
                    int j = world.getTopY();
                    if (playerEntity instanceof ServerPlayerEntity && mutable.getY() >= j) {
                        ((ServerPlayerEntity)playerEntity).sendMessageToClient(Text.translatable("build.tooHigh", new Object[]{j - 1}).formatted(Formatting.DARK_GREEN), true);
                    }
                    break;
                }

                blockState = world.getBlockState(mutable);
                if (!blockState.isOf(this.getBlock())) {
                    if (blockState.canReplace(context)) {
                        return ItemPlacementContext.offset(context, mutable, direction);
                    }
                    break;
                }

                mutable.move(direction);
            }

            return null;
        }
    }
    static {
        WATERLOGGED = Properties.WATERLOGGED;
    }

    protected boolean checkStatePlacement() {
        return false;
    }
}