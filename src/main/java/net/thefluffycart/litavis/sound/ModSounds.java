package net.thefluffycart.litavis.sound;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;

public class ModSounds {

    //UPDATING SOUNDS FOR 2.1, BURROW SOUNDS, EARTH CHARGE SOUNDS, AND TRIPSLATE
    public static final SoundEvent BURROW_IDLE = registerSoundEvent("burrow_idle");
    public static final SoundEvent BURROW_HIT = registerSoundEvent("burrow_hit");
    public static final SoundEvent FLUFFY_SQUEAKS = registerSoundEvent("fluffy_squeaks");
    public static final SoundEvent TRIPSLATE_COLLAPSE = registerSoundEvent("tripslate_collapse");

    public static final SoundEvent ECHOES = registerSoundEvent("echoes");
    public static final RegistryKey<JukeboxSong> ECHOES_KEY = of("echoes");


    private static RegistryKey<JukeboxSong> of(String name) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Litavis.MOD_ID, name));
    }

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(Litavis.MOD_ID, name),
                SoundEvent.of(Identifier.of(Litavis.MOD_ID, name)));
    }

    public static void registerSounds() {
        Litavis.LOGGER.info("Registering Mod Sounds for " + Litavis.MOD_ID);
    }
}
