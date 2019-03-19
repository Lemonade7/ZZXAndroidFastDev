package com.zzx.zzxandroidfastdev.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity基类
 * @author ZZX
 */
abstract class BaseActivity : AppCompatActivity() {
    /**
     * 后台杀死的数据恢复
     */
    abstract fun recoverData(savedInstanceState: Bundle)

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 设置布局ID
     */
    protected abstract fun setLayoutID(): Int

    /**
     * 初始化布局
     */
    protected abstract fun initView()

    /**
     * 设置布局
     */
    protected abstract fun setView()

    /**
     * 存储后台杀死用来恢复的数据
     */
    abstract fun saveData(outState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutID())
        if (savedInstanceState != null) {
            recoverData(savedInstanceState)
        }
        initData()
        initView()
        setView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveData(outState)
    }
}