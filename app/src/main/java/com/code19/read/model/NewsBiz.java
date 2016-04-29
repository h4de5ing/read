package com.code19.read.model;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public class NewsBiz implements INewsBiz {

    @Override
    public void getData(final String url, final String keyworld, final int num, final int page, final OnLoadListener onLoadListener) {
        final String uri = url + "/" + keyworld + "/?key=85ee2b20a54d12ddcbd2a23ebb0bff5f&num=" + num + "&page=" + page;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.get(uri)
                        .tag(this)
                        .cacheKey(url)
                        .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                        .execute(new StringCallback() {
                            @Override
                            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                Gson gson = new Gson();
                                NewModel n = gson.fromJson(s, NewModel.class);
                                onLoadListener.loadSuccess(n);
                            }
                        });
            }
        }).start();
    }
}
