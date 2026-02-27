package net.thefluffycart.litavis.entity.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.effect.entity.ApplyMobEffectEnchantmentEffect;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.effect.ModEffects;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.item.ModItems;
import net.thefluffycart.litavis.util.ModTags;

import java.util.List;

public class EarthChargeEntity extends ThrownItemEntity implements FlyingItemEntity {
    public EarthChargeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EarthChargeEntity(World world, LivingEntity owner) {
        super(ModEntities.EARTH_CHARGE, owner, world);
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.SMOKE : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 1) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(ParticleTypes.MYCELIUM, this.getParticleX((double)0.5F), this.getRandomBodyY(), this.getParticleZ((double)0.5F), (double)0.0F, (double)0.0F, (double)0.0F);
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();
        BlockPos pos = blockHitResult.getBlockPos();
        if (!world.isClient && world.getBlockState(pos).isSolidBlock(world, pos)) {
            BlockPos hitPos = blockHitResult.getBlockPos();
            replaceBlocks((ServerWorld) world, hitPos);
            entityLaunch((ServerWorld) world, hitPos);
            this.discard();
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        World world = this.getWorld();
        Entity target = entityHitResult.getEntity();
        if (!world.isClient && target instanceof LivingEntity && !((LivingEntity) target).isBlocking())
        {
            BlockPos hitPos = target.getBlockPos().down();
            replaceBlocks((ServerWorld) world, hitPos);
            entityLaunch((ServerWorld) world, hitPos);
            this.discard();
        }
        else return;
        super.onEntityHit(entityHitResult);
    }



    private void entityLaunch(ServerWorld serverWorld, BlockPos pos) {
        Box impactBox = new Box(pos).expand(2.5, 2.5, 2.5);
        List<LivingEntity> nearbyEntities = serverWorld.getEntitiesByClass(
                LivingEntity.class,
                impactBox,
                entity -> isAlive()
        );

        for (LivingEntity entity : nearbyEntities) {
            if (entity.isBlocking())
            {
                break;
            }
            else
            {
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.UNSTEADY, 30, 0), this);
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0), this);
                Vec3d currentMotion = entity.getVelocity();
                entity.addVelocity(currentMotion.x, 0.5, currentMotion.z);
                entity.velocityModified = true;
            }
        }
    }

    private void replaceBlocks(ServerWorld world, BlockPos centerPos) {
        if (this.getWorld().getGameRules().getBoolean(Litavis.EARTH_CHARGE_GRIEFING)) {
            if (this.getWorld().getGameRules().getBoolean(Litavis.EARTH_CHARGE_RESTRICTED))
            {
                playSound(SoundEvents.ENTITY_SNIFFER_DIGGING_STOP, 3, 1);
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                            continue;
                        }
                        BlockPos targetPos = centerPos.add(x, 0, z);
                        BlockState blockState = world.getBlockState(targetPos);

                        if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_RESTRICTED)) {
                            spawnUpperFallingBlock(world, targetPos, blockState);
                            world.removeBlock(targetPos, false);
                        }
                    }
                }
            }

            else
            {
                playSound(SoundEvents.ENTITY_SNIFFER_DIGGING_STOP, 3, 1);
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                            continue;
                        }
                        BlockPos targetPos = centerPos.add(x, 0, z);
                        BlockState blockState = world.getBlockState(targetPos);
                        if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_THROWABLE) || blockState.isIn(BlockTags.LEAVES) || blockState.isIn(BlockTags.LOGS)) {
                            if (world.getBlockState(centerPos.up()).isOf(Blocks.AIR))
                            {
                                spawnUpperFallingBlock(world, targetPos, blockState);
                                world.removeBlock(targetPos, false);
                            }
                        }
                    }
                }
            }

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos targetPos = centerPos.add(x, -1, z);
                    BlockState blockState = world.getBlockState(targetPos);

                    if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_THROWABLE) || blockState.isIn(BlockTags.LEAVES) || blockState.isIn(BlockTags.LOGS)) {
                        if (world.getBlockState(centerPos.up()).isOf(Blocks.AIR)) {
                            spawnLowerFallingBlock(world, targetPos, blockState);
                            world.removeBlock(targetPos, false);
                        }
                    }
                }
            }
        }

    }
    private void spawnLowerFallingBlock(ServerWorld world, BlockPos pos, BlockState blockState) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, blockState);
        if (world.getBlockState(pos.up()).isOf(Blocks.AIR) || world.getBlockState(pos.up()).isIn(ModTags.Blocks.EARTH_CHARGE_THROWABLE)) {
            fallingBlockEntity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            fallingBlockEntity.setVelocity(new Vec3d(0, 0.7, 0));
            fallingBlockEntity.velocityModified = true;
            world.spawnEntity(fallingBlockEntity);

//            world.spawnEntity(fallingBlockEntity);
        }
    }
    private void spawnUpperFallingBlock(ServerWorld world, BlockPos pos, BlockState blockState) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, blockState);
        if (world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
            fallingBlockEntity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            fallingBlockEntity.setVelocity(new Vec3d(0, 0.75, 0));
            fallingBlockEntity.velocityModified = true;
            world.spawnEntity(fallingBlockEntity);

//            world.spawnEntity(fallingBlockEntity);
        }
    }

    public void tick() {
        for (int x = 0; x<400; x++)
        {

        }
        if (!this.getWorld().isClient && this.getBlockY() > this.getWorld().getTopY() + 30) {
            this.discard();
        } else {
            super.tick();
        }

    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.EARTH_CHARGE;
    }

    @Override
    public ItemStack getStack() {
        return ModItems.EARTH_CHARGE.getDefaultStack();
    }
}
