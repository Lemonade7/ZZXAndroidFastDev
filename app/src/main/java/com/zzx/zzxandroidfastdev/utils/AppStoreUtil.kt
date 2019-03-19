package com.zhuzongxing.zzxdevelopmentkit.utils

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import java.lang.Exception

/**
 * App商城相关工具类
 */
object AppStoreUtil {
    /**
     * 跳转到商城中的某个APP
     */
    fun goToAppStore(activity: Activity, title: String, message: String, textPositive: String, textNegative: String, uriString: String) {
        DialogUtil.showAlertDialog(activity, title, message, textPositive, DialogInterface.OnClickListener { _, _ ->
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                activity.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, textNegative, DialogInterface.OnClickListener { it, _ ->
            it.cancel()
        })
    }
}