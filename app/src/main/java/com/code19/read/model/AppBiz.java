package com.code19.read.model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.code19.read.App;
import com.code19.read.util.DateUtils;
import com.code19.read.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppBiz implements IAppBiz {
    @Override
    public void getAppInfo(OnAppLoadListener onAppLoadListener) {
        List<AppModel> appDatas = new ArrayList<AppModel>();
        PackageManager pm = App.getContext().getPackageManager();
        List<PackageInfo> infoList = pm.getInstalledPackages(0);
        for (PackageInfo info : infoList) {
            onAppLoadListener.loadProgress();//正在加载
            AppModel appModel = new AppModel();
            String appNmae = getAppName(info.packageName, pm);
            Drawable appIcon = getAppIcon(info.packageName, pm);
            String appDate = DateUtils.formatDate(getAppDate(info.packageName, pm));
            String appSize = FileUtils.formatFileSize(App.getContext(), getAppSize(info.packageName, pm));
            appModel.setAppName(appNmae);
            appModel.setAppIcon(appIcon);
            appModel.setAppDate(appDate);
            appModel.setAppSize(appSize);
            appDatas.add(appModel);
        }
        onAppLoadListener.loadFinish(appDatas);//加载结束
    }

    /**
     * 获取应用名称
     *
     * @param packageName
     * @param pm
     * @return
     */
    private String getAppName(String packageName, PackageManager pm) {
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
    private Drawable getAppIcon(String packageName, PackageManager pm) {
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
    public long getAppSize(String packageName, PackageManager pm) {
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
    public long getAppDate(String packageName, PackageManager pm) {
        long lastUpdateTime = 0;
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            lastUpdateTime = packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return lastUpdateTime;
    }
}
