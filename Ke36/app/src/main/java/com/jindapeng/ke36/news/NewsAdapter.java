package com.jindapeng.ke36.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/10.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private NewsBean newsBean;

    // 数据传到适配器
    public void setNewsBean(NewsBean newsBean) {
        this.newsBean = newsBean;
        Log.d("--------", newsBean.getData().getPageSize() + "+++++++++++");
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    // 获得的数据条数
    @Override
    public int getCount() {
        return newsBean == null ? 0 : newsBean.getData().getPageSize();
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
        String title = newsBean.getData().getData1().get(position).getTitle();
        String author = newsBean.getData().getData1().get(position).getUser().getName();
        long time = newsBean.getData().getData1().get(position).getPublishTime();
        final String columnName = newsBean.getData().getData1().get(position).getColumnName();
        String imageUrl = newsBean.getData().getData1().get(position).getFeatureImg();
        // Picasso组件,用来加载图片
        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.title.setText(title);
        holder.author.setText(author);
        // 将时间转换成HH:mm:ss格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = simpleDateFormat.format(new Date(Long.valueOf(time)));
        holder.time.setText(times);
        holder.columnName.setText(columnName);

        //改变columnName的字体颜色 不一样的columnName的字体是不一样的颜色
        if (columnName != null) {
            switch (columnName) {
                case "早期项目":
                    holder.columnName.setTextColor(0xff6cb96a);
                    break;
                case "B轮后":
                    holder.columnName.setTextColor(0xFFa1ccf5);
                    break;
                case "大公司":
                    holder.columnName.setTextColor(0xff71c2f4);
                    break;
                case "资本":
                    holder.columnName.setTextColor(0xffbb322d);
                    break;
                case "深度":
                    holder.columnName.setTextColor(0xffcf4c65);
                    break;
                case "研究":
                    holder.columnName.setTextColor(0xff80be42);
                    break;
                case "氪TV":
                    holder.columnName.setTextColor(0xffece92c);
                    break;
            }
        }

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
