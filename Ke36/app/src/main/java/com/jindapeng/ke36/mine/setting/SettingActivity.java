package com.jindapeng.ke36.mine.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import static com.jindapeng.ke36.utils.DataCleanManager.clearAllCache;
import static com.jindapeng.ke36.utils.DataCleanManager.getTotalCacheSize;

/**
 * Created by JINDAPENG on 2016/6/4.
 * 设置页面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Button outBtn;
    private TextView cacheTv, cache;
    private ImageView backImg;
    private SharedPreferences sp;

    @Override
    protected int getLayout() {
        ShareSDK.initSDK(this);
        getSupportActionBar().hide();
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        cacheTv = bindView(R.id.clear_the_cache);
        cacheTv.setOnClickListener(this);
        backImg = bindView(R.id.setting_back);
        backImg.setOnClickListener(this);
        outBtn = bindView(R.id.log_out);
        outBtn.setOnClickListener(this);
        cache = bindView(R.id.the_cache_size);
    }

    @Override
    protected void initData() {
        sp = getSharedPreferences("onLoad", MODE_PRIVATE);
        if (sp.getBoolean("online", false)) {
            // 如果是已经登录的状态，就显示
            outBtn.setVisibility(View.VISIBLE);
        } else {
            // 如果是未登录的状态，就隐藏
            outBtn.setVisibility(View.INVISIBLE);
        }
        try {
            String totalCacheSize = getTotalCacheSize(getBaseContext());//读取缓存大小
            cache.setText(totalCacheSize);// 显示缓存大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.clear_the_cache:
                clearAllCache(getBaseContext());
                // 清除缓存
                break;
            case R.id.log_out:
                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.isValid();
                qq.removeAccount();//取消登录授权
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("online", false);
                editor.apply();//提交
                //isValid和removeAccount不开启线程，会直接返回。
                finish();
                break;
        }
    }
}
