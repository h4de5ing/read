package com.code19.read.preserter;

import android.os.Handler;

import com.code19.read.model.AppBiz;
import com.code19.read.domain.AppModel;
import com.code19.read.model.IAppBiz;
import com.code19.read.model.OnAppLoadListener;
import com.code19.read.view.IAppView;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppLoadPresenter {
    private IAppBiz mIAppBiz;
    private IAppView mIAppView;
    private Handler mHandler;

    public AppLoadPresenter(IAppView iAppView) {
        this.mIAppView = iAppView;
        this.mIAppBiz = new AppBiz();
        this.mHandler = new Handler();
    }

    public void getData() {
        mIAppView.showLoading();
        mIAppBiz.getAppInfo(new OnAppLoadListener() {
            @Override
            public void loadFinish(final List<AppModel> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIAppView.referData(list);
                        mIAppView.hideLoading();
                    }
                });

            }
        });
    }
}
