package net.skullag.plantsvsminecraft.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.ModEntities;

public class ModModelLayers {
    public static final EntityModelLayer SUNFLOWER =
            new EntityModelLayer(new Identifier(PlantsVsMinecraft.MOD_ID, "sunflower"), "main");

    public static void registerModelLayers () {
        EntityRendererRegistry.register(ModEntities.SUNFLOWER, SunFlowerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SUNFLOWER, SunFlowerModel::getTexturedModelData);
    }
}
