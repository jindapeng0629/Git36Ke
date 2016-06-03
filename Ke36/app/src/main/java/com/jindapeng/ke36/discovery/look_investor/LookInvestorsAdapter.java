package com.jindapeng.ke36.discovery.look_investor;

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
 * 寻找投资人
 */
public class LookInvestorsAdapter extends BaseAdapter {
    private Context context;
    private LookInvestorsBean lookInvestorsBean;

    public LookInvestorsAdapter(Context context) {
        this.context = context;
    }

    public void setLookInvestorsBean(LookInvestorsBean lookInvestorsBean) {
        this.lookInvestorsBean = lookInvestorsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lookInvestorsBean == null ? 0 : lookInvestorsBean.getData().getPageSize();
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
                    .inflate(R.layout.listview_item_look_investor, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(lookInvestorsBean.getData().getData1().get(position).getUser().getName());
        holder.focusIndustry.setText("关注领域:   " + lookInvestorsBean.getData().getData1().get(position).getFocusIndustry());
        holder.investPhases.setText("投资阶段:   " + lookInvestorsBean.getData().getData1().get(position).getInvestPhases());
        String image_url = lookInvestorsBean.getData().getData1().get(position).getUser().getAvatar();

        Picasso.with(context).load(image_url).error(R.mipmap.ic_launcher).into(holder.avatar);
        return convertView;

    }

    class ViewHolder {
        ImageView avatar;
        TextView name, investPhases, focusIndustry;

        public ViewHolder(View view) {
            // 加载布局文件
            avatar = (ImageView) view.findViewById(R.id.looking_investors_image);
            name = (TextView) view.findViewById(R.id.looking_investors_name);
            investPhases = (TextView) view.findViewById(R.id.tv_look_invest);
            focusIndustry = (TextView) view.findViewById(R.id.tv_look_foucs);
        }
    }
}
