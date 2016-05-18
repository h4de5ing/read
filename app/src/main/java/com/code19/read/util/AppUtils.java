package com.code19.read.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppUtils {
    /**
     * 获取应用名称
     *
     * @param packageName
     * @param pm
     * @return
     */
    public static String getAppName(String packageName, PackageManager pm) {
        String appName = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appName = String.valueOf(pm.getApplicationLabel(applicationInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    /**
     * 获取应用图标
     *
     * @param packageName
     * @param pm
     * @return
     */
    public static Drawable getAppIcon(String packageName, PackageManager pm) {
        Drawable appIcon = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }

    /**
     * 获取app应用的大小
     *
     * @param packageName
     * @param pm
     * @return
     */
    public static long getAppSize(String packageName, PackageManager pm) {
        long appSize = 0;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            String sourceDir = applicationInfo.sourceDir;
            File file = new File(sourceDir);
            appSize = file.length();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appSize;
    }

    /**
     * 获取应用程序最后更新的时间
     *
     * @param packageName
     * @param pm
     * @return
     */
    public static long getAppDate(String packageName, PackageManager pm) {
        long lastUpdateTime = 0;
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            lastUpdateTime = packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return lastUpdateTime;
    }

    /**
     * 获取应用成apk
     *
     * @param packageName 应用程序包名
     * @param pm          包管理器
     * @return 返回获取到的应用程序apk
     */
    public static String getAppApk(String packageName, PackageManager pm) {
        String sourceDir = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            sourceDir = applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sourceDir;
    }
}
