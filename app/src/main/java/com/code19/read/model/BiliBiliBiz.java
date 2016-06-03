package com.code19.read.model;

import android.support.annotation.Nullable;
import android.util.Log;

import com.code19.library.NetUtils;
import com.code19.read.App;
import com.code19.read.R;
import com.code19.read.util.Utils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public class BiliBiliBiz implements IBiliBiliBiz {
    @Override
    public void getBiliData(final String url, final OnBiliBiliLoadListener onBiliBiliLoadListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (NetUtils.isConnected(App.getContext())) {
                    OkHttpUtils.get(url)
                            .tag(this)
                            .cacheKey(url)
                            .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                            .execute(new StringCallback() {
                                @Override
                                public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                    Log.i("ghost", "Bili:" + s);
                                    //onBiliBiliLoadListener.loadSuccess();
                                }
                            });
                } else {
                    onBiliBiliLoadListener.loadFailed(Utils.getString(R.string.check_networkd));
                }
            }
        }).start();
    }
}
