package com.code19.read.ui.gragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public abstract class BaseFragment extends Fragment {
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 通常用于初始化Fragment数据
     */
    public abstract void init();

    /**
     * 通常用于初始化Fragment中的View
     *
     * @return
     */
    public abstract View initView();
}
