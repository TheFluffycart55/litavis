package net.thefluffycart.litavis.entity.custom;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.thefluffycart.litavis.entity.ai.inventory.SimpleInventoryWrapper;
import org.jetbrains.annotations.Nullable;

public class CopperGolemEntity extends TameableEntity implements InventoryProvider  {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();

    public CopperGolemEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 120;
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

    public void writeCustomDataToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeCustomDataToNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory, registries);
    }


    public void readCustomDataFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readCustomDataFromNbt(nbt);
        Inventories.readNbt(nbt, this.inventory, registries);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder createCopperGolemAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, (double) 30f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double) 0.4f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, (double) 0.5f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, (double) 1.0F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, (double) 15.0F)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, (double) 1.0F);
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }


    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return null;
    }

    public interface InventoryProvider {
        DefaultedList<ItemStack> getItems();

        default boolean isEmpty() {
            for (ItemStack itemStack : getItems()) {
                if (!itemStack.isEmpty()) return false;
            }
            return true;
        }

        default int size() {
            return getItems().size();
        }

        default ItemStack getStack(int slot) {
            return getItems().get(slot);
        }

        default ItemStack removeStack(int slot, int amount) {
            return Inventories.splitStack(getItems(), slot, amount);
        }

        default ItemStack removeStack(int slot) {
            return Inventories.removeStack(getItems(), slot);
        }

        default void setStack(int slot, ItemStack stack) {
            getItems().set(slot, stack);
            if (!stack.isEmpty() && stack.getCount() > 64) {
                stack.setCount(64);
            }
        }

        default void clear() {
            getItems().clear();
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                    (syncId, playerInventory, playerEntity) -> new GenericContainerScreenHandler(
                            ScreenHandlerType.GENERIC_9X1, syncId, playerInventory,
                            new SimpleInventoryWrapper(this.getItems()), 1),
                    this.getDisplayName()
            ));
        }
        return ActionResult.SUCCESS;
    }
}