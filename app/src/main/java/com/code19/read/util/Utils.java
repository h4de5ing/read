package com.code19.read.util;

import android.content.Context;
import android.content.Intent;

import com.code19.library.JsonUtils;
import com.code19.read.App;
import com.code19.read.domain.ZhihuStoryModel;
import com.code19.read.ui.activity.WebViewActivity;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public class Utils {
    public static String getString(int resId) {
        return App.getContext().getResources().getString(resId);
    }

    public static void startZhihuWebAcitivty(Context context, String string) {
        ZhihuStoryModel z = JsonUtils.fromJson(string, ZhihuStoryModel.class);
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.zhihuURL, z.getBody());
        context.startActivity(intent);
    }
}
