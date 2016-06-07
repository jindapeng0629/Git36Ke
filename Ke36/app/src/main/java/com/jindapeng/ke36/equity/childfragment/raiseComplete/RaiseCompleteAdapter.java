package com.jindapeng.ke36.equity.childfragment.raiseComplete;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.utils.RoundImageView;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/24.
 * 募资完成的适配器
 */
public class RaiseCompleteAdapter extends BaseAdapter {
    private Context context;
    private RaiseCompleteBean raiseCompleteBean;

    public void setRaiseCompleteBean(RaiseCompleteBean raiseCompleteBean) {
        this.raiseCompleteBean = raiseCompleteBean;
        Log.d("~~~~~~~~", raiseCompleteBean.getData().getPageSize() + "+++++++++++");
        notifyDataSetChanged();
    }

    public RaiseCompleteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return raiseCompleteBean == null ? 0 : raiseCompleteBean.getData().getPageSize();
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
        if (convertView == null)

        {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_item_equity, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else

        {
            holder = (ViewHolder) convertView.getTag();
        }

        String fileListImgUrl = raiseCompleteBean.getData().getData().get(position).getFile_list_img();
        String companyLogoUrl = raiseCompleteBean.getData().getData().get(position).getCompany_logo();
        Picasso.with(context).load(fileListImgUrl).into(holder.fileListImg);
        Picasso.with(context).load(companyLogoUrl).into(holder.companyLogo);

        holder.lead_name.setText(raiseCompleteBean.getData().getData().get(position).getLead_name());

        holder.adnameOne.setText(raiseCompleteBean.getData().getData().get(position).getCf_advantage().get(0).getAdname());
        holder.adcontentOne.setText(raiseCompleteBean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());

        holder.adcontentTwo.setText(raiseCompleteBean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());
        holder.adnameTwo.setText(raiseCompleteBean.getData().getData().get(position).getCf_advantage().get(1).getAdname());

        holder.companyBrief.setText(raiseCompleteBean.getData().getData().get(position).getCompany_brief());
        holder.companyName.setText(raiseCompleteBean.getData().getData().get(position).getCompany_name());
        holder.desc.setText(raiseCompleteBean.getData().getData().get(position).getFundStatus().getDesc());
        holder.crowdFundingStatus.setText(raiseCompleteBean.getData().getData().get(position).getFundStatus().getCrowd_funding_status());

        holder.trueBtn.setText("去看看");
        return convertView;
    }


    class ViewHolder {
        TextView adcontentTwo, adnameTwo, adcontentOne, adnameOne, lead_name, trueBtn,
                companyBrief, companyName, desc, crowdFundingStatus;
        ImageView fileListImg;
        RoundImageView companyLogo;

        public ViewHolder(View itemView) {
            lead_name = (TextView) itemView.findViewById(R.id.equity_adcontent_one);

            adcontentOne = (TextView) itemView.findViewById(R.id.equity_adcontent_two);
            adnameOne = (TextView) itemView.findViewById(R.id.equity_adname_two);

            adcontentTwo = (TextView) itemView.findViewById(R.id.equity_adcontent_three);
            adnameTwo = (TextView) itemView.findViewById(R.id.equity_adname_three);

            companyBrief = (TextView) itemView.findViewById(R.id.equity_company_brief);
            companyName = (TextView) itemView.findViewById(R.id.equity_company_name);
            desc = (TextView) itemView.findViewById(R.id.equity_desc);
            trueBtn = (TextView) itemView.findViewById(R.id.equity_true_btn);
            crowdFundingStatus = (TextView) itemView.findViewById(R.id.equity_crowd_funding_status);

            fileListImg = (ImageView) itemView.findViewById(R.id.equity_file_list_img);
            companyLogo = (RoundImageView) itemView.findViewById(R.id.equity_company_logo);
        }
    }
}
