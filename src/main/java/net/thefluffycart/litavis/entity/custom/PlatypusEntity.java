package net.thefluffycart.litavis.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.variant.PlatypusVariant;
import net.thefluffycart.litavis.item.ModItems;
import net.thefluffycart.litavis.util.ModTags;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;

public class PlatypusEntity extends WaterCreatureEntity implements AngledModelEntity, Bucketable {
    private static final TrackedData<Boolean> FROM_BUCKET;
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT = DataTracker.registerData(PlatypusEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public PlatypusEntity(EntityType<? extends PlatypusEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new PlatypusEntity.PlatypusMoveControl(this);
        this.lookControl = new PlatypusEntity.PlatypusLookControl(this, 20);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DOOR_OPEN, -1.0F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1));
        this.goalSelector.add(1, new TemptGoal(this, 0.75, (stack) -> stack.isIn(ModTags.Items.PLATYPUS_FOOD), false));
        this.goalSelector.add(2, new SwimAroundGoal(this, 0.5, 1));
        this.goalSelector.add(3, new WanderAroundGoal(this, 1));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1, 1f));
        this.goalSelector.add(2, new AttackGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(1, new ActiveTargetGoal(this, FishEntity.class, false));
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    public static DefaultAttributeContainer.Builder createPlatypusAttribute() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }

    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isFromBucket() && !this.hasCustomName();
    }

    @Override
    protected void tickWaterBreathingAir(int air) {
        if (this.isAlive() && !this.isInsideWaterOrBubbleColumn()) {
            this.setAir(air - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
            }
        } else {
            this.setAir(300);
        }

    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ModTags.Items.PLATYPUS_FOOD);
    }

    public PlatypusEntity createChild(ServerWorld world, WaterCreatureEntity entity) {
        return ModEntities.PLATYPUS.create(world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(FROM_BUCKET, false);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public PlatypusVariant getVariant() {
        return PlatypusVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(PlatypusVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        PlatypusVariant variant = PlatypusVariant.BASE;
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public boolean isFromBucket() {
        return (Boolean)this.dataTracker.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        nbt.putBoolean("FromBucket", this.isFromBucket());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        this.setFromBucket(nbt.getBoolean("FromBucket"));
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.TROPICAL_FISH_BUCKET)) {
            if (!this.getWorld().isClient) {
                buffPlayer(player);
                player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        }
        else if (itemStack.isIn(ModTags.Items.PLATYPUS_FOOD))
        {
            if (!this.getWorld().isClient) {
                buffPlayer(player);
                itemStack.decrementUnlessCreative(1, player);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        }

        else if (itemStack.isOf(Items.GLOW_INK_SAC))
        {
            if (!this.getWorld().isClient) {
                setVariant(PlatypusVariant.PERRY);
                itemStack.decrementUnlessCreative(1, player);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        }

        else {
            return (ActionResult)Bucketable.tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
        }
    }

    public void buffPlayer(PlayerEntity player) {
        StatusEffectInstance statusEffectInstance = player.getStatusEffect(StatusEffects.WATER_BREATHING);
        if (statusEffectInstance == null || statusEffectInstance.isDurationBelow(2399)) {
            int i = statusEffectInstance != null ? statusEffectInstance.getDuration() : 0;
            int j = Math.min(2400, 100 + i);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, j, 0), this);
        }

        player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
    }

    public int getMaxLookPitchChange() {
        return 1;
    }

    public int getMaxHeadRotation() {
        return 1;
    }

    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        }
        else if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add((double) 0.0F, -0.005, (double) 0.0F));
            }
        }
           else {
            super.travel(movementInput);
        }

    }

    public boolean canSpawn(WorldView world) {
        return world.doesNotIntersectEntities(this);
    }

    protected EntityNavigation createNavigation(World world) {
        return new AmphibiousSwimNavigation(this, world);
    }

    static class PlatypusMoveControl extends AquaticMoveControl {
        private final PlatypusEntity platypus;

        public PlatypusMoveControl(PlatypusEntity platypus) {
            super(platypus, 85, 10, 0.1F, 0.5F, false);
            this.platypus = platypus;
        }
    }

    class PlatypusLookControl extends YawAdjustingLookControl {
        public PlatypusLookControl(final PlatypusEntity platypus, final int yawAdjustThreshold) {
            super(platypus, yawAdjustThreshold);
        }
    }

    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbt) -> {
            nbt.putInt("Variant", this.getVariant().getId());
        });
    }

    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
        this.setVariant(PlatypusVariant.byId(nbt.getInt("Variant")));
    }

//    public ActionResult interactMob(PlayerEntity player, Hand hand) {
//        return (ActionResult)Bucketable.tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
//    }

    @Override
    public ItemStack getBucketItem() {
        return ModItems.PLATYPUS_BUCKET.getDefaultStack();
    }

    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    protected boolean hasSelfControl() {
        return true;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_FISH_SWIM;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        int i = world.getSeaLevel();
        int j = i - 2;
        return pos.getY() >= j && pos.getY() <= i && world.getFluidState(pos.down()).isIn(FluidTags.WATER) && world.getBlockState(pos.up()).isOf(Blocks.WATER);
    }

    static {
        FROM_BUCKET = DataTracker.registerData(PlatypusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Override
    public Map<String, Vector3f> getModelAngles() {
        return Map.of();
    }
}
