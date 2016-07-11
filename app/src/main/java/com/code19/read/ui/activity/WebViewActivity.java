package com.code19.read.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.code19.read.R;

public class WebViewActivity extends AppCompatActivity {
    public static String httpURL = "URL";
    public static String zhihuURL = "zhihuurl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.setWebChromeClient(new WebChromeClient());
        Intent intent = getIntent();
        if (TextUtils.isEmpty(intent.getStringExtra(httpURL))) {
            webview.loadData(intent.getStringExtra(zhihuURL), "text/html; charset=UTF-8", "utf-8");
        } else {
            webview.loadUrl(getIntent().getStringExtra(httpURL));
        }
    }
}
