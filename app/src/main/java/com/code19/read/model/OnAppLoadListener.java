package com.code19.read.model;

import com.code19.read.domain.AppModel;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public interface OnAppLoadListener {

    void loadFinish(List<AppModel> list);
}
