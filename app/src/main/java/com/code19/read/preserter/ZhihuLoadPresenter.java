package com.code19.read.preserter;

import android.os.Handler;

import com.code19.read.domain.ZhihuModel;
import com.code19.read.model.IZhihuBiz;
import com.code19.read.model.OnZhihuLoadListener;
import com.code19.read.model.ZhihuBiz;
import com.code19.read.view.IZhihuView;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class ZhihuLoadPresenter {
    private IZhihuBiz mIZhihuBiz;
    private IZhihuView mIZhihuView;
    private Handler mHandler;

    public ZhihuLoadPresenter(IZhihuView iZhihuView) {
        this.mIZhihuBiz = new ZhihuBiz();
        this.mIZhihuView = iZhihuView;
        this.mHandler = new Handler();
    }

    public void getData() {
        mIZhihuBiz.getData(mIZhihuView.getUrl(), new OnZhihuLoadListener() {
            @Override
            public void loadSuccess(final ZhihuModel zhihuModel) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIZhihuView.referData(zhihuModel);
                        mIZhihuView.hideLoading();
                    }
                });

            }

            @Override
            public void loadFailed(final String tips) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIZhihuView.hideLoading();
                        mIZhihuView.showFailedError(tips);
                    }
                });
            }
        });
    }

}
