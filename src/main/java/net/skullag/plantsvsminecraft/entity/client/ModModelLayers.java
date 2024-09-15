package net.skullag.plantsvsminecraft.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.ModEntities;
import net.skullag.plantsvsminecraft.entity.client.peashooter.PeaShooterModel;
import net.skullag.plantsvsminecraft.entity.client.peashooter.PeaShooterRenderer;
import net.skullag.plantsvsminecraft.entity.client.potatomine.PotatoMineModel;
import net.skullag.plantsvsminecraft.entity.client.potatomine.PotatoMineRenderer;
import net.skullag.plantsvsminecraft.entity.client.sunflower.SunFlowerModel;
import net.skullag.plantsvsminecraft.entity.client.sunflower.SunFlowerRenderer;
import net.skullag.plantsvsminecraft.entity.client.wallnut.WallNutModel;
import net.skullag.plantsvsminecraft.entity.client.wallnut.WallNutRenderer;

public class ModModelLayers {
    public static final EntityModelLayer SUNFLOWER =
            new EntityModelLayer(new Identifier(PlantsVsMinecraft.MOD_ID, "sunflower"), "main");

    public static final EntityModelLayer PEASHOOTER =
            new EntityModelLayer(new Identifier(PlantsVsMinecraft.MOD_ID, "peashooter"), "main");

    public static final EntityModelLayer WALLNUT =
            new EntityModelLayer(new Identifier(PlantsVsMinecraft.MOD_ID, "wallnut"), "main");

    public static final EntityModelLayer POTATOMINE =
            new EntityModelLayer(new Identifier(PlantsVsMinecraft.MOD_ID, "potatomine"), "main");

    public static void registerModelLayers () {
        EntityRendererRegistry.register(ModEntities.SUNFLOWER, SunFlowerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SUNFLOWER, SunFlowerModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PEASHOOTER, PeaShooterRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(PEASHOOTER, PeaShooterModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.WALLNUT, WallNutRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(WALLNUT, WallNutModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.POTATOMINE, PotatoMineRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(POTATOMINE, PotatoMineModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PEA_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
