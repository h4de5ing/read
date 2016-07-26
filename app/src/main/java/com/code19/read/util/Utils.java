package com.code19.read.util;

import android.content.Context;
import android.content.Intent;

import com.code19.library.JsonUtils;
import com.code19.read.App;
import com.code19.read.domain.ZhihuStoryModel;
import com.code19.read.ui.activity.WebViewActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

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
    public static void crash2File(Context context, String msg) {
        String absolutePath = context.getExternalCacheDir().getAbsolutePath();
        File file = new File(absolutePath+"/log_" + System.currentTimeMillis() + ".log");
        FileOutputStream trace = null;
        try {
            trace = new FileOutputStream(file, true);
            OutputStreamWriter writer = new OutputStreamWriter(trace, "utf-8");
            writer.write(msg);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
