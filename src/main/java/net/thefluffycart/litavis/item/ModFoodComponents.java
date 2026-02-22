package net.thefluffycart.litavis.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent TIRIM_BERRY = new FoodComponent.Builder().nutrition(1).saturationModifier(0.05f).alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 300), 1)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300), 0.5f).build();

}