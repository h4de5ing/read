package com.code19.kanzhihu.utils;

import android.app.Activity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */

public class BaseAppManager {
    public static BaseAppManager mBaseAppManager;
    private static List<Activity> sActivityList;
    public static BaseAppManager getInstance() {
        if (mBaseAppManager == null) {
            mBaseAppManager = new BaseAppManager();
        }
        return mBaseAppManager;
    }
    public void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    public void finishAllActivity() {
        for (int i = 0; i < sActivityList.size(); i++) {
            if (sActivityList.get(i) != null) {
                sActivityList.get(i).finish();
            }
        }
        sActivityList.clear();
    }
}
