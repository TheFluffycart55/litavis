package net.thefluffycart.litavis.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thefluffycart.litavis.Litavis;

public class CopperGripItem extends Item {
    public static final int MAX_DAMAGE = 200;
    public CopperGripItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isClient && (entity instanceof PlayerEntity player)) {
            if (player.getEquippedStack(EquipmentSlot.OFFHAND).isOf(this)) {

                if (!player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE).hasModifier(Identifier.of(Litavis.MOD_ID, "copper_grip_placerange")))
                player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE)
                        .addPersistentModifier(new EntityAttributeModifier(Identifier.of(Litavis.MOD_ID, "copper_grip_placerange"), 5.0, EntityAttributeModifier.Operation.ADD_VALUE));
            }
            else {
                player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE)
                        .removeModifier(Identifier.of(Litavis.MOD_ID, "copper_grip_placerange"));
            }

        }

        if (!world.isClient && (entity instanceof PlayerEntity player)) {
            if (player.getEquippedStack(EquipmentSlot.OFFHAND).isOf(this)) {
                if (!player.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE).hasModifier(Identifier.of(Litavis.MOD_ID, "copper_grip_attackrange")))
                    player.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE)
                            .addPersistentModifier(new EntityAttributeModifier(Identifier.of(Litavis.MOD_ID, "copper_grip_attackrange"), 2.5, EntityAttributeModifier.Operation.ADD_VALUE));
            }
            else {
                player.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE)
                        .removeModifier(Identifier.of(Litavis.MOD_ID, "copper_grip_attackrange"));
            }

        }
        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    }
}
