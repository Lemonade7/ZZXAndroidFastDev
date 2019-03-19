package com.zzx.zzxandroidfastdev.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.zhuzongxing.zzxdevelopmentkit.utils.DialogUtil;

import androidx.annotation.RequiresApi;

/**
 * 权限工具类
 *
 * @author Lemonade_7
 * @date 2018/12/25
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionUtil {

    private static final int REQUEST_PERMISSION = 0;
    private static PermissionListener mPermissionListener;

    /**
     * 判断是否有权限
     *
     * @param context     context
     * @param permissions 权限
     * @return 是否含有权限
     */
    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否有权限，没有则请求
     *
     * @param activity    Activity
     * @param permissions 权限
     */
    public static void checkAndRequestPermission(Activity activity, PermissionListener permissionListener, String... permissions) {
        mPermissionListener = permissionListener;
        if (!hasPermissions(activity, permissions)) {
            activity.requestPermissions(permissions, REQUEST_PERMISSION);
        } else {
            allPermissionsGranted();
        }
    }


    /**
     * 用户权限处理，如果全部获取，则跳过，否则，提示Dialog.
     *
     * @param activity     activity
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION && hasAllPermissionGranted(grantResults)) {
            allPermissionsGranted();
        } else {
            showMissingPermissionDialog(activity);
        }
    }

    private static boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 缺少权限Dialog
     *
     * @param activity Activity
     */
    private static void showMissingPermissionDialog(final Activity activity) {
        DialogUtil.INSTANCE.showAlertDialog(activity, "帮助", "权限申请被拒绝", "手动设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToAppSetting(activity);
            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    /**
     * 跳转到App设置页面
     */
    private static void goToAppSetting(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

    /**
     * 取得所有权限
     */
    private static void allPermissionsGranted() {
        if (mPermissionListener != null) {
            mPermissionListener.getPermissionSucceed();
        }
    }

    /**
     * 监听权限是否获取成功接口
     */
    public interface PermissionListener {
        /**
         * 成功获取所有权限
         */
        void getPermissionSucceed();
    }

}
