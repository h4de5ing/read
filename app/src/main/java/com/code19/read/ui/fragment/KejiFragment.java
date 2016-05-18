package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.code19.read.ApiConfig;
import com.code19.read.R;
import com.code19.read.model.NewModel;
import com.code19.read.preserter.NewsLoadPresenter;
import com.code19.read.ui.adapter.NewsListAdapter;
import com.code19.read.view.INewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class KejiFragment extends Fragment implements INewsView {
    private NewsLoadPresenter mNewsLoadPresenter;
    private List<NewModel.NewslistEntity> mData;
    private NewsListAdapter mAdapter;
    private ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress();
        mData = new ArrayList<NewModel.NewslistEntity>();
        mNewsLoadPresenter = new NewsLoadPresenter(this);
        mNewsLoadPresenter.getData();
    }

    private void initProgress() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("正在加载数据");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w("ghost", "在主线程中执行");
        }*/
        View v = inflater.inflate(R.layout.fragment_keji, null);
        ListView kejiListView = (ListView) v.findViewById(R.id.keji_listview);
        mAdapter = new NewsListAdapter(getActivity(), mData);
        kejiListView.setAdapter(mAdapter);
        return v;
    }


    @Override
    public String getUrl() {
        return ApiConfig.URL;
    }

    @Override
    public String getKeyWorld() {
        return "tiyu";
    }

    @Override
    public int getNum() {
        return 20;
    }

    @Override
    public int getPage() {
        return 1;
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
    public void refreData(NewModel newModel) {
        for (NewModel.NewslistEntity news : newModel.getNewslist()) {
            mData.add(news);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void loadMore() {

    }
}
