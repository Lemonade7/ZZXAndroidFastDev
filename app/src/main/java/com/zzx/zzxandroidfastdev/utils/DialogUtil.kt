package com.zhuzongxing.zzxdevelopmentkit.utils

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog

/**
 * 对话框工具类
 * @author ZZX
 */
object DialogUtil {
    private var mDialogChoiceCallback: DialogChoiceCallback? = null
    /**
     * show popupWindow
     * @param context just context
     * @param popupLayout popupWindow inside view"layout id
     * @param width width
     * @param height height
     * @param animationStyle popupWindow animation
     */
    fun showPopupWindow(
        context: Context,
        popupLayout: Int,
        width: Int,
        height: Int,
        animationStyle: Int?
    ): PopupWindow {
        val view = LayoutInflater.from(context).inflate(popupLayout, null)
        val popupWindow = PopupWindow(view, width, height)
        if (animationStyle != null) {
            popupWindow.animationStyle = animationStyle
        }
        popupWindow.isOutsideTouchable = true
        return popupWindow
    }

    /**
     * 显示最基本的AlertDialog
     */
    fun showAlertDialog(context: Context, title: String, message: String, cancelable: Boolean) {
        alertDialogBuilder(context, title, message, cancelable).show()
    }

    /**
     * 显示带按钮的AlertDialog
     */
    fun showAlertDialog(
        context: Context,
        title: String,
        message: String,
        textPositive: String,
        positiveListener: DialogInterface.OnClickListener,
        textNegative: String,
        negativeListener: DialogInterface.OnClickListener
    ) {
        val builder = alertDialogBuilder(context, title, message, true)
        builder.setPositiveButton(textPositive, positiveListener)
        builder.setNegativeButton(textNegative, negativeListener)
        val dialog = builder.create()
        dialog.show()
    }

    private fun alertDialogBuilder(
        context: Context,
        title: String,
        message: String,
        cancelable: Boolean
    ): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(cancelable)
        return builder
    }


    /**
     * 单选对话框
     */
    fun showSingleChoiceDialog(
        context: Context,
        title: String,
        iconId: Int,
        defaultSelected: Int,
        items: Array<String>,
        dialogChoiceCallback: DialogChoiceCallback
    ) {
        mDialogChoiceCallback = dialogChoiceCallback
        var myChoice: Int? = null
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title).setIcon(iconId)
        builder.setSingleChoiceItems(items, defaultSelected) { _, which -> myChoice = which }
        builder.setPositiveButton("确定") { dialog, _ ->
            if (myChoice == null) {
                myChoice = defaultSelected
            }
            mDialogChoiceCallback!!.getResult(myChoice)
            dialog!!.dismiss()
        }.setNegativeButton("取消") { dialog, _ ->
            mDialogChoiceCallback!!.onCancel()
            dialog!!.dismiss()
        }
        builder.show()
    }

    /**
     * 对话框返回接口
     */
    interface DialogChoiceCallback {
        fun getResult(which: Int?)
        fun onCancel()
    }
}