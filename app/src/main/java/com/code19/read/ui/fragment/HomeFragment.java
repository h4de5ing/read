package com.code19.read.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code19.read.MainActivity;
import com.code19.read.R;
import com.code19.read.ui.adapter.HomeAdapter;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public class HomeFragment extends Fragment {
    private MainActivity mMainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] mHome_title = getActivity().getResources().getStringArray(R.array.home_title);
        View view = inflater.inflate(R.layout.fragment_home, container);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.home_tablayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_viewpager);
        viewPager.setAdapter(new HomeAdapter(mMainActivity.getSupportFragmentManager(), mHome_title));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }
}
