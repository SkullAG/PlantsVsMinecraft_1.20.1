package net.skullag.plantsvsminecraft.damagetype;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModDamageTypes {

    public static final RegistryKey<DamageType> THROWNCROP = registerDamageType("thrown_crop");

    private static RegistryKey<DamageType> registerDamageType(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(PlantsVsMinecraft.MOD_ID, name));
    }

    public static void registerModDamageTypes() {
        PlantsVsMinecraft.LOGGER.info("Registering Mod Damage Types for " + PlantsVsMinecraft.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> addItemsToItemGroup(entries, /*list of items*/));
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key, @Nullable Entity source, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(key), source, attacker);
    }
}
