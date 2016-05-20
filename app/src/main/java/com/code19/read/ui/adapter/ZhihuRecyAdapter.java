package com.code19.read.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code19.read.R;
import com.code19.read.domain.ZhihuModel;
import com.code19.read.util.PicassoUtils;

import java.util.List;

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
        String s = mStories.get(position).getImages().get(position);
        Log.i("ghost", "第" + position + "张图片，地址" + s);
        PicassoUtils.loadImageWithHodler(mContext, s, R.mipmap.ic_launcher, holder.iv);
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
                    Toast.makeText(mContext, "点击了" + v.getId(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
