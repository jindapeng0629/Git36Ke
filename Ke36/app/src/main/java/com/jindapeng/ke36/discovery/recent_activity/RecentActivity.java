package com.jindapeng.ke36.discovery.recent_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;
import com.jindapeng.ke36.base.VolleySingle;
import com.jindapeng.ke36.discovery.recent_activity.recentDetails.RecentDetailsActivity;

/**
 * Created by dllo on 16/5/13.
 * 近期项目
 */
public class RecentActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RecentAdapter recentAdapter;
    private ImageView imageView;
    private RecentBean recentBean;
    private PullToRefreshListView pullToRefreshListView;

    @Override
    protected int getLayout() {
        return R.layout.activity_recent;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        imageView = bindView(R.id.recent_back);//返回键
        imageView.setOnClickListener(this);//监听返回键

        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.recent_list_view);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        recentAdapter = new RecentAdapter(this);

        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/activity/list?page=1",
                RecentBean.class,
                new Response.Listener<RecentBean>() {
                    @Override
                    public void onResponse(RecentBean response) {
                        recentBean = response;
                        Log.d("RecentActivity", "response.getData():" + response.getData());
                        recentAdapter.setRecentBean(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("---------", "onErrorResponse: " + error);
                    }
                });

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(RecentActivity.this, "刷新", Toast.LENGTH_SHORT).show();
                VolleySingle.addRequest("http://rong.36kr.com/api/mobi/activity/list?page=1",
                        RecentBean.class,
                        new Response.Listener<RecentBean>() {
                            @Override
                            public void onResponse(RecentBean response) {
                                recentBean = response;
                                Log.d("RecentActivity", "response.getData():" + response.getData());
                                recentAdapter.setRecentBean(response);
                                pullToRefreshListView.onRefreshComplete();//取消动画
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("---------", "onErrorResponse: " + error);
                            }
                        });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(RecentActivity.this, "加载", Toast.LENGTH_SHORT).show();
                VolleySingle.addRequest("http://rong.36kr.com/api/mobi/activity/list?page=1&pageSize=40",
                        RecentBean.class,
                        new Response.Listener<RecentBean>() {
                            @Override
                            public void onResponse(RecentBean response) {
                                recentBean = response;
                                Log.d("RecentActivity", "response.getData():" + response.getData());
                                recentAdapter.setRecentBean(response);
                                pullToRefreshListView.onRefreshComplete();//取消动画

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("---------", "onErrorResponse: " + error);
                            }
                        });
            }
        });
        pullToRefreshListView.setAdapter(recentAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recent_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        bundle.putString("link",recentBean.getData().getData1().get(position).getActivityLink());

        Log.d("activitylink",recentBean.getData().getData1().get(position).getActivityLink());

        Intent intent = new Intent(this,RecentDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
