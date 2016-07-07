package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.code19.read.ApiConfig;
import com.code19.read.R;
import com.code19.read.domain.NewModel;
import com.code19.read.preserter.NewsLoadPresenter;
import com.code19.read.ui.adapter.NewsListAdapter;
import com.code19.read.view.INewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class NewsFragment extends Fragment implements INewsView {
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
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setMessage(getString(R.string.loading));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //mNewsLoadPresenter.getData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w("ghost", "在主线程中执行");
        }*/
        View v = inflater.inflate(R.layout.fragment_news, null);
        ListView newsListView = (ListView) v.findViewById(R.id.listview_news);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("ghost", "点击:" + position);
                WebView webView = new WebView(getActivity());
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(mData.get(position).getUrl());
                        return true;
                    }
                });
            }
        });
        mAdapter = new NewsListAdapter(getActivity(), mData);
        newsListView.setAdapter(mAdapter);
        return v;
    }


    @Override
    public String getUrl() {
        return ApiConfig.NewsURL;
    }

    @Override
    public String getKeyWorld() {
        return "tiyu"; //加载体育新闻
    }

    @Override
    public int getNum() {
        return 20;  //一次加载20条数据
    }

    @Override
    public int getPage() {
        return 1; //默认加载第一页数据
    }

    @Override
    public void showLoading() {
        //mDialog.show();
    }

    @Override
    public void hideLoading() {
        //mDialog.cancel();
    }

    @Override
    public void refreData(NewModel newModel) {
        for (NewModel.NewslistEntity news : newModel.getNewslist()) {
            mData.add(news);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMore() {

    }
}
