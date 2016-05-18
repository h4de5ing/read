package com.code19.read.util;

import android.util.Log;

import com.lzy.okhttputils.OkHttpUtils;

import java.io.IOException;

/**
 * Create by h4de5ing 2016/5/17 017
 */
public class HttpClient {
    public static String get(String uri) throws IOException {
        String encodeName = Md5Utils.encode(uri);
        if ("".equals(CacheUtils.getCache(encodeName))) {
            String cache = OkHttpUtils.get(uri).execute().body().toString();
            Log.i("ghost", "cache:" + cache);
            CacheUtils.setCache(encodeName, cache);
            return cache;
        } else {
            return CacheUtils.getCache(encodeName);
        }
    }

}
