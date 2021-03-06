package com.code19.read.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.code19.read.ApiConfig;
import com.code19.read.R;
import com.code19.read.domain.BiliBiliModel;
import com.code19.read.presenter.BiliBiliLoadPresenter;
import com.code19.read.view.IBibiBiliView;

import java.util.List;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class BiliBiliFragment extends Fragment implements IBibiBiliView {
    private BiliBiliLoadPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BiliBiliLoadPresenter(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.getData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bilibili, null);
        return view;
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
    public void referData(List<BiliBiliModel> list) {

    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
