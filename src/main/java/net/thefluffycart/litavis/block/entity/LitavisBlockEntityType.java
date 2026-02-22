package net.thefluffycart.litavis.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;

public class LitavisBlockEntityType {
    public static BlockEntityType<BurrowHeadBlockEntity> BURROW_HEAD;
    public static BlockEntityType<LitavisBrushableBlockEntity> SUSPICIOUS_RED_SAND;

    public static void register() {
        BURROW_HEAD = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of("litavis", "burrow_head"),
                FabricBlockEntityTypeBuilder.create(BurrowHeadBlockEntity::new,
                        ModBlocks.BURROW_HEAD).build(null));
    }
}
