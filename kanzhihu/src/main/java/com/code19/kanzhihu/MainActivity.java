package com.code19.kanzhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,AboutActivity.class));
        Log.i(TAG, "MainOnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainOnStart");
    }
}
