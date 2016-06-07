package com.jindapeng.ke36.ketv;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.VideoView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.base.VolleySingle;

/**
 * Created by dllo on 16/5/12.
 * 氪TV页面
 */
public class KeTvFragment extends BaseFragment {
    private ListView listView;
    private KeTvAdapter keTvAdapter;

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
    }
}
