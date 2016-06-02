package com.code19.read.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.library.CacheUtils;
import com.code19.library.NetUtils;
import com.code19.read.ApiConfig;
import com.code19.read.App;
import com.code19.read.MainActivity;
import com.code19.read.R;
import com.code19.read.util.PicassoUtils;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class SplashActivity extends AppCompatActivity {

    private Splash mSplash;
    private ImageView mIv_splash;
    private TextView mTv_splash;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        mIv_splash = (ImageView) findViewById(R.id.iv_splash);
        mTv_splash = (TextView) findViewById(R.id.tv_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //SystemClock.sleep(2000);
                if ("".equals(CacheUtils.getCache(App.getContext(), ApiConfig.ZhihuSplashURL))) { //为空,就从网络去加载
                    if (NetUtils.isConnected(SplashActivity.this)) {
                        OkHttpUtils.get(ApiConfig.ZhihuSplashURL)
                                .tag(this)
                                .cacheKey("Splash")
                                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                        set(s);
                                        CacheUtils.setCache(App.getContext(), ApiConfig.ZhihuSplashURL, s);
                                    }
                                });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SplashActivity.this, getString(R.string.check_networkd), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    String cache = CacheUtils.getCache(App.getContext(), ApiConfig.ZhihuSplashURL);
                    set(cache);
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }

    private void set(String s) {
        Gson gson = new Gson();
        mSplash = gson.fromJson(s, Splash.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PicassoUtils.loadImageWithCrop(mContext, mSplash.img, mIv_splash);
                mTv_splash.setText(mSplash.text);
            }
        });
    }

    class Splash {
        public String text;
        public String img;
    }
}
