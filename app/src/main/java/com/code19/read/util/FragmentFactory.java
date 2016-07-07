package com.code19.read.util;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.code19.read.ui.fragment.AboutFragment;
import com.code19.read.ui.fragment.MeinvFragment;
import com.code19.read.ui.fragment.NewsFragment;
import com.code19.read.ui.fragment.ZhihuDailyFragment;

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
                fragment = new NewsFragment();
                break;
            case 1:
                fragment = new MeinvFragment();
                break;
            case 2:
                fragment = new ZhihuDailyFragment();
                break;
            case 3:
                fragment = new AboutFragment();
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}
