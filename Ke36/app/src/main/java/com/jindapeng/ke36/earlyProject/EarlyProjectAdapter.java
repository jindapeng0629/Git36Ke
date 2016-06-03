package com.jindapeng.ke36.earlyProject;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/12.
 */
public class EarlyProjectAdapter extends BaseAdapter {
    private Context context;
    private EarlyProjectBean earlyProjectBean;

    public EarlyProjectAdapter(Context context) {
        this.context = context;
    }

    public void setEarlyProjectBean(EarlyProjectBean earlyProjectBean) {
        this.earlyProjectBean = earlyProjectBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return earlyProjectBean == null ? 0 : earlyProjectBean.getData().getPageSize();
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_item_news, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(earlyProjectBean.getData().getData1().get(position).getTitle());
        holder.author.setText(earlyProjectBean.getData().getData1().get(position).getUser().getName());
        long time = earlyProjectBean.getData().getData1().get(position).getPublishTime();
        String imageurl = earlyProjectBean.getData().getData1().get(position).getFeatureImg();
        // 将时间转换成HH:mm:ss格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String times = simpleDateFormat.format(new Date(time));
        holder.time.setText(times);
        Picasso.with(context).load(imageurl).into(holder.imageView);

        return convertView;
    }

    class ViewHolder {
        TextView title, author, time;
        ImageView imageView;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.item_title);
            author = (TextView) view.findViewById(R.id.item_author);
            time = (TextView) view.findViewById(R.id.item_time);
            imageView = (ImageView) view.findViewById(R.id.item_image_view);
        }
    }
}