package com.code19.read.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.code19.read.R;
import com.code19.read.model.NewModel;

import java.util.List;

/**
 * Created by Gh0st on 2016/4/28 028.
 */
public class NewsListAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewModel.NewslistEntity> mData;

    public NewsListAdapter(Context context, List<NewModel.NewslistEntity> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_news, null);
            holder.pic = (ImageView) convertView.findViewById(R.id.news_item_pic);
            holder.Title = (TextView) convertView.findViewById(R.id.news_item_title);
            holder.des = (TextView) convertView.findViewById(R.id.news_item_desc);
            holder.cTime = (TextView) convertView.findViewById(R.id.news_item_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewModel.NewslistEntity newslistEntity = mData.get(position);
        //ImageUtils.setImageViewURL(newslistEntity.getPicUrl(), holder.pic);
        final ViewHolder finalHolder = holder;
   /*     OkHttpUtils.get(newslistEntity.getPicUrl())
                .tag(this)
                .execute(new BitmapCallback() {
                             @Override
                             public void onResponse(boolean isFromCache, Bitmap bitmap, Request request, @Nullable Response response) {
                                 finalHolder.pic.setImageBitmap(bitmap);
                             }
                         }
                );*/
        holder.pic.setImageResource(R.mipmap.ic_launcher);
        holder.Title.setText(newslistEntity.getTitle());
        holder.des.setText(newslistEntity.getDescription());
        holder.cTime.setText(newslistEntity.getCtime());
        return convertView;
    }

    class ViewHolder {
        TextView cTime;
        TextView Title;
        TextView des;
        ImageView pic;
    }
}
