package com.code19.read.domain;

import android.graphics.drawable.Drawable;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppModel {
    private Drawable icon;
    private String appName;
    private String appSize;
    private String appDate;
    private String appApk;

    public Drawable getAppIcon() {
        return icon;
    }

    public void setAppIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppApk() {
        return appApk;
    }

    public void setAppApk(String appApk) {
        this.appApk = appApk;
    }
}
