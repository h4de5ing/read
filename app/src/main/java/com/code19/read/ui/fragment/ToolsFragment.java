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

import com.code19.read.R;
import com.code19.read.model.AppModel;
import com.code19.read.preserter.AppLoadPresenter;
import com.code19.read.ui.adapter.ToolsRecyAdapter;
import com.code19.read.view.IAppView;

import java.util.List;

/**
 * Created by Gh0st on 2016/4/26 026.
 */
public class ToolsFragment extends Fragment implements IAppView {

    private AppLoadPresenter mAppLoadPresenter;
    private ProgressDialog mDialog;
    private List<AppModel> mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress(); //初始化加载进度条
        mAppLoadPresenter = new AppLoadPresenter(this);
        mAppLoadPresenter.getData();//真正开始加载数据

    }

    private void initProgress() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("正在加载...");
        mDialog.setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_tools, null);
        RecyclerView tools_list = (RecyclerView) view.findViewById(R.id.recy_tools_list);
        tools_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tools_list.setAdapter(new ToolsRecyAdapter(getContext(), mData));
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
    }
}
