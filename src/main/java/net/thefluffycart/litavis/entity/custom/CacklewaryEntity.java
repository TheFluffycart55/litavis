package net.thefluffycart.litavis.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.variant.CacklewaryVariant;
import net.thefluffycart.litavis.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CacklewaryEntity extends AbstractHorseEntity {
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(CacklewaryEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public CacklewaryEntity(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2));
        this.goalSelector.add(4, new FollowParentGoal(this, (double)1.25F));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        if (this.shouldAmbientStand()) {
            this.goalSelector.add(9, new AmbientStandGoal(this));
        }
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

    public static DefaultAttributeContainer.Builder createDodoAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 3)
                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.TIRIM_BERRIES);
    }

//    @Nullable
//    @Override
//    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
//        return ModEntities.CACKLEWARY.create(world);
//    }

    /* SOUNDS */

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PARROT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_GHAST_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PANDA_DEATH;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public CacklewaryVariant getVariant() {
        return CacklewaryVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(CacklewaryVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        CacklewaryVariant variant = Util.getRandom(CacklewaryVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    public boolean isLurableItem(ItemStack stack) {
        return stack.isOf(ModItems.TIRIM_BERRIES);
    }

    /* RIDEABLE */
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        boolean bl;
        boolean bl2 = bl = !this.isBaby() && this.isTame() && player.shouldCancelInteraction();
        if (this.hasPassengers() || bl) {
            return super.interactMob(player, hand);
        }
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isEmpty()) {
            if (this.isLurableItem(itemStack)) {
                return this.interactHorse(player, itemStack);
            }
            if (!this.isTame()) {
                this.playAngrySound();
                return ActionResult.success(this.getWorld().isClient);
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    protected Vec3d getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        return super.getPassengerAttachmentPos(passenger, dimensions, scaleFactor).add(new Vec3d(0.0, -0.5 * (double)scaleFactor,
                0 * (double)scaleFactor).rotateY(-this.getYaw() * ((float)Math.PI / 180)));
    }

    //HORSE OVERRIDES
    public boolean isEatingGrass() {
        return false;
    }
}