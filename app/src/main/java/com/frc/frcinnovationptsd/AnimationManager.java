package com.frc.frcinnovationptsd;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class AnimationManager {

    /**
     * Translate a view using coordinates.
     * @param view The view to translate.
     * @param finalX Change in x position
     * @param finalY Change in y position
     * @param duration Time of animation (ms)
     * @param interpolatorX Interpolation type (default is LinearInterpolator)
     * @param interpolatorY Interpolation type (default is LinearInterpolator)
     */
    public static void Move(View view, float finalX, float finalY, long duration, TimeInterpolator interpolatorX, TimeInterpolator interpolatorY) {
        ValueAnimator animatorX = ValueAnimator.ofFloat(0, finalX)
                .setDuration(duration);

        ValueAnimator animatorY = ValueAnimator.ofFloat(0, finalX)
                .setDuration(duration);


        animatorX.setInterpolator(interpolatorX == null ? new LinearInterpolator() : interpolatorX);
        animatorY.setInterpolator(interpolatorY == null ? new LinearInterpolator() : interpolatorY);

        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((Float) animation.getAnimatedValue());
            }
        });

        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Float) animation.getAnimatedValue());
            }
        });

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

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setAlpha((Float)animation.getAnimatedValue());
            }
        });

        animator.start();
    }
}
