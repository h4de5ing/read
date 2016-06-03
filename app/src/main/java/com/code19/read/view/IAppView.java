package com.code19.read.view;

import com.code19.read.domain.AppModel;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public interface IAppView {
    void showLoading();
    void hideLoading();
    void referData(List<AppModel> list);
}
