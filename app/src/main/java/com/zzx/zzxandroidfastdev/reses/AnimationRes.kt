package com.zzx.zzxandroidfastdev.reses

import android.view.animation.*

/**
 * 常用Animation资源
 * @author ZZX
 */
class AnimationRes {
    companion object {
        /**
         * 从左侧进入，并带有弹性的动画
         * @return AnimationSet
         */
        fun getFromLeftBounce(): AnimationSet {
            val animationSet = AnimationSet(true)
            val translateX1 = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.1f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
            )
            translateX1.duration = 300
            translateX1.interpolator = DecelerateInterpolator()
            translateX1.startOffset = 0

            val translateX2 = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, -0.1f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
            )
            translateX2.startOffset = 300
            translateX2.interpolator = DecelerateInterpolator()
            translateX2.duration = 50

            val translateX3 = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -0.1f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
            )
            translateX3.startOffset = 350
            translateX3.interpolator = DecelerateInterpolator()
            translateX3.duration = 50

            animationSet.addAnimation(translateX1)
            animationSet.addAnimation(translateX2)
            animationSet.addAnimation(translateX3)
            animationSet.duration = 400

            return animationSet
        }

        /**
         * 滑入动画
         *
         * @param left 是否为左
         * @return AnimationSet
         */
        fun getSlideInAnimation(left: Boolean): AnimationSet {
            val set = AnimationSet(true)
            val translateLeftAnimation = TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                -1.0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_PARENT,
                0f,
                Animation.RELATIVE_TO_PARENT,
                0f
            )
            translateLeftAnimation.duration = 2000
            translateLeftAnimation.fillAfter = true
            val translateRightAnimation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,
                1.0f,
                Animation.RELATIVE_TO_PARENT,
                0f,
                Animation.RELATIVE_TO_PARENT,
                0f,
                Animation.RELATIVE_TO_PARENT,
                0f
            )
            translateRightAnimation.duration = 2000
            translateRightAnimation.fillAfter = true
            val alphaAnimation = AlphaAnimation(0f, 1.0f)
            alphaAnimation.duration = 2000
            set.interpolator = AccelerateDecelerateInterpolator()
            set.addAnimation(if (left) translateLeftAnimation else translateRightAnimation)
            set.addAnimation(alphaAnimation)
            return set
        }

        /**
         * 从底部滑入的动画
         * @return AnimationSet
         */
        fun getSlideInBottom(): AnimationSet {
            val set = AnimationSet(true)
            val translateYAnimation = TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                -1.0f,
                Animation.RELATIVE_TO_SELF,
                0f
            )
            val alphaAnimation = AlphaAnimation(0f, 1.0f)
            set.addAnimation(alphaAnimation)
            set.addAnimation(translateYAnimation)
            set.duration = 400
            set.interpolator = AccelerateDecelerateInterpolator()
            return set
        }
    }
}