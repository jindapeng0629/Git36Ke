package com.jindapeng.ke36.news.header;

import android.content.Context;
import android.support.v4.view.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jindapeng.ke36.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 */
public class NewsHeaderAdapter extends PagerAdapter {

    private List<HeaderBean> imageViews;
    private Context context;

    public NewsHeaderAdapter(Context context) {
        this.context = context;
    }

    public void setImageViews(List<HeaderBean> imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size() * 1000;
    }// 数组宽度

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.header_item_view, null);// 绑定布局
        // 加载图片
        ImageView iv = (ImageView) v.findViewById(R.id.item_imageview);
        Picasso.with(context).load(imageViews.get(position % imageViews.size()).getImage_url()).into(iv);

        container.addView(v);
        return v;
    }
}


