package net.thefluffycart.litavis.world.biome;

import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.of(Litavis.MOD_ID, "overworld"), 25));

//        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Litavis.MOD_ID, ModMaterialRules.makeRules());
    }
}