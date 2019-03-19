package com.zzx.zzxandroidfastdev.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 属性动画工具类
 *
 * @author ZZX
 */
public class ObjectAnimatorUtil {
    private static EndAnimatorListener mEndAnimatorListener;

    /**
     * 点击放大动画
     *
     * @param view targetView
     */
    public static void clickScaleXY(View view) {
        ObjectAnimator startScaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1, 1.2f, 1);
        ObjectAnimator startScaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1, 1.2f, 1);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.playTogether(startScaleXAnimator, startScaleYAnimator);
        set.start();
    }

    /**
     * 点击放大动画
     *
     * @param view                targetView
     * @param endAnimatorListener 动画结束监听器
     */
    public static void clickScaleXY(View view, final EndAnimatorListener endAnimatorListener) {
        ObjectAnimator startScaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1, 1.2f, 1);
        ObjectAnimator startScaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1, 1.2f, 1);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.playTogether(startScaleXAnimator, startScaleYAnimator);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mEndAnimatorListener = endAnimatorListener;
                mEndAnimatorListener.onAnimatorEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * Y轴平移动画
     *
     * @param view targetView
     * @param up   上升
     * @param sp   平移的像素距离
     */
    public static void translateY(View view, boolean up, int sp) {
        ObjectAnimator translationY = null;
        if (up) {
            translationY = ObjectAnimator.ofFloat(view, "translationY", 0, sp);
        } else {
            translationY = ObjectAnimator.ofFloat(view, "translationY", 0, sp);
        }
        translationY.setDuration(1000);
        translationY.setInterpolator(new LinearInterpolator());
        translationY.start();
    }

    public interface EndAnimatorListener {
        /**
         * 属性动画结束时
         */
        void onAnimatorEnd();
    }
}