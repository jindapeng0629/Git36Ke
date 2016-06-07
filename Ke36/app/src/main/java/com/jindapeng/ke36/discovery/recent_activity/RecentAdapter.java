package com.jindapeng.ke36.discovery.recent_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 */
public class RecentAdapter extends BaseAdapter {
    private Context context;
    private RecentBean recentBean;

    public RecentAdapter(Context context) {
        this.context = context;
    }

    public void setRecentBean(RecentBean recentBean) {
        this.recentBean = recentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recentBean == null ? 0 : recentBean.getData().getPageSize();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_item_recent, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.activityCity.setText(recentBean.getData().getData1().get(position).getActivityCity());
        holder.activityDesc.setText(recentBean.getData().getData1().get(position).getActivityDesc());
        holder.activityName.setText(recentBean.getData().getData1().get(position).getActivityName());
        holder.activityStatus.setText(recentBean.getData().getData1().get(position).getActivityStatus());
        holder.activityTime.setText(recentBean.getData().getData1().get(position).getActivityTime());
        String image_url = recentBean.getData().getData1().get(position).getActivityImg();
        Picasso.with(context).load(image_url).into(holder.activityImg);

        return convertView;
    }

    class ViewHolder {
        ImageView activityImg;
        TextView activityCity, activityDesc, activityName, activityStatus, activityTime;

        public ViewHolder(View view) {
            activityImg = (ImageView) view.findViewById(R.id.activity_img);
            activityCity = (TextView) view.findViewById(R.id.activity_city);
            activityDesc = (TextView) view.findViewById(R.id.activity_desc);
            activityName = (TextView) view.findViewById(R.id.activity_name);
            activityStatus = (TextView) view.findViewById(R.id.activity_status);
            activityTime = (TextView) view.findViewById(R.id.activity_time);
        }
    }
}
