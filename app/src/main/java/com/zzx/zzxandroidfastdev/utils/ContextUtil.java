package com.zzx.zzxandroidfastdev.utils;

import android.app.Application;
import android.content.Context;
import androidx.annotation.Keep;

/**
 * Context工具类
 *
 * @author ZZX
 */
@Keep
class ContextUtil {
    private static Context mAppContext;

    /**
     * 获取Context方法
     *
     * @return context
     */
    static Context getContext() {
        if (mAppContext == null) {
            try {
                Application firstApp = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null);
                if (firstApp != null) {
                    mAppContext = firstApp;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mAppContext;
    }
}
