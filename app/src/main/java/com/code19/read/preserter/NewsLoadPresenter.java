package com.code19.read.preserter;

import android.os.Handler;

import com.code19.read.model.INewsBiz;
import com.code19.read.domain.NewModel;
import com.code19.read.model.NewsBiz;
import com.code19.read.model.OnLoadListener;
import com.code19.read.view.INewsView;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public class NewsLoadPresenter {
    private INewsBiz mINewsBiz;
    private INewsView mINewsView;
    private Handler mHandler;

    public NewsLoadPresenter(INewsView iNewsView) {
        this.mINewsView = iNewsView;
        this.mINewsBiz = new NewsBiz();
        this.mHandler = new Handler();
    }

    public void getData() {
        mINewsBiz.getData(mINewsView.getUrl(), mINewsView.getKeyWorld(), mINewsView.getNum(), mINewsView.getPage(), new OnLoadListener() {
            @Override
            public void loadSuccess(final NewModel newModel) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mINewsView.refreData(newModel);
                        mINewsView.hideLoading();
                    }
                });
            }

            @Override
            public void loadProgress() {
                mINewsView.showLoading();
            }

            @Override
            public void loadFailed(final String tips) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mINewsView.showFailedError(tips);
                        mINewsView.hideLoading();
                    }
                });
            }
        });
    }

}