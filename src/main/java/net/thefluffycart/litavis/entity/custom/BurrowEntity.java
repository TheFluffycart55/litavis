package net.thefluffycart.litavis.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.EnumSet;

public class BurrowEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState diggingAnimationState = new AnimationState();
    private float eyeOffset = 0f;
    private int eyeOffsetCooldown;
    private static final TrackedData<Byte> BURROW_FLAGS = DataTracker.registerData(BurrowEntity.class, TrackedDataHandlerRegistry.BYTE);

    public BurrowEntity(EntityType<? extends BurrowEntity> entityType, World world) {

        super((EntityType<? extends HostileEntity>)entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0f);
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0f);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0f);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0f);
        this.experiencePoints = 10;
    }

    protected void initGoals() {
        this.goalSelector.add(4, new BurrowEntity.ShootFireballGoal(this));
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal((PathAwareEntity)this, 1.0, 0.0f));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createburrowAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(BURROW_FLAGS, (byte)0);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BREEZE_WHIRL;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BREEZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BREEZE_DEATH;
    }

    @Override
    public float getBrightnessAtEyes() {
        return 1.0f;
    }

    @Override
    public boolean isOnFire() {
        return this.isFireActive();
    }

    private boolean isFireActive() {
        return (this.dataTracker.get(BURROW_FLAGS) & 1) != 0;
    }

    void setFireActive(boolean fireActive) {
        byte b = this.dataTracker.get(BURROW_FLAGS);
        b = fireActive ? (byte)(b | 1) : (byte)(b & 0xFFFFFFFE);
        this.dataTracker.set(BURROW_FLAGS, b);
    }

    @Override
    public void tickMovement() {
        if (!this.isOnGround() && this.getVelocity().y < 0.0) {
            this.setVelocity(this.getVelocity().multiply(1.0, 0.6, 1.0));
        }
        if (this.getWorld().isClient) {
            if (this.random.nextInt(24) == 0 && !this.isSilent()) {
                this.getWorld().playSound(this.getX() + 0.5, this.getY() + 0.5, this.getZ() + 0.5, SoundEvents.ENTITY_BREEZE_INHALE, this.getSoundCategory(), 1.0f + this.random.nextFloat(), this.random.nextFloat() * 0.7f + 0.3f, false);
            }
        }
        super.tickMovement();
    }

    @Override
    public boolean hurtByWater() {
        return true;
    }

    @Override
    protected void mobTick() {
        LivingEntity livingEntity;
        --this.eyeOffsetCooldown;
        if (this.eyeOffsetCooldown <= 0) {
            this.eyeOffsetCooldown = 100;
            this.eyeOffset = (float)this.random.nextTriangular(0.5, 6.891);
        }
        if ((livingEntity = this.getTarget()) != null && livingEntity.getEyeY() > this.getEyeY() + (double)this.eyeOffset && this.canTarget(livingEntity)) {
            Vec3d vec3d = this.getVelocity();
            this.setVelocity(this.getVelocity().add(0.0, ((double)0.3f - vec3d.y) * (double)0.3f, 0.0));
            this.velocityDirty = true;
        }
        super.mobTick();
    }

    static class ShootFireballGoal
            extends Goal {
        private final BurrowEntity burrow;
        private int fireballsFired;
        private int fireballCooldown;
        private int targetNotVisibleTicks;

        public ShootFireballGoal(BurrowEntity burrow) {
            this.burrow = burrow;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.burrow.getTarget();
            return livingEntity != null && livingEntity.isAlive() && this.burrow.canTarget(livingEntity);
        }

        @Override
        public void start() {
            this.fireballsFired = 0;
        }

        @Override
        public void stop() {
            this.burrow.setFireActive(false);
            this.targetNotVisibleTicks = 0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }


        @Override
        public void tick() {
            --this.fireballCooldown;
            LivingEntity livingEntity = this.burrow.getTarget();
            if (livingEntity == null) {
                return;
            }
            boolean bl = this.burrow.getVisibilityCache().canSee(livingEntity);
            this.targetNotVisibleTicks = bl ? 0 : ++this.targetNotVisibleTicks;
            double d = this.burrow.squaredDistanceTo(livingEntity);
            if (d < 4.0) {
                if (!bl) {
                    return;
                }
                if (this.fireballCooldown <= 0) {
                    this.fireballCooldown = 20;
                    this.burrow.tryAttack(livingEntity);
                }
                this.burrow.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
            } else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
                double e = livingEntity.getX() - this.burrow.getX();
                double f = livingEntity.getBodyY(0.5) - this.burrow.getBodyY(0.5);
                double g = livingEntity.getZ() - this.burrow.getZ();
                if (this.fireballCooldown <= 0) {
                    ++this.fireballsFired;
                    if (this.fireballsFired == 1) {
                        this.fireballCooldown = 60;
                        this.burrow.setFireActive(true);
                    } else if (this.fireballsFired <= 4) {
                        this.fireballCooldown = 6;
                    } else {
                        this.fireballCooldown = 100;
                        this.fireballsFired = 0;
                        this.burrow.setFireActive(false);
                    }
                    if (this.fireballsFired > 1) {
                        double h = Math.sqrt(Math.sqrt(d)) * 0.5;
                        if (!this.burrow.isSilent()) {
                            this.burrow.getWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, this.burrow.getBlockPos(), 0);
                        }
                        for (int i = 0; i < 1; ++i) {
                            Vec3d vec3d = new Vec3d(this.burrow.getRandom().nextTriangular(e, 2.297 * h), f, this.burrow.getRandom().nextTriangular(g, 2.297 * h));
                            SmallFireballEntity smallFireballEntity = new SmallFireballEntity(this.burrow.getWorld(), this.burrow, vec3d.normalize());
                            smallFireballEntity.setPosition(smallFireballEntity.getX(), this.burrow.getBodyY(0.5) + 0.5, smallFireballEntity.getZ());
                            this.burrow.getWorld().spawnEntity(smallFireballEntity);
                        }
                    }
                }
                this.burrow.getLookControl().lookAt(livingEntity, 10.0f, 10.0f);
            } else if (this.targetNotVisibleTicks < 5) {
                this.burrow.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
            }
            super.tick();
        }

        private double getFollowRange() {
            return this.burrow.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        }
    }

}
