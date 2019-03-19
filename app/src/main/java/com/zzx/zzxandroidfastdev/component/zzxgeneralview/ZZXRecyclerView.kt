package com.zhuzongxing.zzxdevelopmentkit.zzxview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * 自定义RecyclerView
 * 1. 支持GridLayoutControl
 * 2. item侧滑菜单
 */
open class ZZXRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    /**
     * 支持GridLayoutManager以及StaggeredGridLayoutManager
     */
    override fun attachLayoutAnimationParameters(
        child: View?,
        params: ViewGroup.LayoutParams?,
        index: Int,
        count: Int
    ) {
        val layoutManager = this.layoutManager
        if (adapter != null && (layoutManager is GridLayoutManager || layoutManager is StaggeredGridLayoutManager)) {
            var animationParams: GridLayoutAnimationController.AnimationParameters =
                GridLayoutAnimationController.AnimationParameters()
            params!!.layoutAnimationParameters = animationParams
            var columns = 0
            when (layoutManager) {
                is GridLayoutManager -> {
                    columns = layoutManager.spanCount
                }
                is StaggeredGridLayoutManager -> {
                    columns = layoutManager.spanCount
                }
            }
            animationParams.count = count
            animationParams.index = index
            animationParams.columnsCount = columns
            animationParams.rowsCount = count / columns

            val invertedIndex = count - 1 - index
            animationParams.column = columns - 1 - (invertedIndex % columns)
            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns
        } else {
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}