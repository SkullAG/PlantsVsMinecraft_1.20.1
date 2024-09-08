package net.skullag.plantsvsminecraft.entity.client.wallnut;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.skullag.plantsvsminecraft.entity.custom.WallNutEntity;

public class WallNutModel<T extends WallNutEntity> extends SinglePartEntityModel<T> {
	private final ModelPart foot;
	private final ModelPart normalFace;
	private final ModelPart brokenFace;
	private final ModelPart armature;
	private final ModelPart fullArmature;
	public WallNutModel(ModelPart root) {
		this.foot = root.getChild("Foot");
		this.normalFace = foot.getChild("NormalFace");
		this.brokenFace = foot.getChild("BrokenFace");
		this.armature = foot.getChild("Armature");
		this.fullArmature = armature.getChild("FullArmature");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Foot = modelPartData.addChild("Foot", ModelPartBuilder.create().uv(36, 0).cuboid(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
				.uv(40, 38).cuboid(-4.0F, -15.0F, -4.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F))
				.uv(60, 0).cuboid(-3.0F, -16.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
				.uv(0, 37).cuboid(-5.0F, -13.0F, -5.0F, 10.0F, 12.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 112).cuboid(-4.0F, -12.0F, -3.0F, 8.0F, 10.0F, 6.0F, new Dilation(0.0F))
				.uv(50, 57).cuboid(5.0F, -11.0F, -4.0F, 1.0F, 9.0F, 8.0F, new Dilation(0.0F))
				.uv(40, 48).cuboid(-6.0F, -11.0F, -4.0F, 1.0F, 9.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 19).cuboid(-7.0F, -8.0F, -2.0F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(6.0F, -8.0F, -2.0F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData BrokenFace = Foot.addChild("BrokenFace", ModelPartBuilder.create().uv(68, 13).cuboid(-5.0F, -13.0F, -5.0F, 10.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData NormalFace = Foot.addChild("NormalFace", ModelPartBuilder.create().uv(64, 38).cuboid(-4.0F, -15.0F, -4.0F, 8.0F, 2.0F, 4.0F, new Dilation(0.0F))
				.uv(36, 22).cuboid(-3.0F, -16.0F, -3.0F, 6.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(68, 7).cuboid(-5.0F, -13.0F, -5.0F, 10.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Armature = Foot.addChild("Armature", ModelPartBuilder.create().uv(0, 19).cuboid(-6.0F, -7.0F, -6.0F, 12.0F, 6.0F, 12.0F, new Dilation(0.0F))
				.uv(20, 59).cuboid(5.5F, -7.0F, -4.0F, 2.0F, 5.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 59).cuboid(-7.5F, -7.0F, -4.0F, 2.0F, 5.0F, 8.0F, new Dilation(0.0F))
				.uv(38, 27).cuboid(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData FullArmature = Armature.addChild("FullArmature", ModelPartBuilder.create().uv(38, 9).cuboid(-5.0F, -16.5F, -5.0F, 10.0F, 3.0F, 10.0F, new Dilation(0.0F))
				.uv(32, 66).cuboid(-7.5F, -11.0F, -4.0F, 2.0F, 4.0F, 8.0F, new Dilation(0.0F))
				.uv(60, 48).cuboid(5.5F, -11.0F, -4.0F, 2.0F, 4.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-6.0F, -14.0F, -6.0F, 12.0F, 7.0F, 12.0F, new Dilation(0.0F))
				.uv(84, 0).cuboid(-4.5F, -12.0F, -6.5F, 9.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(WallNutEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float maxH = entity.getMaxHealth();
		float health = entity.getHealth();
		float hSegments = maxH / 4;

		if(health > hSegments * 3){
			normalFace.hidden = false;
			armature.hidden = false;
			fullArmature.hidden = false;
		} else if(health > hSegments * 2) {
			normalFace.hidden = false;
			armature.hidden = false;
			fullArmature.hidden = true;
		} else if(health > hSegments) {
			normalFace.hidden = false;
			armature.hidden = true;
			fullArmature.hidden = true;
		} else {
			normalFace.hidden = true;
			armature.hidden = true;
			fullArmature.hidden = true;
		}

		brokenFace.hidden = !normalFace.hidden;
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