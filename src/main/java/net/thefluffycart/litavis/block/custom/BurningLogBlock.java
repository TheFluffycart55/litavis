package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BurningLogBlock extends Block {
    public static BooleanProperty IS_BURNING = BooleanProperty.of("is_burning");
    public static final MapCodec<BurningLogBlock> CODEC = BurningLogBlock.createCodec(BurningLogBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS;


    public BurningLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y));
        this.setDefaultState(this.stateManager.getDefaultState().with(IS_BURNING, false));
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean burning = state.get(IS_BURNING);
        if (!burning)
        {
            if (stack.isOf(Items.FLINT_AND_STEEL))
            {
                this.ignite(world, state, pos);
                stack.damage(1, player, player.getPreferredEquipmentSlot(stack));
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            else if (stack.isOf(Items.FIRE_CHARGE))
            {
                this.ignite(world, state, pos);
                stack.damage(1, player, player.getPreferredEquipmentSlot(stack));
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    public MapCodec<? extends BurningLogBlock> getCodec() {
        return CODEC;
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return changeRotation(state, rotation);
    }

    public static BlockState changeRotation(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)state.get(AXIS)) {
                    case X -> {
                        return state.with(AXIS, Direction.Axis.Z);
                    }
                    case Z -> {
                        return state.with(AXIS, Direction.Axis.X);
                    }
                    default -> {
                        return state;
                    }
                }
            default:
                return state;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AXIS);
        builder.add(IS_BURNING);
    }

    public void ignite(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, state.with(IS_BURNING, true), 3);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        this.getDefaultState().with(IS_BURNING, Boolean.FALSE);
        return this.getDefaultState().with(AXIS, ctx.getSide().getAxis());
    }

    static {
        AXIS = Properties.AXIS;
    }
}
