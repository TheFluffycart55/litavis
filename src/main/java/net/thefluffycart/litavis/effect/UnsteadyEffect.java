package net.thefluffycart.litavis.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class UnsteadyEffect extends StatusEffect {
    public UnsteadyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        for (int x = 0; x <= 360; x++)
        {
            float currentYaw = entity.getYaw();
            float newYaw = currentYaw + (x/349);
            entity.setYaw(newYaw);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}