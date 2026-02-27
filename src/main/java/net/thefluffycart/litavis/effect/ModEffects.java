package net.thefluffycart.litavis.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> IGNITION = registerStatusEffect("ignition",
            new IgnitionEffect(StatusEffectCategory.HARMFUL, 0xb85f30)
                    .addAttributeModifier(EntityAttributes.GENERIC_BURNING_TIME,
                            Identifier.of(Litavis.MOD_ID, "ignition"), 1f,
                            EntityAttributeModifier.Operation.ADD_VALUE));

    public static final RegistryEntry<StatusEffect> UNSTEADY = registerStatusEffect("unsteady",
            new UnsteadyEffect(StatusEffectCategory.HARMFUL, 0xb85f30)
                    .addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH,
                            Identifier.of(Litavis.MOD_ID, "unsteady"), -0.5f,
                            EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED,
                            Identifier.of(Litavis.MOD_ID, "unsteady"), -0.5f,
                            EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(Litavis.MOD_ID, "unsteady"), -1f,
                            EntityAttributeModifier.Operation.ADD_VALUE));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Litavis.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Litavis.LOGGER.info("Registering Mod Effects for " + Litavis.MOD_ID);
    }
}
