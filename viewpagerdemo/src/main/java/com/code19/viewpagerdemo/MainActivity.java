package com.code19.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int[] imags;
    private String[] desc;
    private List<ImageView> list;
    private LinearLayout mLl_dot_view;
    private int priposition = 0;
    private TextView mTv_desc;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_img);
        mLl_dot_view = (LinearLayout) findViewById(R.id.ll_dot_view);
        mTv_desc = (TextView) findViewById(R.id.viewpager_disc);
        initData();
        mViewPager.setAdapter(new MyAdapter());
        mViewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        imags = new int[]{
                R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3,
                R.drawable.a4,
                R.drawable.a5,
        };
        desc = getResources().getStringArray(R.array.desc);
        list = new ArrayList<ImageView>();
        View dot;
        LinearLayout.LayoutParams params;
        for (int id : imags) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(id);
            list.add(iv);

            //添加一个小圆点
            params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 10;
            dot = new View(this);
            dot.setEnabled(false);
            dot.setBackgroundResource(R.drawable.dot_view_backaground);
            dot.setLayoutParams(params);
            mLl_dot_view.addView(dot);
        }
        mTv_desc.setText(desc[0]);
        mLl_dot_view.getChildAt(priposition).setEnabled(true);
        //设置Pager的当前也
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % imags.length;
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newpostion = position % imags.length;
        mLl_dot_view.getChildAt(priposition).setEnabled(false);
        mLl_dot_view.getChildAt(newpostion).setEnabled(true);
        mTv_desc.setText(desc[position]);
        priposition = newpostion;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imags.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = list.get(position % list.size());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position % list.size()));
        }
    }
}
