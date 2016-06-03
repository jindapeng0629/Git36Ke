package com.jindapeng.ke36.ketv;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.base.GsonRequest;
import com.jindapeng.ke36.base.VolleySingle;
import com.jindapeng.ke36.discovery.recent_activity.RecentAdapter;
import com.jindapeng.ke36.discovery.recent_activity.RecentBean;

import java.util.List;

/**
 * Created by dllo on 16/5/12.
 * 氪TV页面
 */
public class KeTvFragment extends BaseFragment {
    private ListView listView;
    private KeTvAdapter keTvAdapter;
    private int currentIndex=-1;
    private int playPosition=-1;
    private boolean isPaused=false;
    private boolean isPlaying=false;
    private VideoView mVideoView;


    public int setLayout() {
        return R.layout.fragment_ke_tv;
    }

    @Override
    public void initView(View view) {
        listView = bindView(R.id.ke_tv_list_view);
    }// 加载布局

    @Override
    public void initData() {
        // 解析视频数据
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news?pageSize=20&columnId=tv&pagingAction=up",
                KeTvBean.class, new Response.Listener<KeTvBean>() {
                    @Override
                    public void onResponse(KeTvBean response) {
                        Log.d("KeTvFragment", "response.getData():" + response.getData());
                        keTvAdapter.setKeTvBean(response);// 传递数据到适配器
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("---------", "onErrorResponse: " + error);
                    }
                });

        keTvAdapter = new KeTvAdapter(getContext());// 初始化适配器
        listView.setAdapter(keTvAdapter);// 将ListView绑定到适配器
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if ((currentIndex < firstVisibleItem || currentIndex > listView.getLastVisiblePosition()) && isPlaying) {
//                    playPosition = mVideoView.getCurrentPosition();
//                    mVideoView.pause();
//                    mVideoView.setMediaController(null);
//                    isPaused = true;//进行点击，就会暂停
//                    isPlaying = false;
//                    System.out.println("视频已经暂停："+playPosition);
//                }
//           }
//        });
    }
}