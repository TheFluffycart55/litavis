package net.thefluffycart.litavis.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

public class ModBoats {
    //BOATS
    public static final Identifier EUCALYPTUS_BOAT_ID = Identifier.of(Litavis.MOD_ID, "eucalyptus_boat");
    public static final Identifier EUCALYPTUS_CHEST_BOAT_ID = Identifier.of(Litavis.MOD_ID, "eucalyptus_chest_boat");

    public static final RegistryKey<TerraformBoatType> EUCALYPTUS_BOAT_KEY = TerraformBoatTypeRegistry.createKey(EUCALYPTUS_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType eucalyptusBoat = new TerraformBoatType.Builder()
                .item(ModItems.EUCALYPTUS_BOAT)
                .chestItem(ModItems.EUCALYPTUS_CHEST_BOAT)
                .planks(ModBlocks.EUCALYPTUS_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, EUCALYPTUS_BOAT_KEY, eucalyptusBoat);
    }
}