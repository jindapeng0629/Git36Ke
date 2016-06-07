package com.jindapeng.ke36.author;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;
import com.jindapeng.ke36.base.VolleySingle;

/**
 * Created by dllo on 16/5/23.
 * 作者页面
 */
public class AuthorActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backImg;
    private AuthorBean authorBean;
    private TextView totalCount, totalView;

    @Override
    protected int getLayout() {
        return R.layout.activity_author;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        backImg = bindView(R.id.author_icon_back);
        backImg.setOnClickListener(this);

        totalView = bindView(R.id.author_total_view);
        totalCount = bindView(R.id.author_total_count);

    }

    @Override
    protected void initData() {
        Bundle bundle1 = this.getIntent().getExtras();
        String postId = bundle1.getString("postId");
        Log.d("postId", "+_+_+_+_" + postId);
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news/" + postId + "/author-region",
                AuthorBean.class, new Response.Listener<AuthorBean>() {
                    @Override
                    public void onResponse(AuthorBean response) {
                        authorBean = response;
                        Log.d("response", "+_+_+_+_" + response);
                        totalCount.setText(response.getData().getTotalCount() + "篇");
                        totalView.setText(response.getData().getTotalView() + "次");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.author_icon_back:
                finish();
                break;

        }
    }
}
