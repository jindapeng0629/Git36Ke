package com.jindapeng.ke36.details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.author.AuthorBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/26.
 */
public class AuthorPopAdapter extends BaseAdapter {
    private AuthorBean authorBean;
    private Context context;

    public void setAuthorBean(AuthorBean authorBean) {
        this.authorBean = authorBean;
        notifyDataSetChanged();
    }

    public AuthorPopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView.setBackgroundColor(0x70000000);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(authorBean.getData().getLatestArticle().get(position).getTitle());
        Log.d("title", "  " + authorBean.getData().getLatestArticle().get(position).getTitle());
        String imageUrl = authorBean.getData().getLatestArticle().get(position).getFeatureImg();

        // Picasso组件,用来加载图片
        Picasso.with(context).load(imageUrl).into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView imageView;

        public ViewHolder(View itemView) {
            // 加载布局文件
            title = (TextView) itemView.findViewById(R.id.item_title);
            imageView = (ImageView) itemView.findViewById(R.id.item_image_view);

        }
    }
}
