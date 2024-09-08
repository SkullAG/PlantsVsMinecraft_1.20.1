package net.skullag.plantsvsminecraft.entity.client.sunflower;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.client.ModModelLayers;
import net.skullag.plantsvsminecraft.entity.custom.SunFlowerEntity;

public class SunFlowerRenderer extends MobEntityRenderer<SunFlowerEntity, SunFlowerModel<SunFlowerEntity>> {
    private static final Identifier Texture = new Identifier(PlantsVsMinecraft.MOD_ID, "textures/entity/sunflower.png");

    public SunFlowerRenderer(EntityRendererFactory.Context context) {
        super(context, new SunFlowerModel<>(context.getPart(ModModelLayers.SUNFLOWER)), 0.4f);
    }

    @Override
    public Identifier getTexture(SunFlowerEntity entity) {
        return Texture;
    }

    @Override
    public void render(SunFlowerEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
