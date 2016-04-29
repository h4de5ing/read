package com.code19.read.preserter;

import android.os.Handler;

import com.code19.read.model.INewsBiz;
import com.code19.read.model.NewModel;
import com.code19.read.model.NewsBiz;
import com.code19.read.model.OnLoadListener;
import com.code19.read.view.INewsView;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public class NewsLoadPresenter {
    private INewsBiz mINewsBiz;
    private INewsView mINewsView;
    private Handler mHandler = new Handler();

    public NewsLoadPresenter(INewsView iNewsView) {
        this.mINewsView = iNewsView;
        this.mINewsBiz = new NewsBiz();
    }

    public void getData() {
        mINewsView.showLoading();
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
            public void loadFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mINewsView.showFailedError();
                        mINewsView.hideLoading();
                    }
                });
            }
        });
    }

}