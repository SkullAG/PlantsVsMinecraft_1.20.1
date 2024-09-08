package net.skullag.plantsvsminecraft.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;

public class ModSounds {
    public static final SoundEvent PEA_THROW_SOUND = registerSoundEvent("pea_throw");
    public static final SoundEvent PEA_HIT_SOUND = registerSoundEvent("pea_hit");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(PlantsVsMinecraft.MOD_ID, name);

        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        PlantsVsMinecraft.LOGGER.info("Registering Mod Sounds for " + PlantsVsMinecraft.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> addItemsToItemGroup(entries, /*list of items*/));
    }
}
