package com.code19.read.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.read.ApiConfig;
import com.code19.read.R;
import com.code19.read.domain.ZhihuModel;
import com.code19.read.preserter.ZhihuLoadPresenter;
import com.code19.read.ui.adapter.ZhihuRecyAdapter;
import com.code19.read.util.PicassoUtils;
import com.code19.read.view.IZhihuView;

import java.util.ArrayList;
import java.util.List;

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
        mZhihu_recy.setAdapter(new ZhihuRecyAdapter(mContext, mStories));
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
                    Toast.makeText(mContext, "点击了" + position + "张图片", Toast.LENGTH_SHORT).show();
                }
            });
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
