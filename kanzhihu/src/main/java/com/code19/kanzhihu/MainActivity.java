package com.code19.kanzhihu;

import android.os.Bundle;
import android.widget.Button;

import com.code19.kanzhihu.ui.activity.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private String url = "http://gank.io/api/history/content/day/2016/08/02";

    @BindView(R.id.test)
    Button mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void getData() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
}
