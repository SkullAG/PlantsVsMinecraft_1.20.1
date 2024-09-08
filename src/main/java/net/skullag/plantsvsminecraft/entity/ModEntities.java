package net.skullag.plantsvsminecraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.custom.PeaShooterEntity;
import net.skullag.plantsvsminecraft.entity.custom.SunFlowerEntity;
import net.skullag.plantsvsminecraft.entity.custom.WallNutEntity;
import net.skullag.plantsvsminecraft.entity.custom.projectiles.PeaProjectileEntity;

public class ModEntities {
    public static final EntityType<SunFlowerEntity> SUNFLOWER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PlantsVsMinecraft.MOD_ID, "sunflower"), SunFlowerEntity.builder().build());
    public static final EntityType<PeaShooterEntity> PEASHOOTER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PlantsVsMinecraft.MOD_ID, "peashooter"), PeaShooterEntity.builder().build());
    public static final EntityType<WallNutEntity> WALLNUT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PlantsVsMinecraft.MOD_ID, "wallnut"), WallNutEntity.builder().build());

    public static final EntityType<PeaProjectileEntity> PEA_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(PlantsVsMinecraft.MOD_ID, "pea_projectile"), PeaProjectileEntity.builder().build());

        public static void registerEntities () {
            PlantsVsMinecraft.LOGGER.info("Registering Mod Entities for " + PlantsVsMinecraft.MOD_ID);

            FabricDefaultAttributeRegistry.register(SUNFLOWER, SunFlowerEntity.createSunFlowerAttributes());
            FabricDefaultAttributeRegistry.register(PEASHOOTER, PeaShooterEntity.createPeaShooterAttributes());
            FabricDefaultAttributeRegistry.register(WALLNUT, WallNutEntity.createWallNutAttributes());
        }
}
