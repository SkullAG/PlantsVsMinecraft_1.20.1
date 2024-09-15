package net.skullag.plantsvsminecraft.entity.client.potatomine;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.skullag.plantsvsminecraft.entity.animation.PotatoMineAnimations;
import net.skullag.plantsvsminecraft.entity.custom.PotatoMineEntity;

public class PotatoMineModel<T extends PotatoMineEntity> extends SinglePartEntityModel<T> {
	private final ModelPart foot;

	public PotatoMineModel(ModelPart root) {
		this.foot = root.getChild("Foot");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Foot = modelPartData.addChild("Foot", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Potato = Foot.addChild("Potato", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -5.0F, -5.0F, 10.0F, 5.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-4.0F, -6.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(30, 5).cuboid(-4.0F, -4.0F, -6.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 0).cuboid(-4.0F, -4.0F, 5.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(24, 16).cuboid(-6.0F, -4.0F, -4.0F, 1.0F, 4.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 24).cuboid(5.0F, -4.0F, -4.0F, 1.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Dirt = Potato.addChild("Dirt", ModelPartBuilder.create().uv(34, 15).cuboid(-4.0F, -1.0F, -7.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 15).cuboid(-4.0F, -1.0F, 6.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-5.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-6.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 28).cuboid(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(10, 28).cuboid(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(4.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData AlarmOff = Foot.addChild("AlarmOff", ModelPartBuilder.create().uv(0, 4).cuboid(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData AlarmOn = AlarmOff.addChild("AlarmOn", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

		ModelPartData TinyDirt = Foot.addChild("TinyDirt", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, -1.0F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-1.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-0.5F, -1.0F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.updateAnimation(entity.idleTinyState, PotatoMineAnimations.IdleTiny, ageInTicks, 1);
		this.updateAnimation(entity.growingState, PotatoMineAnimations.Grow, ageInTicks, 1);
		this.updateAnimation(entity.idleGrownState, PotatoMineAnimations.IdleGrown, ageInTicks, 1);
		this.updateAnimation(entity.explodingState, PotatoMineAnimations.Explode, ageInTicks, 1);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		foot.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return foot;
	}
}