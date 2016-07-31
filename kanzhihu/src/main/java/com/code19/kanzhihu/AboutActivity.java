package com.code19.kanzhihu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AboutActivity extends AppCompatActivity {
    private static final String TAG = "AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Log.i(TAG, "AboutOnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "AboutOnStart");
    }
}
