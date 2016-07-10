package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.library.CacheUtils;
import com.code19.library.NetUtils;
import com.code19.read.ApiConfig;
import com.code19.read.App;
import com.code19.read.R;
import com.code19.read.domain.ZhihuModel;
import com.code19.read.domain.ZhihuStoryModel;
import com.code19.read.preserter.ZhihuLoadPresenter;
import com.code19.read.ui.activity.WebViewActivity;
import com.code19.read.ui.adapter.ZhihuRecyAdapter;
import com.code19.read.util.PicassoUtils;
import com.code19.read.view.IZhihuView;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import static com.code19.library.CacheUtils.getCache;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class ZhihuDailyFragment extends Fragment implements IZhihuView, ViewPager.OnPageChangeListener {
    private Context mContext;
    private ZhihuLoadPresenter mLoadPresenter;
    private ProgressDialog mDialog;
    private ViewPager mZhihu_viewpager;
    private RecyclerView mZhihu_recy;
    private List<ZhihuModel.StoriesEntity> mStories;
    private List<ZhihuModel.TopStoriesEntity> mTop_stories;
    private LinearLayout mLl_dot_view;
    private int priposition = 0;
    private List<ImageView> ivList;
    private TextView mTv_desc;
    private static String s = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initProgress();
        mLoadPresenter = new ZhihuLoadPresenter(this);
        mLoadPresenter.getData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mLoadPresenter.getData();
        }
    }

    private void initProgress() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(getString(R.string.loading));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu, null);
        mZhihu_viewpager = (ViewPager) view.findViewById(R.id.zhihu_viewapager);
        mLl_dot_view = (LinearLayout) view.findViewById(R.id.zhihu_vp_dot_view);
        mTv_desc = (TextView) view.findViewById(R.id.zhihu_vp_disc);
        mZhihu_recy = (RecyclerView) view.findViewById(R.id.zhihu_recy);
        mZhihu_recy.setLayoutManager(new LinearLayoutManager(mContext));
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
        initViewPager();
        mZhihu_viewpager.setAdapter(new ZhihuViewPagerAdapter());
        mZhihu_viewpager.addOnPageChangeListener(this);
        ZhihuRecyAdapter adapter = new ZhihuRecyAdapter(mContext, mStories);
        mZhihu_recy.setAdapter(adapter);
    }

    private void initViewPager() {
        ivList = new ArrayList<>();
        View dot;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < mTop_stories.size(); i++) {
            ImageView iv = new ImageView(mContext);
            PicassoUtils.loadImageWithHolder(mContext, mTop_stories.get(i).getImage(), R.mipmap.ic_launcher, iv);
            ivList.add(iv);
            //添加一个小圆点
            params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 10;
            dot = new View(mContext);
            dot.setEnabled(false);
            dot.setBackgroundResource(R.drawable.dot_view_backaground);
            dot.setLayoutParams(params);
            mLl_dot_view.addView(dot);
        }
        mLl_dot_view.getChildAt(0).setEnabled(true);
        mZhihu_viewpager.setCurrentItem(0);
        mTv_desc.setText(mTop_stories.get(0).getTitle());
    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //L.i("位置", positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        int newpostion = position % mTop_stories.size();
        mLl_dot_view.getChildAt(priposition).setEnabled(false);
        mLl_dot_view.getChildAt(newpostion).setEnabled(true);
        mTv_desc.setText(mTop_stories.get(newpostion).getTitle());
        priposition = newpostion;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ZhihuViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mTop_stories != null ? mTop_stories.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = ivList.get(position % mTop_stories.size());
            view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            final String url = ApiConfig.ZhihuDialyNewUrl + mStories.get(position).getId();
                                            if (NetUtils.isConnected(App.getContext())) {
                                                if (!TextUtils.isEmpty(getCache(App.getContext(), url))) {
                                                    s = CacheUtils.getCache(App.getContext(), url);
                                                } else {
                                                    OkHttpUtils.get(url)
                                                            .tag(this)
                                                            .cacheKey(url)
                                                            .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                                                            .execute(new StringCallback() {
                                                                @Override
                                                                public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                                                    ZhihuDailyFragment.s = s;
                                                                    CacheUtils.setCache(App.getContext(), url, s);  //设置缓存
                                                                }
                                                            });
                                                }
                                                Gson gson = new Gson();
                                                ZhihuStoryModel z = gson.fromJson(s, ZhihuStoryModel.class);
                                                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                                                intent.putExtra(WebViewActivity.zhihuURL, z.getBody());
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getActivity(), getString(R.string.check_networkd), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

            );
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView(ivList.get(position % mTop_stories.size()));
        }
    }
}
