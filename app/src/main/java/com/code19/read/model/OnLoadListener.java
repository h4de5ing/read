package com.code19.read.model;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public interface OnLoadListener {
    void loadSuccess(NewModel newModel);

    void loadFailed();
}
