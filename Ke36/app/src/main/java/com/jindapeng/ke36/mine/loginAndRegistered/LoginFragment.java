package com.jindapeng.ke36.mine.loginAndRegistered;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.base.MyApplication;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/5/15.
 * 登录
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private ImageView qqImg;
    private SharedPreferences sp;

    @Override
    public int setLayout() {
        ShareSDK.initSDK(getContext());
        return R.layout.fragment_mine_login;
    }

    @Override
    public void initView(View view) {
        qqImg = bindView(R.id.mine_login_qq);
        qqImg.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_login_qq:
                //取得登录授权
                Platform qq = ShareSDK.getPlatform(getContext(), QQ.NAME);
                qq.SSOSetting(true);//自动
                qq.authorize();//手动

                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        //登陆成功后的请求回调
                        sp = getContext().getSharedPreferences("onLoad", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("online", true);
                        editor.apply();
                        getActivity().finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });

                break;
        }
    }
}
