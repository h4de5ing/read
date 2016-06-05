package com.code19.read.model;

import android.support.annotation.Nullable;

import com.code19.library.NetUtils;
import com.code19.read.App;
import com.code19.read.R;
import com.code19.read.domain.BiliBiliModel;
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
    private String temp="{'type1': {'0': {'age': 12,'name': 'zhangsdan'},'1': {'age': 13,'name': 'lisi'},'num': '123'},'type3': {'0': {'age': 14,'name': 'wangwu'},'1': {'age': 15,'name': 'maliu'},'num': '456',}}";
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
                                    getModel(s);
                                    //onBiliBiliLoadListener.loadSuccess();
                                }
                            });
                } else {
                    onBiliBiliLoadListener.loadFailed(Utils.getString(R.string.check_networkd));
                }
            }
        }).start();
    }

    private BiliBiliModel getModel(String json) {

        return null;
    }
}
