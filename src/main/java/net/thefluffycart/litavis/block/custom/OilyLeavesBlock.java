package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.thefluffycart.litavis.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class OilyLeavesBlock extends LeavesBlock {
    public static BooleanProperty HAS_OIL = BooleanProperty.of("has_oil");
//    public static final MapCodec<BeehiveBlock> CODEC = createCodec(BeehiveBlock::new);

    public OilyLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HAS_OIL, false));
    }

    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean has_oil = (Boolean)state.get(HAS_OIL);
        boolean bl = false;
        if (has_oil) {
            Item item = stack.getItem();
              if (stack.isOf(Items.GLASS_BOTTLE)) {
                stack.decrement(1);
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                this.takeOil(world, state, pos);
                if (stack.isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(ModItems.EUCALYPTUS_OIL_VIAL));
                } else if (!player.getInventory().insertStack(new ItemStack(ModItems.EUCALYPTUS_OIL_VIAL))) {
                    player.dropItem(new ItemStack(ModItems.EUCALYPTUS_OIL_VIAL), false);
                }

                bl = true;
                world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
            }

            if (!world.isClient() && bl) {
                player.incrementStat(Stats.USED.getOrCreateStat(item));
            }
        }
        else if (!has_oil) {
            Item item = stack.getItem();
            if (stack.isOf(ModItems.EUCALYPTUS_OIL_VIAL)) {
                stack.decrement(1);
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                this.giveOil(world, state, pos);
                bl = true;
                world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
                if (stack.isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                } else if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE))) {
                    player.dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
                }
            }

            if (!world.isClient() && bl) {
                player.incrementStat(Stats.USED.getOrCreateStat(item));
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        float f = random.nextFloat();
        boolean has_oil = (Boolean)state.get(HAS_OIL);
        if (has_oil)
        {
            if (!(f > 0.05F)) {
                if (OilyLeavesBlock.canSpawnParticles(world.getBlockState(pos.down())))
                {
                    createParticle(world, pos, state, random);
                }
            }
        }
    }

    private static void createParticle(World world, BlockPos pos, BlockState state, Random random) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        double d = (double)0.0625F;
        double e = (double)pos.getX() + (double)0.5F + vec3d.x + (random.nextDouble() - 0.5);
        double f = (double)((float)(pos.getY() + 0.6) - 0.6875F) - (double)0.0625F;
        double g = (double)pos.getZ() + (double)0.5F + vec3d.z + (random.nextDouble() - 0.5);
        ParticleEffect particleEffect = ParticleTypes.DRIPPING_HONEY;
        world.addParticle(particleEffect, e, f, g, (double)0.0, (double)0.0F, (double)0.0F);
    }

    public void takeOil(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, (BlockState)state.with(HAS_OIL, false), 3);
    }

    public void giveOil(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, (BlockState)state.with(HAS_OIL, true), 3);
    }

    public static boolean canSpawnParticles(BlockState state)
    {
        return state.isAir();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_OIL);
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldDecay(state)) {
            dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }

    }

    protected boolean shouldDecay(BlockState state) {
        return (Integer)state.get(DISTANCE) == 7;
    }
}
