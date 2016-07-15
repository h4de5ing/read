package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.code19.read.R;
import com.code19.read.domain.AppModel;
import com.code19.read.preserter.AppLoadPresenter;
import com.code19.read.ui.adapter.AppRecyAdapter;
import com.code19.read.view.IAppView;

import java.util.List;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public class ToolsFragment extends Fragment implements IAppView {
    private AppLoadPresenter mAppLoadPresenter;
    private ProgressDialog mDialog;
    private List<AppModel> mData;
    private AppRecyAdapter mRecyAdapter;
    private RecyclerView mTools_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress(); //初始化加载进度条
        mAppLoadPresenter = new AppLoadPresenter(this);
        mAppLoadPresenter.getData();//异步加载数据
    }

    private void initProgress() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setMessage(getString(R.string.loading));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mAppLoadPresenter.getData();//异步加载数据
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_appmanager, null);
        mTools_list = (RecyclerView) view.findViewById(R.id.recy_tools_list);
        mTools_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.cancel();
    }

    @Override
    public void referData(List<AppModel> list) {
        mData = list;
        mRecyAdapter = new AppRecyAdapter(getActivity(), mData);
        mTools_list.setAdapter(mRecyAdapter);
        mRecyAdapter.notifyDataSetChanged();
    }
}
