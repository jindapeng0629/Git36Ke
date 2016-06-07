package com.jindapeng.ke36.mine.myCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.greenDao.Collection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by JINDAPENG on 2016/5/30.
 */
public class MyCollectionAdapter extends BaseAdapter {
    private List<Collection> collectionList;
    private Context context;

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
        notifyDataSetChanged();
    }

    public MyCollectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return collectionList == null ? 0 : collectionList.size();
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
        String title = collectionList.get(position).getTitle();
        String author = collectionList.get(position).getName();
        long time = collectionList.get(position).getPublishTime();
        final String columnName = collectionList.get(position).getColumnName();
        String imageUrl = collectionList.get(position).getFeatureImg();
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
