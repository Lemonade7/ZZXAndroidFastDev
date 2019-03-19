package com.zhuzongxing.zzxdevelopmentkit.utils
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.GridLayoutAnimationController
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * 布局动画工具类
 * @author ZZX
 */
class LayoutAnimationUtil {
    companion object {
        /**
         * LayoutAnimation动画
         * @param viewGroup    容器
         * @param animation    子item的动画集合
         * @param reverseOrder 是否倒序
         */
        fun doLayoutAnimation(viewGroup: ViewGroup, animation: Animation, reverseOrder: Boolean) {
            if (viewGroup is RecyclerView) {
                when (viewGroup.layoutManager) {
                    null -> {
                        return
                    }
                    //走此条件需要自定义RecyclerView的attachLayoutAnimationParameters方法
                    is GridLayoutManager, is StaggeredGridLayoutManager -> {
                        val controller = GridLayoutAnimationController(animation)
                        controller.columnDelay = 0.2f
                        controller.rowDelay = 0.3f
                        viewGroup.layoutAnimation = controller
                        controller.order = if (reverseOrder) LayoutAnimationController.ORDER_REVERSE else LayoutAnimationController.ORDER_NORMAL
                    }
                    is LinearLayoutManager -> {
                        setLayoutController(animation, viewGroup, reverseOrder)
                    }
                }
                viewGroup.adapter!!.notifyDataSetChanged()
            } else {
                setLayoutController(animation, viewGroup, reverseOrder)

            }
            viewGroup.scheduleLayoutAnimation()
        }

        private fun setLayoutController(animation: Animation, viewGroup: ViewGroup, reverseOrder: Boolean) {
            val controller = LayoutAnimationController(animation)
            controller.delay = 0.15f
            viewGroup.layoutAnimation = controller
            controller.order = if (reverseOrder) LayoutAnimationController.ORDER_REVERSE else LayoutAnimationController.ORDER_NORMAL
        }
    }
}