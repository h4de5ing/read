package com.code19.read.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.library.CacheUtils;
import com.code19.library.NetUtils;
import com.code19.read.App;
import com.code19.read.R;
import com.code19.read.domain.ZhihuModel;
import com.code19.read.util.Utils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import static com.code19.library.CacheUtils.getCache;
import static com.code19.read.ApiConfig.ZhihuDialyNewUrl;

/**
 * Create by h4de5ing 2016/5/20 020
 */
public class ZhihuRecyAdapter extends RecyclerView.Adapter<ZhihuRecyAdapter.ZhihuViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<ZhihuModel.StoriesEntity> mStories;
    private Context mContext;

    public ZhihuRecyAdapter(Context context, List<ZhihuModel.StoriesEntity> stories) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mStories = stories;
    }

    @Override
    public ZhihuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZhihuViewHolder(mLayoutInflater.inflate(R.layout.fragment_zhihu_recy, parent, false));
    }

    @Override
    public void onBindViewHolder(ZhihuViewHolder holder, int position) {
        holder.tv.setText(mStories.get(position).getTitle());
        holder.iv.setImageResource(R.mipmap.ic_launcher);
        String s = mStories.get(position).getImages().get(0);
        //L.i(s);
        //PicassoUtils.loadImageWithCrop(mContext,sString,holder.iv);
        //PicassoUtils.loadImageWithHolder(mContext, sString, R.mipmap.ic_launcher, holder.iv);
    }


    @Override
    public int getItemCount() {
        return mStories.size();
    }

    class ZhihuViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public ZhihuViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.zhihu_recy_tv);
            iv = (ImageView) itemView.findViewById(R.id.zhihu_recy_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String url = ZhihuDialyNewUrl + mStories.get(getAdapterPosition()).getId();
                    if (NetUtils.isConnected(App.getContext())) {
                        if (!TextUtils.isEmpty(getCache(mContext, url))) {
                            Utils.startZhihuWebAcitivty(mContext, getCache(mContext, url));
                        } else {
                            OkHttpUtils.get(url)
                                    .tag(this)
                                    .cacheKey(url)
                                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                            CacheUtils.setCache(App.getContext(), url, s);  //设置缓存
                                            Utils.startZhihuWebAcitivty(mContext, s);
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.check_networkd), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
