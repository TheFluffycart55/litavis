package net.thefluffycart.litavis.entity.client.animation;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class BurrowAnimations {
    public static final Animation BURROW_WALK = Animation.Builder.create(1f).looping()
            .addBoneAnimation("HeadBase",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, -2f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, -2f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, -1f, -2f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, -2f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("HeadBase",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("rod",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, -3f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, -3f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, -2f, -3f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, -3f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("rod",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("top_base",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, -1f, -2f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, AnimationHelper.createTranslationalVector(0f, -1f, -2f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("top_base",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(-15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, AnimationHelper.createRotationalVector(-15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bottom_base",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, -1f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation BURROW_DRILLREADY = Animation.Builder.create(0.5f)
            .addBoneAnimation("HeadBase",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -3f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("rod",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -2f, -3f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, -5f, -6f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("rod",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(45f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(90f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("top_base",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -2f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation BURROW_DRILLCHARGE = Animation.Builder.create(1f).looping()
            .addBoneAnimation("HeadBase",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, -2f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, -2f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, -1f, -2f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, -2f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("HeadBase",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("rod",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, -5f, -7f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("rod",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(90f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(90f, 0f, -180f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(90f, 0f, -360f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(90f, 0f, -540f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(90f, 0f, -720f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("top_base",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("top_base",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bottom_base",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 1f, 2f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bottom_base",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(30f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
}
