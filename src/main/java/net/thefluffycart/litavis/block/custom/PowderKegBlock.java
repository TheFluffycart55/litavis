package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.thefluffycart.litavis.Litavis;
import org.jetbrains.annotations.Nullable;

public class PowderKegBlock extends FallingBlock {

    public static final MapCodec<PowderKegBlock> CODEC = createCodec(PowderKegBlock::new);
    public static final BooleanProperty UNSTABLE;
    public static final Float POWER = 0.3f;

    public MapCodec<PowderKegBlock> getCodec() {
        return CODEC;
    }

    public PowderKegBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(UNSTABLE, false));
    }

    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (!oldState.isOf(state.getBlock())) {
            if (world.isReceivingRedstonePower(pos)) {
                explode(world, null, pos, POWER);
                world.removeBlock(pos, false);
            }

        }
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity powderKegEntity) {
        super.onLanding(world, pos, fallingBlockState, currentStateInPos, powderKegEntity);
        if (!world.isClient) {
            explode(world, null, pos, POWER);
        }
    }

    @Override
    public DamageSource getDamageSource(Entity attacker) {
        return super.getDamageSource(attacker);
    }

    public void explode(World world, DamageSource damageSource, BlockPos pos, float power) {
        if (!world.isClient) {
            float d = (float) Math.sqrt(power);
            world.createExplosion(null, damageSource, (ExplosionBehavior)null, pos.getX(), pos.getY(), pos.getZ(), (float)((double)4.0F * (double)1.5F * d), false, World.ExplosionSourceType.TNT);
            world.removeBlock(pos, false);
        }

    }

    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if (world.isReceivingRedstonePower(pos)) {
            explode(world, null, pos, POWER);
            world.removeBlock(pos, false);
        }

    }

    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && !player.isCreative() && (Boolean)state.get(UNSTABLE)) {
            explode(world, null, pos, POWER);
        }

        return super.onBreak(world, pos, state, player);
    }

    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!stack.isOf(Items.FLINT_AND_STEEL) && !stack.isOf(Items.FIRE_CHARGE)) {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        } else {
            explode(world, null, pos, POWER);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            Item item = stack.getItem();
            if (stack.isOf(Items.FLINT_AND_STEEL)) {
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            } else {
                stack.decrementUnlessCreative(1, player);
            }

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ItemActionResult.success(world.isClient);
        }
    }

    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos blockPos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.canModifyAt(world, blockPos)) {
                explode(world, null, world.getSpawnPos(), POWER);
                world.removeBlock(blockPos, false);
            }
        }

    }

    public boolean shouldDropItemsOnExplosion(Explosion explosion) {
        return false;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{UNSTABLE});
    }

    static {
        UNSTABLE = Properties.UNSTABLE;
    }
}
