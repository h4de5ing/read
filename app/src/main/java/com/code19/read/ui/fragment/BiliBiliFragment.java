package com.code19.read.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class BiliBiliFragment extends Fragment {
    private static final String TAG = "ghost";
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        Button tv = new Button(mContext);
        tv.setText("打开网络设置");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NetUtils.openNetSetting((MainActivity) mContext);
            }
        });

        return tv;
    }

}
