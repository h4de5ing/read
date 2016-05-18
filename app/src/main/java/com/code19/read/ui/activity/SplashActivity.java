package com.code19.read.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.code19.read.MainActivity;
import com.code19.read.R;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class SplashActivity extends AppCompatActivity {

    private Splash mSplash;
    private ImageView mIv_splash;
    private TextView mTv_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIv_splash = (ImageView) findViewById(R.id.iv_splash);
        mTv_splash = (TextView) findViewById(R.id.tv_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }).start();
    }

    class Splash {


        /**
         * text : Matthew Wiebe
         * img : https://pic2.zhimg.com/90f85c1aae2ca0845540bd298303ab5b.jpg
         */

        private String text;
        private String img;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
