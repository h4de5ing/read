package com.code19.read;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.code19.read.util.Utils;
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
        Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
    }

    /**
     * @return 返回全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }
    private class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            throwable.printStackTrace();
            Utils.crash2File(App.this, throwable.getMessage());
            Intent intent = new Intent(App.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.this.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}
