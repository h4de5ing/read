package com.code19.read.model;

import com.code19.read.domain.ZhihuModel;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public interface OnZhihuLoadListener {
    void loadSuccess(ZhihuModel zhihuModel);

    void loadProgress();

    void loadFailed(String tips);
}
