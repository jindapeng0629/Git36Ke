package com.jindapeng.ke36.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jindapeng.ke36.discovery.DiscoveryFragment;
import com.jindapeng.ke36.equity.EquityFragment;
import com.jindapeng.ke36.jpush.ExampleUtil;
import com.jindapeng.ke36.mine.MyFragment;
import com.jindapeng.ke36.news.FrameFragment;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainAdapter mainAdapter;
    private List<Fragment> fragments;
    public static boolean isForeground = false;
    @Override
    protected int getLayout() {
        registerMessageReceiver();//注册服务
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        viewPager = bindView(R.id.main_viewpager);
        tabLayout = bindView(R.id.main_tab);
    }

    @Override
    protected void initData() {

        //把fragment数据初始化
        initFragments();
        //把数据放到adapter里
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragments(fragments);
        viewPager.setAdapter(mainAdapter);
        //初始化tab的图标
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.GRAY, 0xff658ef5);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        initTabs();
    }

    //初始化各个Fragment
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new FrameFragment());
        fragments.add(new EquityFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new MyFragment());
    }

    //初始化Tabs
    private void initTabs() {
        int[] selectors = {
                R.drawable.tab_icon_news,
                R.drawable.tab_icon_equity,
                R.drawable.tab_icon_discovery,
                R.drawable.tab_icon_mine};
        for (int i = 0; i < selectors.length; i++) {
            tabLayout.getTabAt(i).setIcon(selectors[i]);
        }
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }

            }
        }
    }
}
