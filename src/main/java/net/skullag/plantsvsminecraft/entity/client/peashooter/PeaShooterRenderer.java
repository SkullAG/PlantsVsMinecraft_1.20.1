package net.skullag.plantsvsminecraft.entity.client.peashooter;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.client.ModModelLayers;
import net.skullag.plantsvsminecraft.entity.custom.PeaShooterEntity;

public class PeaShooterRenderer  extends MobEntityRenderer<PeaShooterEntity, PeaShooterModel<PeaShooterEntity>> {
    private static final Identifier Texture = new Identifier(PlantsVsMinecraft.MOD_ID, "textures/entity/peashooter.png");

    public PeaShooterRenderer(EntityRendererFactory.Context context) {
        super(context, new PeaShooterModel<>(context.getPart(ModModelLayers.PEASHOOTER)), 0.4f);
    }

    @Override
    public Identifier getTexture(PeaShooterEntity entity) {
        return Texture;
    }

    @Override
    public void render(PeaShooterEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
