package com.jindapeng.ke36.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.base.VolleySingle;
import com.jindapeng.ke36.greenDao.Collection;
import com.jindapeng.ke36.greenDao.CollectionDao;
import com.jindapeng.ke36.greenDao.GreenDaoSingle;
import com.jindapeng.ke36.details.DetailsActivity;
import com.jindapeng.ke36.news.header.HeaderBean;
import com.jindapeng.ke36.news.header.NewsHeaderAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by dllo on 16/5/16.
 * 新闻页面
 */
public class NewsFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView pullToRefreshListView;
    private NewsAdapter newsAdapter;// 适配器
    private ArrayList<HeaderBean> images; // 轮播图图片
    private Runnable rotateRunnable;// 轮播线程
    private Handler handler; // 轮播handler
    private static final int TIME = 3000;// 轮播间隔时间
    private boolean isRotate = false; // 是否轮播,默认false
    private LinearLayout pointRoot; // 装载轮播时小点的容器
    private ViewPager viewPager;
    private NewsHeaderAdapter newsHeaderAdapter;// 头布局适配器
    private NewsBean newsBean; //实体类
    private ListView listView;

    @Override
    public int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {
        pullToRefreshListView = bindView(R.id.news_list_view);
        pullToRefreshListView.setOnItemClickListener(this);// 设置监听
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);// 设置既能上拉刷新,下拉加载
    }

    @Override
    public void initData() {
        newsAdapter = new NewsAdapter(getContext());//初始化适配器

        // 轮播图解析
        listView = pullToRefreshListView.getRefreshableView();
        // 绑定头布局
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.header_view, null);
        listView.addHeaderView(headerView);

        viewPager = (ViewPager) headerView.findViewById(R.id.my_viewpager);
        pointRoot = (LinearLayout) headerView.findViewById(R.id.point_container);// 将标示点绑到头布局
        newsHeaderAdapter = new NewsHeaderAdapter(getContext());// 初始化头布局
        VolleySingle.addRequest("http://chanyouji.com/api/adverts.json?name=app_featured_banner_android",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<HeaderBean>>() {
                        }.getType();
                        images = gson.fromJson(response, type);
                        Log.i("hahaha", images + "");
                        newsHeaderAdapter.setImageViews(images);
                        viewPager.setAdapter(newsHeaderAdapter);
                        getPoint();// 设置小点
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("hahaha", "失败" + error);
                    }
                });

        viewPager.setCurrentItem(60);// 设置当前页位置

        // 页面左右滑动的方法
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < images.size(); i++) {
                    ImageView iv = (ImageView) pointRoot.getChildAt(i);
                    iv.setImageResource(R.mipmap.point_white);// 白色图片放入
                }
                ImageView iv = (ImageView) pointRoot.getChildAt(position % images.size());
                iv.setImageResource(R.mipmap.point_grey);// 黑色图片放入
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 设置轮播
        startRotate();
        // 此处解析是为了已进入就显示数据信息
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up",
                NewsBean.class, new Response.Listener<NewsBean>() {
                    @Override
                    public void onResponse(NewsBean response) {
                        newsBean = response;
                        Log.d("NewsFragment", "response.getData():" + response.getData());
                        newsAdapter.setNewsBean(response);// 传数据到适配器
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
                VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up",
                        NewsBean.class, new Response.Listener<NewsBean>() {
                            @Override
                            public void onResponse(NewsBean response) {
                                newsBean = response;
                                Log.d("NewsFragment", "response.getData():" + response.getData());
                                newsAdapter.setNewsBean(response);// 传数据到适配器
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
                VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news?pageSize=60&columnId=all&pagingAction=up",
                        NewsBean.class, new Response.Listener<NewsBean>() {
                            @Override
                            public void onResponse(NewsBean response) {
                                newsBean = response;
                                Log.d("NewsFragment", "response.getData():" + response.getData());
                                newsAdapter.setNewsBean(response);// 传数据到适配器
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
        pullToRefreshListView.setAdapter(newsAdapter);// 绑定适配器


    }

    /**
     * 开始轮播
     */
    protected void startRotate() {
        handler = new Handler();
        // 初始化线程对象
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                // 获得ViewPager当前页
                int nowIndex = viewPager.getCurrentItem();
                // 设置ViewPager的页数是当前页自增1
                // 这里要判断,轮播的下一张page不能超过viewpager的count
                // 否则会崩2
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    // handler延时发送线程,实现轮播
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    /**
     * 添加轮播图小点
     */
    private void getPoint() {
        for (int i = 0; i < images.size(); i++) {
            ImageView pointIv = new ImageView(getContext());
            pointIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);// 设置小图标的宽高
            pointIv.setLayoutParams(lp);

            // 设置小点样式
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_grey);// 表示当前正在显示的页面
            } else {
                pointIv.setImageResource(R.mipmap.point_white);
            }
            pointRoot.addView(pointIv);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        Long feedId = Long.valueOf(newsBean.getData().getData1().get(position-2).getFeedId());
        bundle.putLong("feedId",feedId );
        bundle.putString("title", newsBean.getData().getData1().get(position-2).getTitle());
        bundle.putLong("publishTime", newsBean.getData().getData1().get(position-2).getPublishTime());
        bundle.putString("columnName", newsBean.getData().getData1().get(position-2).getColumnName());
        bundle.putString("name", newsBean.getData().getData1().get(position-2).getUser().getName());
        bundle.putString("featureImg", newsBean.getData().getData1().get(position-2).getFeatureImg());
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        CollectionDao collectionDao = GreenDaoSingle.getOurInstance().getCollectionDao();
        List<Collection> collectionList = collectionDao.queryBuilder().list();// 查询表数据
        for (Collection collection : collectionList) {
            if (collection.getId().equals(feedId)) {
                intent.putExtra("isCollection"+feedId, true);
            }else {
                intent.putExtra("isCollection"+feedId, false);
            }
        }
        intent.putExtras(bundle);// 将数据放入intent
        startActivity(intent);
    }

}

