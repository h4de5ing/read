package com.code19.read.model;

import com.code19.read.domain.BiliBiliModel;

import java.util.List;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public interface OnBiliBiliLoadListener {

    void loadSuccess(List<BiliBiliModel> list);

    void loadFailed(String tips);
}
