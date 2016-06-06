package com.code19.read.model;

import android.support.annotation.Nullable;

import com.code19.library.JsonUtils;
import com.code19.library.NetUtils;
import com.code19.read.App;
import com.code19.read.R;
import com.code19.read.domain.BiliBiliModel;
import com.code19.read.util.Utils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public class BiliBiliBiz implements IBiliBiliBiz {
    private List<String> mTypeList;
    private List<BiliBiliModel> mModelList;

    @Override
    public void getBiliData(final String url, final OnBiliBiliLoadListener onBiliBiliLoadListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (NetUtils.isConnected(App.getContext())) {
                    OkHttpUtils.get(url)
                            .tag(this)
                            .cacheKey(url)
                            .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                            .execute(new StringCallback() {
                                @Override
                                public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                    onBiliBiliLoadListener.loadSuccess(getModel(s));
                                }
                            });
                } else {
                    onBiliBiliLoadListener.loadFailed(Utils.getString(R.string.check_networkd));
                }
            }
        }).start();
    }

    private List<BiliBiliModel> getModel(String json) {
        mTypeList = new ArrayList<>();
        mModelList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            Iterator<String> rootKeys = root.keys();
            while (rootKeys.hasNext()) {
                String next = rootKeys.next();
                mTypeList.add(root.getString(next));
            }  //将type类型剥离出来存放到字符串集合中

            //从集合中的剥离出0，1这些条目数，这里忽略了num这个字段，因为num的层次和item是同一个层次
            for (int i = 0; i < mTypeList.size(); i++) {
                JSONObject type = new JSONObject(mTypeList.get(i));
                Iterator<String> typeKeys = type.keys();
                while (typeKeys.hasNext()) {
                    String next = typeKeys.next();
                    if (type.getString(next).startsWith("{")) {
                        Utils.i(next,type.getString(next));//日志输出
                        BiliBiliModel model = JsonUtils.fromJson(type.getString(next), BiliBiliModel.class); //将item转化成Model实体
                        mModelList.add(model);//将model存入List<BiliBiliModel> mModelList; 集合中
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mModelList;
    }
}
