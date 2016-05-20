package com.code19.read.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.code19.read.App;
import com.code19.read.util.lruCache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        Log.i("ghost", "setCache:key:" + encodeName + ",strCache:" + strCache);
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
        File file = new File(filename);
        return file.exists() ? FileUtils.readFile(filename) : "";
    }

    /**
     * 设置lru缓存
     *
     * @param imgUrl
     */
    public static void setLruCache(final String imgUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DiskLruCache lruCache = DiskLruCache.open(new File(mContext.getExternalCacheDir().getAbsolutePath()), 0, 1, 10 * 1024 * 1024);
                    DiskLruCache.Editor edit = lruCache.edit(Md5Utils.encode(imgUrl));
                    if (edit != null) {
                        OutputStream outputStream = edit.newOutputStream(0);
                        if (HttpClient.downloadUrlToStream(imgUrl, outputStream)) {
                            edit.commit();
                        } else {
                            edit.abort();
                        }
                    }
                    lruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取lru缓存
     *
     * @param key
     * @return
     */
    public static Bitmap getLruCache(String key) {
        Bitmap bitmap = null;
        try {
            DiskLruCache lruCache = DiskLruCache.open(new File(mContext.getExternalCacheDir().getAbsolutePath()), 0, 1, 10 * 1024 * 1024);
            DiskLruCache.Snapshot snapshot = lruCache.get(key);
            if (snapshot != null) {
                InputStream is = snapshot.getInputStream(0);
                bitmap = BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
