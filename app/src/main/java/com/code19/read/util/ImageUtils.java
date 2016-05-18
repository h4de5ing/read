package com.code19.read.util;

import com.lzy.okhttputils.OkHttpUtils;

import java.io.IOException;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public class ImageUtils {
    public static void setImageViewURL(String url) {
        String encodeName = Md5Utils.encode(url);
        try {
            String s = OkHttpUtils.get(url).execute().toString();
            CacheUtils.setCache(encodeName, s);//图片缓存
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
