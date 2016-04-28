package com.code19.read.util;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.code19.read.ui.gragment.KejiFragment;
import com.code19.read.ui.gragment.MeinvFragment;
import com.code19.read.ui.gragment.NewtopFragment;
import com.code19.read.ui.gragment.QiwenFragment;
import com.code19.read.ui.gragment.TiyuFragment;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class HomeFragmentFactory {
    private static SparseArray<Fragment> map = new SparseArray<Fragment>();

    public static Fragment getFragment(int position) {
        Fragment fragment = null;
        if (map.get(position, fragment) != null) {
            return map.get(position);
        }
        switch (position) {
            case 0:
                fragment = new KejiFragment();
                break;
            case 1:
                fragment = new TiyuFragment();
                break;
            case 2:
                fragment = new NewtopFragment();
                break;
            case 3:
                fragment = new QiwenFragment();
                break;
            case 4:
                fragment = new MeinvFragment();
                break;
        }
        map.put(position, fragment);
        return fragment;
    }

    public static int getFragmentCount() {
        return map.size();
    }
}
