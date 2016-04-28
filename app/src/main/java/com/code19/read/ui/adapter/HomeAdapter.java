package com.code19.read.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.code19.read.util.HomeFragmentFactory;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    String[] home_title;

    public HomeAdapter(FragmentManager fm, String[] title) {
        super(fm);
        home_title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return home_title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return home_title[position];
    }
}
