package com.zzx.zzxandroidfastdev.component.zzxgeneralview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.zhuzongxing.zzxdevelopmentkit.utils.ScreenUtil

/**
 * slide item view
 * @author ZZX
 * @date 2019/1/27
 */
class ZZXSlideItemView(context: Context?, attrs: AttributeSet?) : HorizontalScrollView(context, attrs), GestureDetector.OnGestureListener {

    private val ratio = 0.3
    private var mContentWidth: Int = 0
    private var mSlideMenuWidth: Int = 0
    private var isFirstTime: Boolean = true
    private var mDetector: GestureDetector? = null

    init {
        mDetector = GestureDetector(context, this)
        //get content layout width
        mContentWidth = ScreenUtil.getScreenWidth(context!!)
        //get menu layout width
        mSlideMenuWidth = (mContentWidth * ratio).toInt()
        overScrollMode = View.OVER_SCROLL_NEVER
        isHorizontalScrollBarEnabled = false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return mDetector!!.onTouchEvent(ev)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (isFirstTime) {
            val containerLayout = getChildAt(0) as LinearLayout
            //set content Layout
            containerLayout.getChildAt(0).layoutParams.width = mContentWidth
            //set Slide Menu Layout
            containerLayout.getChildAt(1).layoutParams.width = mSlideMenuWidth
            isFirstTime = false
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.d("ZZXView", "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.d("ZZXView", "onSingleTapUp")
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.d("ZZXView", "onDown")
        this.smoothScrollTo(0, 0)
        //true->deliver events
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d("ZZXView", "onFling")
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.d("ZZXView", "onScroll")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.d("ZZXView", "onLongPress")
        this.smoothScrollTo(mSlideMenuWidth, 0)
    }
}