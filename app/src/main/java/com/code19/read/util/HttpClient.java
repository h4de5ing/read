package com.code19.read.util;

import android.util.Log;

import com.code19.library.CacheUtils;
import com.code19.library.CipherUtils;
import com.code19.read.App;
import com.lzy.okhttputils.OkHttpUtils;

import java.io.IOException;

/**
 * Create by h4de5ing 2016/5/17 017
 */
public class HttpClient {
    public static String get(String uri) throws IOException {
        String encodeName = CipherUtils.encode(uri);
        if ("".equals(CacheUtils.getCache(App.getContext(),encodeName))) {
            String cache = OkHttpUtils.get(uri).execute().body().toString();
            Log.i("ghost", "cache:" + cache);
            CacheUtils.setCache(App.getContext(),encodeName, cache);
            return cache;
        } else {
            return CacheUtils.getCache(App.getContext(),encodeName);
        }
    }



}
