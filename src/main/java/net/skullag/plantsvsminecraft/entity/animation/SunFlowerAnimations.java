package net.skullag.plantsvsminecraft.entity.animation;// Save this class in your mod and generate all required imports

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class SunFlowerAnimations {
	public static final Animation idle = Animation.Builder.create(2.0F).looping()
		.addBoneAnimation("Leaf", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.3333F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf2", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(7.0F, 0.0F, -7.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.3333F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf3", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.3333F, AnimationHelper.createRotationalVector(7.0F, 0.0F, 7.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Stem", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-4.8304F, -1.2926F, -14.9455F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(2.6664F, 0.0743F, -1.6651F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(-4.8304F, 1.2926F, 14.9455F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5F, AnimationHelper.createRotationalVector(2.6664F, 0.0743F, -1.6651F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(-4.8304F, -1.2926F, -14.9455F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Stem2", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(9.6831F, 2.346F, 1.0862F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(-0.3235F, 2.2182F, 0.5875F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(9.6831F, 2.346F, 1.0862F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.3235F, 2.2182F, 0.5875F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(9.6831F, 2.346F, 1.0862F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Neck", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Head", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 0.95F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 0.95F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createScalingVector(1.0F, 0.95F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Petals", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.25F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.25F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final Animation sunproduce = Animation.Builder.create(0.5F)
		.addBoneAnimation("Foot", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, 1.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createScalingVector(1.1F, 1.0F, 1.1F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf2", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-6.0745F, 0.0353F, 5.9284F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf2", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createScalingVector(1.1F, 1.0F, 1.1F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf3", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-4.0736F, 0.0187F, -6.2992F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf3", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createScalingVector(1.1F, 1.0F, 1.1F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Stem", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(-20.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Stem", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.0F, 1.1F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Stem2", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Neck", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Neck", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.1F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Petals", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, 1.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf4", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final Animation nutrient = Animation.Builder.create(2.0F)
		.addBoneAnimation("Stem", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.75F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Stem2", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.75F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Neck", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.75F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Neck", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.875F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.125F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Leaf4", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.375F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.875F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.125F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.375F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.625F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();
}