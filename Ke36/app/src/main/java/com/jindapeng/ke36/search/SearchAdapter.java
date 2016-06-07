package com.jindapeng.ke36.search;

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
 * Created by dllo on 16/5/24.
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private SearchBean searchBean;

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
        notifyDataSetChanged();
    }

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchBean == null ? 0 : searchBean.getData().getTotalCount();
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

        // 将数据加载到布局里
        String title = searchBean.getData().getData().get(position).getTitle();
        String author = searchBean.getData().getData().get(position).getUser().getName();
        long time = searchBean.getData().getData().get(position).getPublishTime();
        final String columnName = searchBean.getData().getData().get(position).getColumnName();
        String imageUrl = searchBean.getData().getData().get(position).getFeatureImg();
        // Picasso组件,用来加载图片
        Picasso.with(context).load(imageUrl).resize(150, 150).centerCrop().into(holder.imageView);

        holder.title.setText(title);
        holder.author.setText(author);
        // 将时间转换成HH:mm:ss格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = simpleDateFormat.format(new Date(Long.valueOf(time)));
        holder.time.setText(times);
        holder.columnName.setText(columnName);
        return convertView;
    }

    class ViewHolder {
        TextView title, author, time, columnName;
        ImageView imageView;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.item_title);
            author = (TextView) itemView.findViewById(R.id.item_author);
            time = (TextView) itemView.findViewById(R.id.item_time);
            columnName = (TextView) itemView.findViewById(R.id.item_column_name);
            imageView = (ImageView) itemView.findViewById(R.id.item_image_view);

        }

    }
}
