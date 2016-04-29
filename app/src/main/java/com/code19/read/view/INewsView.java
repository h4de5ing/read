package com.code19.read.view;

import com.code19.read.model.NewModel;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public interface INewsView {
    String getUrl();

    String getKeyWorld();

    int getNum();

    int getPage();

    void showLoading();

    void hideLoading();

    void refreData(NewModel newModel);

    void showFailedError();

    void loadMore();
}
