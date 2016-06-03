package com.jindapeng.ke36.discovery.recent_activity.recentDetails;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;

/**
 * Created by dllo on 16/5/24.
 * 近期活动的详情页面
 */
public class RecentDetailsActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private ImageView backImg;
    private TextView textView;
    private String link;

    @Override
    protected int getLayout() {
        return R.layout.activity_recent_project_details;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        webView = bindView(R.id.recent_details_webview);
        backImg = bindView(R.id.details_back_img);
        textView = bindView(R.id.close);
        backImg.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        link = bundle.getString("link");
        Log.d("link", "" + link);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.requestFocus();
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });
    }

    final class InJavaScriptLocalObj {
        public void showSource(String html) {
            System.out.println("====>html=" + html);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_back_img:
                finish();
                break;
            case R.id.close:
                finish();
                break;
        }
    }
}
