package com.jindapeng.ke36.discovery.look_investor;

import android.util.Log;
import android.view.View;
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

/**
 * Created by dllo on 16/5/13.
 * 寻找投资人
 */
public class LookInvestorsActivity extends BaseActivity implements View.OnClickListener {
    private LookInvestorsAdapter lookInvestorsAdapter;
    private PullToRefreshListView pullToRefreshListView;
    private ImageView imageView;

    @Override
    protected int getLayout() {
        return R.layout.activity_lookinvestor;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        imageView = bindView(R.id.looking_investors_back);
        imageView.setOnClickListener(this);

        pullToRefreshListView = bindView(R.id.looking_investor_list_view);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    protected void initData() {
        lookInvestorsAdapter = new LookInvestorsAdapter(this);

        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/investor?page=1&pageSize=20",
                LookInvestorsBean.class,
                new Response.Listener<LookInvestorsBean>() {
                    @Override
                    public void onResponse(LookInvestorsBean response) {
                        Log.d("LookInvestorsActivity", "response.getData():" + response.getData());
                        lookInvestorsAdapter.setLookInvestorsBean(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("---------", "onErrorResponse: " + error);
                    }
                });

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(LookInvestorsActivity.this, "刷新", Toast.LENGTH_SHORT).show();
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/investor?page=1&pageSize=20",
                        LookInvestorsBean.class,
                        new Response.Listener<LookInvestorsBean>() {
                            @Override
                            public void onResponse(LookInvestorsBean response) {
                                Log.d("LookInvestorsActivity", "response.getData():" + response.getData());
                                lookInvestorsAdapter.setLookInvestorsBean(response);
                                pullToRefreshListView.onRefreshComplete();//取消动画
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("---------", "onErrorResponse: " + error);
                            }
                        });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(LookInvestorsActivity.this, "加载", Toast.LENGTH_SHORT).show();
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/investor?page=1&pageSize=60",
                        LookInvestorsBean.class,
                        new Response.Listener<LookInvestorsBean>() {
                            @Override
                            public void onResponse(LookInvestorsBean response) {
                                Log.d("LookInvestorsActivity", "response.getData():" + response.getData());
                                lookInvestorsAdapter.setLookInvestorsBean(response);
                                pullToRefreshListView.onRefreshComplete();//取消动画
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("---------", "onErrorResponse: " + error);
                            }
                        });

            }
        });

        pullToRefreshListView.setAdapter(lookInvestorsAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.looking_investors_back:
                finish();
                break;
        }
    }
}
