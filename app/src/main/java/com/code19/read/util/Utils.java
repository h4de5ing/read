package com.code19.read.util;

import com.code19.read.App;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public class Utils {
    public static String getString(int resId) {
        return App.getContext().getResources().getString(resId);
    }
}
