package net.skullag.plantsvsminecraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.custom.SunFlowerEntity;

public class ModEntities {
    public static final EntityType<SunFlowerEntity> SUNFLOWER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PlantsVsMinecraft.MOD_ID, "sunflower"), SunFlowerEntity.builder().build());

        public static void registerEntities () {
            FabricDefaultAttributeRegistry.register(SUNFLOWER, SunFlowerEntity.createSunFlowerAttributes());
        }
}
