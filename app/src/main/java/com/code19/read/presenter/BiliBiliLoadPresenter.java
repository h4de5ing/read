package com.code19.read.presenter;

import android.os.Handler;

import com.code19.read.domain.BiliBiliModel;
import com.code19.read.model.BiliBiliBiz;
import com.code19.read.model.IBiliBiliBiz;
import com.code19.read.model.OnBiliBiliLoadListener;
import com.code19.read.view.IBibiBiliView;

import java.util.List;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public class BiliBiliLoadPresenter {
    private IBiliBiliBiz mIBiliBiliBiz;
    private IBibiBiliView mIBibiBiliView;
    private Handler mHandler;

    public BiliBiliLoadPresenter(IBibiBiliView iBibiBiliView) {
        this.mIBiliBiliBiz = new BiliBiliBiz();
        this.mIBibiBiliView = iBibiBiliView;
        this.mHandler = new Handler();
    }

    public void getData() {
        mIBibiBiliView.showLoading();
        mIBiliBiliBiz.getBiliData(mIBibiBiliView.getUrl(), new OnBiliBiliLoadListener() {
            @Override
            public void loadSuccess(final List<BiliBiliModel> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIBibiBiliView.referData(list);
                        mIBibiBiliView.hideLoading();
                    }
                });
            }

            @Override
            public void loadFailed(String tips) {
                mIBibiBiliView.hideLoading();
                mIBibiBiliView.showFailedError(tips);
            }
        });
    }
}
