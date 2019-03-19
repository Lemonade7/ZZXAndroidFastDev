package com.zhuzongxing.zzxdevelopmentkit.utils

import android.content.Context
import android.view.WindowManager

/**
 * ScreenUtil
 * @author ZZX
 */
object ScreenUtil {
    fun getScreenWidth(context: Context): Int {
        val windowManager: WindowManager? = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return windowManager?.defaultDisplay?.width ?: 0
    }
}