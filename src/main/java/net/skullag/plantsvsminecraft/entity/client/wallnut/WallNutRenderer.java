package net.skullag.plantsvsminecraft.entity.client.wallnut;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.client.ModModelLayers;
import net.skullag.plantsvsminecraft.entity.custom.WallNutEntity;

public class WallNutRenderer extends MobEntityRenderer<WallNutEntity, WallNutModel<WallNutEntity>> {
    private static final Identifier Texture = new Identifier(PlantsVsMinecraft.MOD_ID, "textures/entity/wallnut.png");

    public WallNutRenderer(EntityRendererFactory.Context context) {
        super(context, new WallNutModel<>(context.getPart(ModModelLayers.WALLNUT)), 0.4f);
    }

    @Override
    public Identifier getTexture(WallNutEntity entity) {
        return Texture;
    }

    @Override
    public void render(WallNutEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}