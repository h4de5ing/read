package com.code19.read.view;

import com.code19.read.domain.BiliBiliModel;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public interface IBibiBiliView {
    String getUrl();

    void showLoading();

    void hideLoading();

    void referData(BiliBiliModel biliBiliModel);

    void showFailedError(String error);
}
