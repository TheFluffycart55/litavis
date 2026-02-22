package net.thefluffycart.litavis.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.thefluffycart.litavis.entity.variant.BurrowVariant;
import net.thefluffycart.litavis.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class BurrowEntity extends HostileEntity {
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(BurrowEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState dashAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    private int chargeAnimationTimeout = 0;
    private int dashAnimationTimeout = 0;
    public static boolean doIdle = true;
    public static boolean doCharge = false;
    public static boolean doDash = false;
    private static final TrackedData<Byte> BURROW_FLAGS = DataTracker.registerData(BurrowEntity.class, TrackedDataHandlerRegistry.BYTE);

    public BurrowEntity(EntityType<? extends BurrowEntity> entityType, World world) {
        super((EntityType<? extends HostileEntity>)entityType, world);
        this.experiencePoints = 15;
    }
    private State state = State.IDLE;

    public enum State {
        IDLE,
        CHARGE,
        DASH,
        BURROWED
    }

    public void setState(State newState) {
        if (state != newState)
        {
            this.state = newState;
            switch (this.state) {
                case IDLE -> {
                    doIdle = true;
                    doCharge = false;
                    doDash = false;
                    idleAnimationTimeout = 0;
                    idleAnimationState.start(this.age);
                }
                case CHARGE -> {
                    doIdle = false;
                    doCharge = true;
                    doDash = false;
                    chargeAnimationTimeout = 0;
                    chargeAnimationState.start(this.age);
                }
                case DASH -> {
                    doIdle = false;
                    doCharge = false;
                    doDash = true;
                    dashAnimationTimeout = 0;
                    dashAnimationState.start(this.age);
                }
            }
        }
    }

    public State getState() {
        return this.state;
    }


    private void setupAnimationStates() {
            if(doIdle)
        {
            if(this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 40;
                this.idleAnimationState.start(this.age);
            } else {
                --this.idleAnimationTimeout;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

        //BURROW GOALS
        protected void initGoals() {
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        //this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.65));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, GoatEntity.class, true).setMaxTimeWithoutVisibility(300));
    }

    public static DefaultAttributeContainer.Builder createburrowAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double)0.285F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 32f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 20f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20f);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        super.initDataTracker(builder);
        builder.add(BURROW_FLAGS, (byte)0);
    }

    @Override
    public boolean hurtByWater() {
        return true;
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.BURROW_HIT;
    }
    public void playAmbientSound() {
        if (this.getTarget() == null || !this.isOnGround()) {
            this.getWorld().playSoundFromEntity(this, ModSounds.BURROW_IDLE, this.getSoundCategory(), 1.0F, 1.0F);
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BURROW_HIT;
    }

    //BURROW VARIANTS

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public BurrowVariant getVariant() {
        return BurrowVariant.byId(this.getTypeVariant());
    }

    public void setVariant(BurrowVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId());
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        BurrowVariant variant = getWeightedRandomVariant();
        setVariant(variant);
         return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    private BurrowVariant getWeightedRandomVariant() {
        float[] weights = {75,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,5};
        BurrowVariant[] variants = BurrowVariant.values();
        int totalWeight = 0;

        for (float weight : weights) {
            totalWeight += weight;
        }
        int randomWeight = this.random.nextInt(totalWeight);

        int cumulativeWeight = 0;
        for (int i = 0; i < variants.length; i++) {
            cumulativeWeight += (int) weights[i];
            if (randomWeight < cumulativeWeight) {
                return variants[i];
            }
        }
        return variants[0];
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    //EARTH-AND-WATER REF
}