package com.code19.read.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.code19.library.CacheUtils;
import com.code19.library.NetUtils;
import com.code19.read.App;
import com.code19.read.R;
import com.code19.read.domain.ZhihuModel;
import com.code19.read.util.Utils;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Create by h4de5ing 2016/5/18 018
 */
public class ZhihuBiz implements IZhihuBiz {

    @Override
    public void getData(final String url, final OnZhihuLoadListener onZhihuLoadListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (NetUtils.isConnected(App.getContext())) {
                    if (!TextUtils.isEmpty(CacheUtils.getCache(App.getContext(), url))) {
                        String sl = CacheUtils.getCache(App.getContext(), url);
                        Gson gson = new Gson();
                        onZhihuLoadListener.loadSuccess(gson.fromJson(sl, ZhihuModel.class));
                    } else {
                        OkHttpUtils.get(url)
                                .tag(this)
                                .cacheKey(url)
                                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                        CacheUtils.setCache(App.getContext(), url, s);  //设置缓存
                                        Gson gson = new Gson();
                                        onZhihuLoadListener.loadSuccess(gson.fromJson(s, ZhihuModel.class));
                                    }
                                });
                    }
                } else {
                    onZhihuLoadListener.loadFailed(Utils.getString(R.string.check_networkd));
                }
            }
        }).start();
    }
}
