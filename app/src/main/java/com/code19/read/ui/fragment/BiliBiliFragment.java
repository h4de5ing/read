package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.read.ApiConfig;
import com.code19.read.R;
import com.code19.read.domain.BiliBiliModel;
import com.code19.read.preserter.BiliBiliLoadPresenter;
import com.code19.read.util.Utils;
import com.code19.read.view.IBibiBiliView;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class BiliBiliFragment extends Fragment implements IBibiBiliView {
    private static final String TAG = "ghost";
    private BiliBiliLoadPresenter mPresenter;
    private ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
        mPresenter = new BiliBiliLoadPresenter(this);
        mPresenter.getData();
    }

    private void initDialog() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage(Utils.getString(R.string.loading));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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
        if (!mDialog.isShowing())
            mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog.isShowing())
            mDialog.cancel();
    }

    @Override
    public void referData(BiliBiliModel biliBiliModel) {

    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
