package com.code19.read.view;

import com.code19.read.domain.NewModel;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public interface INewsView {
    //总结,接口中的方法,没有返回值的一般都有参数
    String getUrl();

    String getKeyWorld();

    int getNum();

    int getPage();

    void showLoading();

    void hideLoading();

    void refreData(NewModel newModel);

    void showFailedError(String error);

    void loadMore();
}
