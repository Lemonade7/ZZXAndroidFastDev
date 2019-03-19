package com.leku.ustv.utils

import android.os.Handler
import android.view.MotionEvent
import android.view.View

/**
 * 点击事件
 * @author ZZX
 * @date 2019/3/6
 */
class ZZXClickListener(view: View, zzxClickCallBack: ZZXClickCallBack) : View.OnTouchListener {
    private val timeout = 400
    private var mHandler: Handler? = null
    private var mClickCount = 0
    private var mView:View= view
    private var mZZXClickCallBack: ZZXClickCallBack? = zzxClickCallBack

    init {
        this.mHandler = Handler()
    }

    interface ZZXClickCallBack {
        /**
         * 点击一次的回调
         */
        fun oneClick(view: View)

        /**
         * 续点击两次的回调
         */
        fun doubleClick(view: View)

    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        if (p1 == null) {
            return false
        }
        mClickCount++
        if (p1.action == MotionEvent.ACTION_DOWN) {
            mHandler?.postDelayed((Runnable {
                if (1 == mClickCount) {
                    mZZXClickCallBack?.oneClick(mView)
                } else if (2 == mClickCount) {
                    mZZXClickCallBack?.doubleClick(mView)
                }
                mHandler?.removeCallbacksAndMessages(null)
                mClickCount = 0
            }), timeout.toLong())
        }
        return false
    }
}
