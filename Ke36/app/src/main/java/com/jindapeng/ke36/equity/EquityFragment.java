package com.jindapeng.ke36.equity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.equity.childfragment.all.AllFragment;
import com.jindapeng.ke36.equity.childfragment.financ.FinancingFragment;
import com.jindapeng.ke36.equity.childfragment.raiseComplete.RaiseCompleteFragment;
import com.jindapeng.ke36.equity.childfragment.toRaise.ToRaiseFragment;
import com.jindapeng.ke36.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 * 股权投资页面
 */
public class EquityFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EquityAdapter equityAdapter;
    private List<Fragment> fragmentList;
    private ImageView searchImg;

    @Override
    public int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    public void initView(View view) {
        tabLayout = bindView(R.id.equity_tab);
        viewPager = bindView(R.id.equity_viewpager);
        searchImg = bindView(R.id.iv_icon_search);
        searchImg.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //把fragment数据初始化
        initFragments();
        //把数据放到adapter里
        equityAdapter = new EquityAdapter(getActivity().getSupportFragmentManager());
        equityAdapter.setFragmentList(fragmentList);// 集合放到适配器
        viewPager.setAdapter(equityAdapter);// 绑定适配器

        //联系tabLayout和ViewPagerChild
        tabLayout.setTabTextColors(Color.DKGRAY, Color.BLUE);// 选中为蓝色,未选中为灰色
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);// 设置下标线为蓝色
        tabLayout.setupWithViewPager(viewPager);

    }

    // 初始化集合
    public void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new AllFragment());
        fragmentList.add(new ToRaiseFragment());
        fragmentList.add(new RaiseCompleteFragment());
        fragmentList.add(new FinancingFragment());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon_search:
                Intent intentSearch = new Intent(getContext(), SearchActivity.class);
                startActivity(intentSearch);//跳转到搜索页面
        }
    }
}
