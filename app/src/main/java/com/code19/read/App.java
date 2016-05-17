package com.code19.read;

import android.app.Application;
import android.content.Context;

import com.lzy.okhttputils.OkHttpUtils;

/**
 * Created by Gh0st on 2016/4/29 029.
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        OkHttpUtils.init(this);
    }

    /**
     * @return 返回全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }

}
