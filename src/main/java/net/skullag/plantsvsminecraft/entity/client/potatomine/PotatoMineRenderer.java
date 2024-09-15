package net.skullag.plantsvsminecraft.entity.client.potatomine;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.client.ModModelLayers;
import net.skullag.plantsvsminecraft.entity.custom.PotatoMineEntity;

public class PotatoMineRenderer extends MobEntityRenderer<PotatoMineEntity, PotatoMineModel<PotatoMineEntity>> {
    private static final Identifier Texture = new Identifier(PlantsVsMinecraft.MOD_ID, "textures/entity/potatomine.png");

    public PotatoMineRenderer(EntityRendererFactory.Context context) {
        this(context, ModModelLayers.POTATOMINE);
    }

    public PotatoMineRenderer(EntityRendererFactory.Context context, EntityModelLayer layer) {
        super(context, new PotatoMineModel<>(context.getPart(ModModelLayers.POTATOMINE)), 0);
    }

    @Override
    public Identifier getTexture(PotatoMineEntity entity) {
        return Texture;
    }

    @Override
    public void render(PotatoMineEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}