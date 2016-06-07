package com.code19.read.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.code19.read.R;
import com.code19.read.domain.AppModel;
import com.code19.read.preserter.AppLoadPresenter;
import com.code19.read.ui.adapter.AppRecyAdapter;
import com.code19.read.view.IAppView;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppManagerActivity extends AppCompatActivity implements IAppView {
    private AppLoadPresenter mAppLoadPresenter;
    private ProgressDialog mDialog;
    private List<AppModel> mData;
    private AppRecyAdapter mRecyAdapter;
    private RecyclerView mTools_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appmanager);
        initProgress(); //初始化加载进度条
        mAppLoadPresenter = new AppLoadPresenter(this);
        mAppLoadPresenter.getData();//异步加载数据
        mTools_list = (RecyclerView) findViewById(R.id.recy_tools_list);
        mTools_list.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initProgress() {
        mDialog = new ProgressDialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setMessage(getString(R.string.loading));
    }

    @Override
    public void showLoading() {
        if (!mDialog.isShowing())
            mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog.isShowing())
            mDialog.dismiss();
    }

    @Override
    public void referData(List<AppModel> list) {
        mData = list;
        mRecyAdapter = new AppRecyAdapter(this, mData);
        mTools_list.setAdapter(mRecyAdapter);
        mRecyAdapter.notifyDataSetChanged();
    }
}
