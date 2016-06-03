package com.code19.read.model;

import com.code19.read.domain.BiliBiliModel;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public interface OnBiliBiliLoadListener {

    void loadSuccess(BiliBiliModel biliBiliModel);

    void loadFailed(String tips);
}
