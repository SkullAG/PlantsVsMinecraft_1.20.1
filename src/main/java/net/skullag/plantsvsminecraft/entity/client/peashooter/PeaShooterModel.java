package net.skullag.plantsvsminecraft.entity.client.peashooter;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.skullag.plantsvsminecraft.entity.animation.PeaShooterAnimations;
import net.skullag.plantsvsminecraft.entity.custom.PeaShooterEntity;

public class PeaShooterModel<T extends PeaShooterEntity> extends SinglePartEntityModel<T> {
	private final ModelPart foot;
	private final ModelPart head;

	public PeaShooterModel(ModelPart root) {
		this.foot = root.getChild("Foot");
		this.head = foot.getChild("Stem").getChild("Stem2").getChild("Neck").getChild("Head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Foot = modelPartData.addChild("Foot", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Leaf = Foot.addChild("Leaf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = Leaf.addChild("cube_r1", ModelPartBuilder.create().uv(44, 58).cuboid(-5.0F, -1.0F, 0.0F, 5.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -3.1416F, -0.7854F, 3.1416F));

		ModelPartData Leaf2 = Foot.addChild("Leaf2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r2 = Leaf2.addChild("cube_r2", ModelPartBuilder.create().uv(44, 58).cuboid(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8961F, -0.5F, 2.0279F, 0.0F, 1.7453F, 0.0F));

		ModelPartData Leaf3 = Foot.addChild("Leaf3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r3 = Leaf3.addChild("cube_r3", ModelPartBuilder.create().uv(44, 58).cuboid(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.2043F, -0.5F, 1.4942F, 0.0F, -0.3491F, 0.0F));

		ModelPartData Stem = Foot.addChild("Stem", ModelPartBuilder.create().uv(0, 56).cuboid(-0.5F, -7.0F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		ModelPartData Stem2 = Stem.addChild("Stem2", ModelPartBuilder.create().uv(0, 51).cuboid(-0.5F, -3.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.5F, 0.0F, 0.4363F, 0.0F, 0.0F));

		ModelPartData Neck = Stem2.addChild("Neck", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

		ModelPartData Head = Neck.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -9.5F, -6.0F, 8.0F, 8.0F, 10.0F, new Dilation(0.0F))
		.uv(20, 19).cuboid(-3.0F, -1.5F, -5.0F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 18).cuboid(-3.0F, -10.5F, -5.0F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(18, 28).cuboid(-5.0F, -8.5F, -5.0F, 1.0F, 6.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 27).cuboid(4.0F, -8.5F, -5.0F, 1.0F, 6.0F, 8.0F, new Dilation(0.0F))
		.uv(36, 40).cuboid(-3.0F, -8.5F, 4.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 28).cuboid(-3.0F, -8.0F, -7.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -6.5F, -8.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData NeckRing = Head.addChild("NeckRing", ModelPartBuilder.create().uv(40, 0).cuboid(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Mouth = Head.addChild("Mouth", ModelPartBuilder.create().uv(40, 19).cuboid(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.5F, -9.0F));

		ModelPartData HeadLeaf = Head.addChild("HeadLeaf", ModelPartBuilder.create().uv(26, 0).cuboid(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.5F, 4.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData Helmet = Head.addChild("Helmet", ModelPartBuilder.create().uv(6, 51).cuboid(-2.5F, -9.5F, -3.5F, 5.0F, 7.0F, 6.0F, new Dilation(3.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Pea2 = Head.addChild("Pea2", ModelPartBuilder.create().uv(10, 20).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

		ModelPartData Pea = Stem.addChild("Pea", ModelPartBuilder.create().uv(10, 20).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(PeaShooterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.updateAnimation(entity.idleState, PeaShooterAnimations.idle, ageInTicks, 1);
		this.updateAnimation(entity.shooting, PeaShooterAnimations.shoot, ageInTicks, 1);
		this.updateAnimation(entity.nutrientState, PeaShooterAnimations.nutrients, ageInTicks, 1);
	}


	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30, 30);
		headPitch = MathHelper.clamp(headPitch, -25, 45);

		this.head.pitch = headPitch * (float) (Math.PI / 180.0);
		this.head.yaw = headYaw * (float) (Math.PI / 180.0);
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