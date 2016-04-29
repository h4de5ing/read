package com.code19.read;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;

/**
 * Created by Gh0st on 2016/4/29 029.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtils.init(this);
    }
}
