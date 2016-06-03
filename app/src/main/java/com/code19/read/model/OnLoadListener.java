package com.code19.read.model;

import com.code19.read.domain.NewModel;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public interface OnLoadListener {
    void loadSuccess(NewModel newModel);

    void loadFailed(String tips);
}
