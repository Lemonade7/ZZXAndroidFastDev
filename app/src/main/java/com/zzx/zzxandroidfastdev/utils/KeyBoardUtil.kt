package com.zhuzongxing.zzxdevelopmentkit.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * KeyBoard相关工具类
 * @author ZZX
 */
object KeyBoardUtil {

    /**
     * 随着view打开软键盘
     */
    fun openKeyBoard(view: View, activity: Activity) {
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
        val imm: InputMethodManager =
            ContextUtil.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    /**
     * 随着view关闭软键盘
     */
    fun closeKeyBoard(view: View) {
        val imm: InputMethodManager =
            ContextUtil.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && view.hasFocus()) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

}