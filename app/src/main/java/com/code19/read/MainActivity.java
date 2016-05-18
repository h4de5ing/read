package com.code19.read;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.code19.read.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new HomeFragment()).commit();
    }
}
