package com.zhuzongxing.zzxdevelopmentkit.utils

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * App工具类
 * @author ZZX
 */
object AppUtil {
    /**
     * 获取App的icon
     */
    fun getAppIcon(context: Context, packageName: String): Drawable {
        val packageManager = context.packageManager
        val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
        return applicationInfo.loadIcon(packageManager)
    }
}