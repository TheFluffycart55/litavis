package net.thefluffycart.litavis;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Litavis implements ModInitializer {

	public static final String MOD_ID = "litavis";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}