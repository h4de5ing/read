package com.code19.read.model;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public interface OnAppLoadListener {
    void loadProgress();

    void loadFinish(List<AppModel> list);
}
