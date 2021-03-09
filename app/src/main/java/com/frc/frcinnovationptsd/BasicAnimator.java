package com.frc.frcinnovationptsd;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.List;
import java.util.Map;

public class BasicAnimator {

    /**
     * Create and play a single animation.
     * @param view The view to animate.
     * @param propertyName The name of property in the view to animate.
     * @param duration The length of animation (ms).
     * @param evaluator How animation should change the type. (Default is FloatEvaluator)
     * @param interpolator How animation should interpolate between values. (Default is LinearInterpolator)
     * @param values The list of values to interpolate through.
     * @param <T> The value's type.
     * @return
     */
    public static<T> ObjectAnimator GetAnimator(View view, String propertyName, long duration, TypeEvaluator evaluator, TimeInterpolator interpolator, T... values){
        if (evaluator == null)
                evaluator = new FloatEvaluator();
        ObjectAnimator animator = ObjectAnimator.ofObject(view, propertyName, evaluator, values);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator == null ? new LinearInterpolator() : interpolator);
        return animator;
    }

    /**
     * Animate through a list of ObjectAnimators one at a time.
     * @param animators A list of Animators to animate through.
     */
    public static AnimatorSet AnimateMultipleSequential(Animator... animators){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);

        animatorSet.start();
        return animatorSet;
    }

    /**
     * Animate through a list of ObjectAnimators all together.
     * @param animators A list of animators to animate through.
     */
    public static AnimatorSet AnimateMultipleParallel(Animator... animators){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);

        animatorSet.start();
        return animatorSet;
    }
}
