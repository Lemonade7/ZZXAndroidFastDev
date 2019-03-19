package com.zhuzongxing.zzxdevelopmentkit.utils

import android.annotation.SuppressLint
import android.widget.Toast

/**
 * Toast工具类
 * @author ZZX
 */
object ToastUtil {
    private var mToast: Toast? = null
    /**
     * 显示最基本的Toast
     */
    @SuppressLint("ShowToast")
    fun showBasicToast(text: String) {
        if (mToast == null) {
            mToast = Toast.makeText(ContextUtil.getContext(), text, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(text)
            mToast!!.duration = Toast.LENGTH_SHORT
        }
        mToast!!.show()
    }
}