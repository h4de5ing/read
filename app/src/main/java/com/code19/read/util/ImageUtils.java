package com.code19.read.util;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.BitmapCallback;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public class ImageUtils {
    public static void setImageViewURL(String url, final ImageView iv) {
        OkHttpUtils.get(url)
                .tag(url)
                .execute(new BitmapCallback() {
                    @Override
                    public void onResponse(boolean isFromCache, Bitmap bitmap, Request request, @Nullable Response response) {
                        iv.setImageBitmap(bitmap);
                    }
                });

    }
}
