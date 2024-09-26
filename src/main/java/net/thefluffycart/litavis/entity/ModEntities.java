package net.thefluffycart.litavis.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.custom.MoleEntity;

public class ModEntities {

    public static final EntityType<MoleEntity> MOLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Litavis.MOD_ID, "mole"),
            EntityType.Builder.create(MoleEntity::new, SpawnGroup.CREATURE).dimensions(1f, 0.5f).build());

    public static final EntityType<BurrowEntity> BURROW = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Litavis.MOD_ID, "burrow"),
            EntityType.Builder.create(BurrowEntity::new, SpawnGroup.MONSTER).dimensions(1f, 2f).build());

    public static void registerModEntities()
    {
        Litavis.LOGGER.info("Registering Mod Entities for " + Litavis.MOD_ID);
    }
}