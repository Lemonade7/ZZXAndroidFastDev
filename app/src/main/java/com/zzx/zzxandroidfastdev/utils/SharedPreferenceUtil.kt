package com.zhuzongxing.zzxdevelopmentkit.utils

import android.content.Context

/**
 * SharedPreference工具类
 * @author ZZX
 */
class SharedPreferenceUtil(prefName: String) {
    private val sp = ContextUtil.getContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val editor = sp.edit()
    /**
     * 存储数据
     */
    fun put(key: String, value: Any) {
        when (value) {
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
        }
        editor.apply()
    }

    /**
     * 根据key值取出数据
     */
    fun get(key: String, defaultValue: Any): Any? {
        var value: Any? = null
        when (defaultValue) {
            is String -> value = sp.getString(key, defaultValue)
            is Boolean -> value = sp.getBoolean(key, defaultValue)
            is Int -> value = sp.getInt(key, defaultValue)
            is Long -> value = sp.getLong(key, defaultValue)
            is Float -> value = sp.getFloat(key, defaultValue)
        }
        return value
    }

    /**
     * 根据key值去除数据
     */
    fun remove(key: String) {
        editor.remove(key).apply()
    }

    /**
     * 删除所有数据
     */
    fun clear() {
        editor.clear().apply()
    }

    /**
     * 是否存在某个key
     */
    fun contain(key: String): Boolean {
        return sp.contains(key)
    }
}