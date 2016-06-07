package com.jindapeng.ke36.discovery;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.discovery.look_investor.LookInvestorsActivity;
import com.jindapeng.ke36.discovery.recent_activity.RecentActivity;
import com.jindapeng.ke36.search.SearchActivity;

/**
 * Created by dllo on 16/5/9.
 * 发现页面
 */
public class DiscoveryFragment extends BaseFragment implements View.OnClickListener {
    private ImageView depthImage, startImage, explosiveImage, emergenceImage, dynamicImage,
            investorImage;
    private TextView depthTv, startTv, explosiveTv, emergenceTv, dynamicTv,
            investorTv;

    @Override
    public int setLayout() {
        return R.layout.fragment_discovery;
    }

    @Override
    public void initView(View view) {
        depthImage = bindView(R.id.discovery_icon_depth);
        startImage = bindView(R.id.discovery_icon_startup);
        explosiveImage = bindView(R.id.discovery_iv_explosive);
        emergenceImage = bindView(R.id.discovery_iv_emergence);
        dynamicImage = bindView(R.id.discovery_iv_dynamic);
        investorImage = bindView(R.id.discovery_iv_investor);


        depthTv = bindView(R.id.discovery_tv_depth);
        startTv = bindView(R.id.discovery_tv_startup);
        explosiveTv = bindView(R.id.discovery_tv_explosive);
        emergenceTv = bindView(R.id.discovery_tv_emergence);
        dynamicTv = bindView(R.id.discovery_tv_dynamic);
        investorTv = bindView(R.id.discovery_tv_investor);

        // 对象对布局进行监听,使整行都有点击效果
        RelativeLayout rlRecent = (RelativeLayout) view.findViewById(R.id.discovery_rl_recent_activities);
        rlRecent.setOnClickListener(this);
        RelativeLayout rlLook = (RelativeLayout) view.findViewById(R.id.discovery_rl_looking_investors);
        rlLook.setOnClickListener(this);
        RelativeLayout rlSearch = (RelativeLayout) view.findViewById(R.id.discovery_rl_search);
        rlSearch.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discovery_rl_recent_activities:
                Intent intentRecent = new Intent(getContext(), RecentActivity.class);
                getActivity().startActivity(intentRecent);
                break;
            case R.id.discovery_rl_looking_investors:
                Intent intentLook = new Intent(getContext(), LookInvestorsActivity.class);
                getActivity().startActivity(intentLook);
                break;
            case R.id.discovery_rl_search:
                Intent intentSearch = new Intent(getContext(), SearchActivity.class);
                getActivity().startActivity(intentSearch);
        }
    }
}
