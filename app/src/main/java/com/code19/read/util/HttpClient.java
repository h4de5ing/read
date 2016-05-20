package com.code19.read.util;

import android.util.Log;

import com.lzy.okhttputils.OkHttpUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
