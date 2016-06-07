package com.jindapeng.ke36.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.main.MainActivity;
import com.jindapeng.ke36.mine.loginAndRegistered.MineFrameActivity;
import com.jindapeng.ke36.mine.myCollection.MyCollectionArticleActivity;
import com.jindapeng.ke36.mine.setting.SettingActivity;
import com.jindapeng.ke36.utils.RoundImageView;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/5/9.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout rlLogin, collection;
    private RoundImageView mineImg;
    private TextView name;
    private Platform qq;
    private SharedPreferences sp;
    private ImageView setImg;

    @Override
    public int setLayout() {
        ShareSDK.initSDK(getContext());
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {

        rlLogin = bindView(R.id.mine_rl_login);
        rlLogin.setOnClickListener(this);
        collection = bindView(R.id.mine_collection_article);
        collection.setOnClickListener(this);
        mineImg = bindView(R.id.mine_common_avatar);
        name = bindView(R.id.mine_name);
        setImg = bindView(R.id.mine_icon_setting);
        setImg.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //创建一线程，让它每隔一秒发一条信息刷新界面，让登陆后传递过来的名字和头像，显示出来。
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {

                    sp = getContext().getSharedPreferences("onLoad", Context.MODE_PRIVATE);
                    if (sp.getBoolean("online", false)) {
                        //当传来的是true，就表示已经登陆成功，显示头像名字
                        qq = ShareSDK.getPlatform(getContext(), QQ.NAME);
                        Picasso.with(getContext()).load(qq.getDb().getUserIcon()).into(mineImg);
                        name.setText(qq.getDb().getUserName());

                    } else {
                        //传来false，表示没有登录，留在此页面。
                        qq = ShareSDK.getPlatform(getContext(), QQ.NAME);

                    }

                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //在这里循环发送消息，刷新页面
                    while (true) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        Log.d("message", "" + message);
                        Thread.currentThread().sleep(100);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_rl_login:
                if (sp.getBoolean("online", false)) {
                    //判断若是true就往下执行代码，若是false就执行else。
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intentLogin = new Intent(getContext(), MineFrameActivity.class);
                    startActivity(intentLogin);
                }
                break;

            case R.id.mine_collection_article:
                Intent collectionIntent = new Intent(getContext(), MyCollectionArticleActivity.class);
                startActivity(collectionIntent);
                break;

            case R.id.mine_icon_setting:
                Intent setIntent = new Intent(getContext(), SettingActivity.class);
                startActivity(setIntent);
                break;
        }
    }
}
