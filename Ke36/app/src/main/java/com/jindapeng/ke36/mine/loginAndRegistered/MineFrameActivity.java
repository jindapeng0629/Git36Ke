package com.jindapeng.ke36.mine.loginAndRegistered;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/16.
 * 登录和注册
 */
public class MineFrameActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MineFrameAdapter mineFrameAdapter;
    private List<Fragment> fragments;
    private ImageView imageClose;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_frame;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        viewPager = bindView(R.id.mine_viewpager);
        tabLayout = bindView(R.id.mine_tab);
        imageClose = bindView(R.id.mine_icon_close);
        imageClose.setOnClickListener(this);// 监听关闭图标

    }

    @Override
    protected void initData() {
        //把fragment数据初始化
        initFragments();
        //把数据放到adapter里
        mineFrameAdapter = new MineFrameAdapter(getSupportFragmentManager());
        mineFrameAdapter.setFragments(fragments);
        viewPager.setAdapter(mineFrameAdapter);
        //将tabLayout和ViewPager联系起来
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(0xff4f8cee);

        View view0 = LayoutInflater.from(this).inflate(R.layout.mine_login_view, null);// 绑定了登录布局
        View view1 = LayoutInflater.from(this).inflate(R.layout.mine_registered_view, null);// 绑定了注册布局
        tabLayout.getTabAt(1).setCustomView(view1);
        tabLayout.getTabAt(0).setCustomView(view0);
        viewPager.setCurrentItem(1);
        viewPager.setCurrentItem(0);

    }

    //初始化各个Fragment
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisteredFragment());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_icon_close:
                finish();
                break;
        }
    }
}
