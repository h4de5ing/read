package com.code19.read.view;

import com.code19.read.domain.ZhihuModel;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public interface IZhihuView {
    String getUrl();

    void showLoading();

    void hideLoading();

    void referData(ZhihuModel zhihuModel);

    void showFailedError(String error);
}
