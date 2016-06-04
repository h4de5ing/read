package com.code19.read.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.read.ApiConfig;
import com.code19.read.domain.BiliBiliModel;
import com.code19.read.preserter.BiliBiliLoadPresenter;
import com.code19.read.view.IBibiBiliView;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class BiliBiliFragment extends Fragment implements IBibiBiliView {
    private static final String TAG = "ghost";
    private BiliBiliLoadPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BiliBiliLoadPresenter(this);
        mPresenter.getData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new TextView(getActivity());
    }

    @Override
    public String getUrl() {
        return ApiConfig.BILIBILIURL;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void referData(BiliBiliModel biliBiliModel) {

    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
