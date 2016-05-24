package com.code19.read.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.code19.library.AppUtils;
import com.code19.library.DateUitls;
import com.code19.library.FileUtils;
import com.code19.read.App;
import com.code19.read.domain.AppModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppBiz implements IAppBiz {
    @Override
    public void getAppInfo(final OnAppLoadListener onAppLoadListener) {
        onAppLoadListener.loadProgress();//正在加载
        List<AppModel> appDatas = new ArrayList<AppModel>();
        Context context = App.getContext();
        PackageManager pm = App.getContext().getPackageManager();
        List<PackageInfo> infoList = pm.getInstalledPackages(0);
        for (PackageInfo info : infoList) {
            AppModel appModel = new AppModel();
            String appNmae = AppUtils.getAppName(info.packageName, context);
            Drawable appIcon = AppUtils.getAppIcon(info.packageName, context);
            String appDate = DateUitls.formatDate(AppUtils.getAppDate(info.packageName, context));
            String appSize = FileUtils.formatFileSize(App.getContext(), AppUtils.getAppSize(info.packageName, context));
            appModel.setAppName(appNmae);
            appModel.setAppIcon(appIcon);
            appModel.setAppDate(appDate);
            appModel.setAppSize(appSize);
            appModel.setAppApk(AppUtils.getAppApk(info.packageName, context));
            if (!TextUtils.isEmpty(appNmae) && !TextUtils.isEmpty(appDate) && !TextUtils.isEmpty(appSize)) {
                appDatas.add(appModel);
            }
        }
        onAppLoadListener.loadFinish(appDatas);//加载结束
    }
}
