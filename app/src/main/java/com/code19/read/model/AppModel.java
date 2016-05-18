package com.code19.read.model;

import android.graphics.drawable.Drawable;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppModel {
    private Drawable icon;
    private String appName;
    private String appSize;
    private String appDate;

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
}
