package com.jindapeng.ke36.mine;

import android.view.View;
import android.widget.ImageView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/5/15.
 * 登录
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private ImageView qqImg;

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
        switch (v.getId()){
            case R.id.mine_login_qq:
                Platform qq=ShareSDK.getPlatform(getContext(), QQ.NAME);
                qq.SSOSetting(true);
                qq.authorize();
                String accessToken = qq.getDb().getToken(); // 获取授权token
                String openId = qq.getDb().getUserId(); // 获取用户在此平台的ID
                String nickname = qq.getDb().getUserName(); // 获取用户昵称
                String Icon=qq.getDb().getUserIcon();

        }
    }
}
