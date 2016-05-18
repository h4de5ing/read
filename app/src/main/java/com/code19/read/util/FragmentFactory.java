package com.code19.read.util;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.code19.read.ui.fragment.AboutFragment;
import com.code19.read.ui.fragment.HomeFragment;
import com.code19.read.ui.fragment.NewsFragment;
import com.code19.read.ui.fragment.BiliBiliFragment;
import com.code19.read.ui.fragment.ZhihuDailyFragment;
import com.code19.read.ui.fragment.ToolsFragment;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public class FragmentFactory {
    private static SparseArray<Fragment> map = new SparseArray<Fragment>();

    public static Fragment getFragment(int position) {
        Fragment fragment = null;
        if (map.get(position, fragment) != null) {
            return map.get(position);
        }
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new ToolsFragment();
                break;
            case 2:
                fragment = new AboutFragment();
                break;
            case 3:
                fragment = new NewsFragment();
                break;
            case 4:
                fragment = new ZhihuDailyFragment();
                break;
            case 5:
                fragment = new BiliBiliFragment();
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}
