package net.thefluffycart.litavis.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CASSAVA = new FoodComponent.Builder().nutrition(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200 ),0.15f).build();
}
