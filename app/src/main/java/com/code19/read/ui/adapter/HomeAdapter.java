package com.code19.read.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.code19.read.util.FragmentFactory;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentFactory.getFragmentCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
