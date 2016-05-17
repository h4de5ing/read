package com.code19.read.util;

import android.content.Context;

import com.code19.read.App;

import java.io.File;

/**
 * Created by Gh0st on 2016/4/29 029.
 */
public class CacheUtils {
    private static Context mContext = App.getContext();

    /**
     * @param key      以Uri为key
     * @param strCache 加载到的网络请求结果为缓存内容
     */
    public static void setCache(String key, String strCache) {
        String encodeName = Md5Utils.encode(key);
        //Log.i("ghost", "setCache:key:" + encodeName + ",strCache:" + strCache);
        boolean isCacheSuccess = FileUtils.writeFile(mContext.getExternalCacheDir() + "/" + encodeName, strCache);
        //Toast.makeText(mContext, isCacheSuccess ? "缓存成功" : "缓存失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据Key查询缓存是否存在
     *
     * @param key uri值
     * @return 有缓存返回缓存字符串, 没缓存则返回空。
     */
    public static String getCache(String key) {
        String encodeName = Md5Utils.encode(key);
        String filename = App.getContext().getExternalCacheDir() + "/" + encodeName;
        //Log.i("ghost", "本地获取缓存:" + filename);
        File file = new File(filename);
        return file.exists() ? FileUtils.readFile(filename) : "";
    }

    public static void setImgCache(String imgUrl) {

    }
}
