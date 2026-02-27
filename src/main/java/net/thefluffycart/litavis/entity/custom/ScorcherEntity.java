package net.thefluffycart.litavis.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.EnumSet;

public class ScorcherEntity extends HostileEntity implements SkinOverlayOwner {
    private static final TrackedData<Integer> FUSE_SPEED;
    private static final TrackedData<Boolean> CHARGED;
    private static final TrackedData<Boolean> IGNITED;
    private int lastFuseTime;
    private int currentFuseTime;
    private int fuseTime = 60;
    private int explosionRadius = 8;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public ScorcherEntity(EntityType<? extends ScorcherEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new ScorcherShootGoal(this));
        this.goalSelector.add(3, new ScorcherIgniteGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, (double)1.0F, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    public static DefaultAttributeContainer.Builder createScorcherAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double)0.25F);
    }

    public int getSafeFallDistance() {
        return this.getTarget() == null ? this.getSafeFallDistance(0.0F) : this.getSafeFallDistance(this.getHealth() - 1.0F);
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        boolean bl = super.handleFallDamage(fallDistance, damageMultiplier, damageSource);
        this.currentFuseTime += (int)(fallDistance * 1.5F);
        if (this.currentFuseTime > this.fuseTime - 5) {
            this.currentFuseTime = this.fuseTime - 5;
        }

        return bl;
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    public void tick() {
        if (this.isAlive()) {
            this.lastFuseTime = this.currentFuseTime;
            if (this.isIgnited()) {
                this.setFuseSpeed(1);
            }

            int i = this.getFuseSpeed();
            if (i > 0 && this.currentFuseTime == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
                this.emitGameEvent(GameEvent.PRIME_FUSE);
            }

            this.currentFuseTime += i;
            if (this.currentFuseTime < 0) {
                this.currentFuseTime = 0;
            }

            if (this.currentFuseTime >= this.fuseTime) {
                this.currentFuseTime = this.fuseTime;
                this.explode();
            }
        }

        super.tick();
    }

    public void setTarget(@Nullable LivingEntity target) {
        if (!(target instanceof GoatEntity)) {
            super.setTarget(target);
        }
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CREEPER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CREEPER_DEATH;
    }

    public boolean tryAttack(Entity target) {
        return true;
    }

    public boolean shouldRenderOverlay() {
        return (Boolean)this.dataTracker.get(CHARGED);
    }

    public int getFuseSpeed() {
        return (Integer)this.dataTracker.get(FUSE_SPEED);
    }

    public void setFuseSpeed(int fuseSpeed) {
        this.dataTracker.set(FUSE_SPEED, fuseSpeed);
    }

    private void explode() {
        if (!this.getWorld().isClient) {
            float f = this.shouldRenderOverlay() ? 2.0F : 1.0F;
            this.dead = true;
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, World.ExplosionSourceType.MOB);
            this.spawnEffectsCloud();
            this.onRemoval(RemovalReason.KILLED);
            this.discard();
        }

    }

    private void spawnEffectsCloud() {
        Collection<StatusEffectInstance> collection = this.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setRadius(2.5F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());

            for(StatusEffectInstance statusEffectInstance : collection) {
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }

            this.getWorld().spawnEntity(areaEffectCloudEntity);
        }

    }

    public boolean isIgnited() {
        return (Boolean)this.dataTracker.get(IGNITED);
    }

    static {
        FUSE_SPEED = DataTracker.registerData(ScorcherEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CHARGED = DataTracker.registerData(ScorcherEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        IGNITED = DataTracker.registerData(ScorcherEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    static class ScorcherShootGoal extends Goal {
        private final ScorcherEntity scorcher;
        private int fireballsFired;
        private int fireballCooldown;
        private int targetNotVisibleTicks;

        public ScorcherShootGoal(ScorcherEntity scorcher) {
            this.scorcher = scorcher;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.scorcher.getTarget();
            return livingEntity != null && livingEntity.isAlive() && this.scorcher.canTarget(livingEntity);
        }

        public void start() {
            this.fireballsFired = 0;
        }

        public void stop() {
            this.targetNotVisibleTicks = 0;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            --this.fireballCooldown;
            LivingEntity livingEntity = this.scorcher.getTarget();
            if (livingEntity != null) {
                boolean bl = this.scorcher.getVisibilityCache().canSee(livingEntity);
                if (bl) {
                    this.targetNotVisibleTicks = 0;
                } else {
                    ++this.targetNotVisibleTicks;
                }

                double d = this.scorcher.squaredDistanceTo(livingEntity);
                if (d < (double)4.0F) {
                    if (!bl) {
                        return;
                    }

                    if (this.fireballCooldown <= 0) {
                        this.fireballCooldown = 20;
                        this.scorcher.tryAttack(livingEntity);
                    }

                    this.scorcher.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), (double)1.0F);
                } else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
                    double e = livingEntity.getX() - this.scorcher.getX();
                    double f = livingEntity.getBodyY((double)0.5F) - this.scorcher.getBodyY((double)0.5F);
                    double g = livingEntity.getZ() - this.scorcher.getZ();
                    if (this.fireballCooldown <= 0) {
                        ++this.fireballsFired;
                        if (this.fireballsFired == 1) {
                            this.fireballCooldown = 60;
                        } else if (this.fireballsFired <= 4) {
                            this.fireballCooldown = 6;
                        } else {
                            this.fireballCooldown = 100;
                            this.fireballsFired = 0;
                        }

                        if (this.fireballsFired > 1) {
                            double h = Math.sqrt(Math.sqrt(d)) * (double)0.5F;
                            if (!this.scorcher.isSilent()) {
                                this.scorcher.getWorld().syncWorldEvent((PlayerEntity)null, 1018, this.scorcher.getBlockPos(), 0);
                            }

                            for(int i = 0; i < 1; ++i) {
                                Vec3d vec3d = new Vec3d(this.scorcher.getRandom().nextTriangular(e, 2.297 * h), f, this.scorcher.getRandom().nextTriangular(g, 2.297 * h));
                                SmallFireballEntity smallFireballEntity = new SmallFireballEntity(this.scorcher.getWorld(), this.scorcher, vec3d.normalize());
                                smallFireballEntity.setPosition(smallFireballEntity.getX(), this.scorcher.getBodyY((double)0.5F) + (double)0.5F, smallFireballEntity.getZ());
                                this.scorcher.getWorld().spawnEntity(smallFireballEntity);
                            }
                        }
                    }

                    this.scorcher.getLookControl().lookAt(livingEntity, 10.0F, 10.0F);
                } else if (this.targetNotVisibleTicks < 5) {
                    this.scorcher.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), (double)1.0F);
                }

                super.tick();
            }
        }

        private double getFollowRange() {
            return this.scorcher.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        }
    }

    public class ScorcherIgniteGoal extends Goal {
        private final ScorcherEntity scorcher;
        @Nullable
        private LivingEntity target;

        public ScorcherIgniteGoal(ScorcherEntity scorcher) {
            this.scorcher = scorcher;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.scorcher.getTarget();
            return this.scorcher.getFuseSpeed() > 0 || livingEntity != null && this.scorcher.squaredDistanceTo(livingEntity) < (double)9.0F;
        }

        public void start() {
            this.target = this.scorcher.getTarget();
        }

        public boolean shouldRunEveryTick() {
            return true;
        }
    }
}