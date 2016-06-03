package com.jindapeng.ke36.equity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/5/10.
 */
public class EquityAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] tabs = {"全部", "募资中", "募资完成", "融资成功"};

    public EquityAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();// 通知适配器改变了
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }// 数组宽度

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];// 添加标题
    }
}
