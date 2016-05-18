package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.code19.read.ApiConfig;
import com.code19.read.R;
import com.code19.read.domain.ZhihuModel;
import com.code19.read.preserter.ZhihuLoadPresenter;
import com.code19.read.view.IZhihuView;

import java.util.List;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class ZhihuDailyFragment extends Fragment implements IZhihuView {
    private Context mContext;
    private ZhihuLoadPresenter mLoadPresenter;
    private ProgressDialog mDialog;
    private ViewPager mZhihu_viewpager;
    private RecyclerView mZhihu_recy;
    private List<ZhihuModel.StoriesEntity> mStories;
    private List<ZhihuModel.TopStoriesEntity> mTop_stories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initProgress();
        mLoadPresenter = new ZhihuLoadPresenter(this);
        mLoadPresenter.getData();
    }

    private void initProgress() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("加载中...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu, null);
        mZhihu_viewpager = (ViewPager) view.findViewById(R.id.zhihu_viewapager);
        mZhihu_recy = (RecyclerView) view.findViewById(R.id.zhihu_recy);
        return view;
    }

    @Override
    public String getUrl() {
        return ApiConfig.ZhihuDiailyURL;
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
    public void referData(ZhihuModel zhihuModel) {
        mStories = zhihuModel.getStories();
        mTop_stories = zhihuModel.getTop_stories();
        //mZhihu_viewpager.setAdapter(new ImViewPager());
    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    class ImViewPager extends BaseAdapter {
        @Override
        public int getCount() {
            return mTop_stories != null ? mTop_stories.size() : null;
        }

        @Override
        public Object getItem(int position) {
            return mTop_stories != null ? mTop_stories.get(position) : 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.zhihu_viewpage, null);
            return view;
        }
    }
}
