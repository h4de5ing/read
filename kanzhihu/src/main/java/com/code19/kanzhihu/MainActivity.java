package com.code19.kanzhihu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.code19.kanzhihu.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.code19.kanzhihu.R.id.io;
import static com.code19.kanzhihu.R.id.nio;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(io)
    Button mIo;
    @BindView(nio)
    Button mNio;
    @BindView(R.id.ioresule)
    TextView mIoresule;
    @BindView(R.id.nioresult)
    TextView mNioresult;
    private String url = "http://gank.io/api/history/content/day/2016/08/02";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({io, nio})
    public void onClick(View view) {
        switch (view.getId()) {
            case io:
                break;
            case nio:
                break;
        }
    }

}
