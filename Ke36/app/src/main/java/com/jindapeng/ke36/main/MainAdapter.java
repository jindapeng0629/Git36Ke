package com.jindapeng.ke36.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] tabs = {"新闻", "股权投资", "发现", "我的"};


    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();// 通知适配器改变了
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }// 数组宽度

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];// 添加标题
    }
}
