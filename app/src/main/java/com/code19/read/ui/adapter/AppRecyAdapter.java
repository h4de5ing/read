package com.code19.read.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code19.library.FileUtils;
import com.code19.read.R;
import com.code19.read.domain.AppModel;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class AppRecyAdapter extends RecyclerView.Adapter<AppRecyAdapter.ToolsViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<AppModel> mDatas;
    private Context mContext;

    public AppRecyAdapter(Context context, List<AppModel> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = list;
    }

    @Override
    public ToolsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToolsViewHolder(mLayoutInflater.inflate(R.layout.fragment_tools_recy, parent, false));
    }

    @Override
    public void onBindViewHolder(ToolsViewHolder holder, int position) {
        holder.tv_appName.setText(mDatas.get(position).getAppName());
        holder.iv_appIcon.setImageDrawable(mDatas.get(position).getAppIcon());
        holder.tv_appSize.setText(mDatas.get(position).getAppSize());
        holder.tv_appDate.setText(mDatas.get(position).getAppDate());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ToolsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_appName;
        ImageView iv_appIcon;
        TextView tv_appSize;
        TextView tv_appDate;

        public ToolsViewHolder(View itemView) {
            super(itemView);
            tv_appName = (TextView) itemView.findViewById(R.id.recy_app_name);
            iv_appIcon = (ImageView) itemView.findViewById(R.id.recy_app_icon);
            tv_appSize = (TextView) itemView.findViewById(R.id.recy_app_size);
            tv_appDate = (TextView) itemView.findViewById(R.id.recy_app_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileUtils.shareFile(mContext, "分享文件", mDatas.get(getAdapterPosition()).getAppApk());
                }
            });
        }
    }
}
