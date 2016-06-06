package com.code19.read.view;

import com.code19.read.domain.BiliBiliModel;

import java.util.List;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public interface IBibiBiliView {
    String getUrl();

    void showLoading();

    void hideLoading();

    void referData(List<BiliBiliModel> list);

    void showFailedError(String error);
}
