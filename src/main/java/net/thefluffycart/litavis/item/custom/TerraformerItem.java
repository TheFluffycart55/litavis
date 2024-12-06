package net.thefluffycart.litavis.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TerraformerItem extends ToolItem {
    public static final Identifier BASE_REACH_RANGE_MODIFIER_ID = Identifier.ofVanilla("base_player_block_interaction_range");

    public TerraformerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }


    public static AttributeModifiersComponent createAttributeModifiers(int baseReachRange, float baseAttackRange) {
        return AttributeModifiersComponent.builder().add(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(BASE_REACH_RANGE_MODIFIER_ID, (float)baseReachRange, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.OFFHAND).build();
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }
}


