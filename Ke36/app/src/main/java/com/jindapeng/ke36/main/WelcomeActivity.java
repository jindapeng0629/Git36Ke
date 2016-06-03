package com.jindapeng.ke36.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;
import com.jindapeng.ke36.utils.DiskCache;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/5/10.
 */
public class WelcomeActivity extends BaseActivity {
    private ImageView imageView;
    private TextView textView;
    private CountDownTimer time;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        imageView = (ImageView) findViewById(R.id.flash_iv);
        textView = (TextView) findViewById(R.id.flash_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.cancel();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));//跳转到主页面
                finish();//关闭这个activity
            }
        });
    }

    @Override
    protected void initData() {
        time = new CountDownTimer(1000, 3000) {// 一秒后执行,执行时间是一秒

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                textView.setText("跳转");
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
