package com.zzx.zzxandroidfastdev.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment基类
 * @author ZZX
 */
abstract class BaseFragment : Fragment() {
    private var mActivity: BaseActivity? = null
    private var mContentView: View? = null
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
    abstract fun initView(mContentView: View?)

    /**
     * 设置布局
     */
    protected abstract fun setView()

    /**
     * 存储后台杀死用来恢复的数据
     */
    abstract fun saveData(outState: Bundle?)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseActivity?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (savedInstanceState != null) {
            recoverData(savedInstanceState)
        }
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContentView = LayoutInflater.from(mActivity).inflate(setLayoutID(), container, false)
        initView(mContentView)
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveData(outState)
    }
}