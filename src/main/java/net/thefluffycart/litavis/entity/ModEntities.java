package net.thefluffycart.litavis.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.*;

public class ModEntities {
    //BURROW SETUP
    public static final EntityType<BurrowEntity> BURROW = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Litavis.MOD_ID, "burrow"),
            EntityType.Builder.create(BurrowEntity::new, SpawnGroup.MONSTER).dimensions(0.8f, 1.5f).build());

    public static final EntityType<ScorcherEntity> SCORCHER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Litavis.MOD_ID, "scorcher"),
            EntityType.Builder.create(ScorcherEntity::new, SpawnGroup.MONSTER).dimensions(2f, 1.5f).build());

    public static final EntityType<PlatypusEntity> PLATYPUS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Litavis.MOD_ID, "platypus"),
            EntityType.Builder.create(PlatypusEntity::new, SpawnGroup.WATER_AMBIENT).dimensions(0.8f, 0.8f).build());

    public static final EntityType<EarthChargeEntity> EARTH_CHARGE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Litavis.MOD_ID, "earth_charge"),
            FabricEntityTypeBuilder.<EarthChargeEntity>create(SpawnGroup.MISC, EarthChargeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5F)).trackRangeBlocks(2)
                    .trackedUpdateRate(10).build());
    
    public static void registerModEntities()
    {
        Litavis.LOGGER.info("Registering Mod Entities for " + Litavis.MOD_ID);
    }
}
