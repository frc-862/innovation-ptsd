package com.frc.frcinnovationptsd;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class BasicAnimator {

    /**
     * Translate a view using coordinates.
     * @param view The view to translate.
     * @param deltaX Change in x position.
     * @param deltaY Change in y position.
     * @param duration Time of animation (ms)
     * @param interpolatorX Interpolation type (default is LinearInterpolator)
     * @param interpolatorY Interpolation type (default is LinearInterpolator)
     */
    public static void Move(View view, float deltaX, float deltaY, long duration, TimeInterpolator interpolatorX, TimeInterpolator interpolatorY) {
        float currentX = view.getTranslationX();
        float currentY = view.getTranslationY();
        ValueAnimator animatorX = ValueAnimator.ofFloat(currentX, currentX + deltaX)
                .setDuration(duration);

        ValueAnimator animatorY = ValueAnimator.ofFloat(currentY, currentY + deltaY)
                .setDuration(duration);


        animatorX.setInterpolator(interpolatorX == null ? new LinearInterpolator() : interpolatorX);
        animatorY.setInterpolator(interpolatorY == null ? new LinearInterpolator() : interpolatorY);

        animatorX.addUpdateListener(animation -> view.setTranslationX((Float) animation.getAnimatedValue()));

        animatorY.addUpdateListener(animation -> view.setTranslationY((Float) animation.getAnimatedValue()));

        animatorX.start();
        animatorY.start();
    }

    /**
     * Changes the alpha value of a view.
     * @param view The view to animate.
     * @param fade Value of fade [0, 1] (0 = transparent, 1 = opaque)
     * @param duration Time of animation (ms)
     * @param interpolator Interpolation type (default is LinearInterpolator)
     */
    public static void FadeInOut(View view, float fade, long duration, TimeInterpolator interpolator) {
        ValueAnimator animator = ValueAnimator.ofFloat(view.getAlpha(), fade)
                .setDuration(duration);

        animator.setInterpolator(interpolator == null ? new LinearInterpolator() : interpolator);

        animator.addUpdateListener(animation -> view.setAlpha((Float)animation.getAnimatedValue()));

        animator.start();
    }

    /**
     * Rotate view about its anchor.
     * @param view The view to animate.
     * @param deltaDegrees The degrees to rotate the view.
     * @param duration Time of animation (ms)
     * @param interpolator Interpolation type (default is LinearInterpolator)
     */
    public static void Rotate(View view, float deltaDegrees, long duration, TimeInterpolator interpolator) {
        float currentRotation = view.getRotation();
        ValueAnimator animator = ValueAnimator.ofFloat(currentRotation, currentRotation + deltaDegrees)
                .setDuration(duration);

        animator.setInterpolator(interpolator == null ? new LinearInterpolator() : interpolator);

        animator.addUpdateListener(animation -> view.setRotation((Float)animation.getAnimatedValue()));

        animator.start();
    }

    /**
     * Change view size about its anchor.
     * @param view The view to animate.
     * @param scaleX The desired x scale [0, infinity] (1 = no change)
     * @param scaleY The desired y scale [0, infinity] (1 = no change)
     * @param duration Time of animation (ms)
     * @param interpolatorX Interpolation type for x scaling (default is LinearInterpolator)
     * @param interpolatorY Interpolation type for y scaling (default is LinearInterpolator)
     */
    public static void Scale(View view, float scaleX, float scaleY, long duration, TimeInterpolator interpolatorX, TimeInterpolator interpolatorY) {
        ValueAnimator animatorX = ValueAnimator.ofFloat(view.getAlpha(), scaleX)
                .setDuration(duration);
        ValueAnimator animatorY = ValueAnimator.ofFloat(view.getAlpha(), scaleY)
                .setDuration(duration);

        animatorX.setInterpolator(interpolatorX == null ? new LinearInterpolator() : interpolatorX);
        animatorY.setInterpolator(interpolatorY == null ? new LinearInterpolator() : interpolatorY);

        animatorX.addUpdateListener(animation -> view.setScaleX((Float)animation.getAnimatedValue()));

        animatorY.addUpdateListener(animation -> view.setScaleY((Float)animation.getAnimatedValue()));

        animatorX.start();
        animatorY.start();
    }
}
