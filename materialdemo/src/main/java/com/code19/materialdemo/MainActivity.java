package com.code19.materialdemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navview = (NavigationView) findViewById(R.id.nav_view);
        navview.setNavigationItemSelectedListener(this);
        FrameLayout fragmentcontainer = (FrameLayout) findViewById(R.id.fragment_container);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }
}
