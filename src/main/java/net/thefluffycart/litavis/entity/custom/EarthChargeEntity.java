package net.thefluffycart.litavis.entity.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.custom.TripslateBlock;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.item.ModItems;

public class EarthChargeEntity extends ThrownItemEntity {
    //NO IDEA WHY THIS HAD TO BE 3 SEPARATE CONSTRUCTORS, BUT IT WORKS
    public EarthChargeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EarthChargeEntity(World world, LivingEntity owner) {
        super(ModEntities.EARTH_CHARGE, owner, world); // null will be changed later
    }

    public EarthChargeEntity(World world, double x, double y, double z) {
        super(ModEntities.EARTH_CHARGE, x, y, z, world); // null will be changed later
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.MYCELIUM : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    //AN HOUR OF STRUGGLE, FOR THE SOLUTION TO BE THIS SHIT
    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 1) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.getWorld().isClient && this.getWorld() instanceof ServerWorld serverWorld) {
            BlockPos pos = this.getBlockPos().up();

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos blockPos = pos.add(x, 6, z);
                    Block Tripslate = ModBlocks.TRIPSLATE;
                    BlockState blockState = Tripslate.getDefaultState().with(TripslateBlock.FALLING, true);
                    serverWorld.setBlockState(blockPos, blockState);
                }
            }
            boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            this.discard();
        }

        this.discard();
    }

    protected Item getDefaultItem() {
        return ModItems.EARTH_CHARGE;
    }
}
