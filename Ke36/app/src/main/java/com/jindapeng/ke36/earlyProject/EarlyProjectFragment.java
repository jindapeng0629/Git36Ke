package com.jindapeng.ke36.earlyProject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.base.VolleySingle;
import com.jindapeng.ke36.details.DetailsActivity;
import com.jindapeng.ke36.greenDao.Collection;
import com.jindapeng.ke36.greenDao.CollectionDao;
import com.jindapeng.ke36.greenDao.GreenDaoSingle;

import java.util.List;

/**
 * Created by dllo on 16/5/14.
 * 早期项目
 */
public class EarlyProjectFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView pullToRefreshListView;
    private EarlyProjectAdapter earlyProjectAdapter;
    private EarlyProjectBean earlyProjectBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_early_project;
    }

    @Override
    public void initView(View view) {
        pullToRefreshListView = bindView(R.id.early_project_list_view);
        pullToRefreshListView.setOnItemClickListener(this);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);// 设置既能上拉刷新,下拉加载
    }

    @Override
    public void initData() {
        earlyProjectAdapter = new EarlyProjectAdapter(getContext());

        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=67&pagingAction=up",
                EarlyProjectBean.class, new Response.Listener<EarlyProjectBean>() {
                    @Override
                    public void onResponse(EarlyProjectBean response) {
                        earlyProjectBean = response;
                        Log.d("EarlyProjectFragment", "response.getData():" + response.getData());
                        earlyProjectAdapter.setEarlyProjectBean(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("---------", "onErrorResponse: " + error);
                    }
                });

        // 此处设置上拉刷新,下拉加载
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=67&pagingAction=up",
                        EarlyProjectBean.class, new Response.Listener<EarlyProjectBean>() {
                            @Override
                            public void onResponse(EarlyProjectBean response) {
                                earlyProjectBean = response;
                                Log.d("NewsFragment", "response.getData():" + response.getData());
                                earlyProjectAdapter.setEarlyProjectBean(response);// 传数据到适配器
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
                Toast.makeText(getContext(), "加载", Toast.LENGTH_SHORT).show();
                VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=60&columnId=67&pagingAction=up",
                        EarlyProjectBean.class, new Response.Listener<EarlyProjectBean>() {
                            @Override
                            public void onResponse(EarlyProjectBean response) {
                                earlyProjectBean = response;
                                Log.d("NewsFragment", "response.getData():" + response.getData());
                                earlyProjectAdapter.setEarlyProjectBean(response);// 传数据到适配器
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

        pullToRefreshListView.setAdapter(earlyProjectAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        Long feedId = Long.valueOf(earlyProjectBean.getData().getData1().get(position - 1).getFeedId());
        bundle.putLong("feedId", feedId);
        bundle.putString("title", earlyProjectBean.getData().getData1().get(position - 1).getTitle());
        bundle.putLong("publishTime", earlyProjectBean.getData().getData1().get(position - 1).getPublishTime());
        bundle.putString("columnName", earlyProjectBean.getData().getData1().get(position - 1).getColumnName());
        bundle.putString("name", earlyProjectBean.getData().getData1().get(position - 1).getUser().getName());
        bundle.putString("featureImg", earlyProjectBean.getData().getData1().get(position - 1).getFeatureImg());
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        CollectionDao collectionDao = GreenDaoSingle.getOurInstance().getCollectionDao();
        List<Collection> collectionList = collectionDao.queryBuilder().list();// 查询表数据
        for (Collection collection : collectionList) {
            if (collection.getId().equals(feedId)) {
                intent.putExtra("isCollection" + feedId, true);
            } else {
                intent.putExtra("isCollection" + feedId, false);
            }
        }
        intent.putExtras(bundle);// 将数据放入intent
        startActivity(intent);
    }
}
