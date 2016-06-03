package com.jindapeng.ke36.equity.childfragment.financ;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.base.VolleySingle;

/**
 * Created by dllo on 16/5/10.
 * 融资成功
 */
public class FinancingFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private ListView listView;
    private FinancAdapter financAdapter;
    private FinancBean financBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_equity_financing;
    }

    @Override
    public void initView(View view) {
        listView = bindView(R.id.equity_list_view);
        listView.setOnItemClickListener(this);//监听listview行布局
    }

    @Override
    public void initData() {
        financAdapter = new FinancAdapter(getContext()); // 初始化适配器
        listView.setAdapter(financAdapter);// 绑定适配器
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=finish&pageSize=20",
                FinancBean.class, new Response.Listener<FinancBean>() {
                    @Override
                    public void onResponse(FinancBean response) {
                        financBean = response;
                        Log.d("FinancingFragment", "response.getData():" + response.getData());
                        financAdapter.setFinancBean(response);// 传数据到适配器
                    }
                }, new Response.ErrorListener() {
                    // 异常监听,如果失败就会显示异常
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("-+-+-+-+-+-+-+", "onErrorResponse: " + error);
                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
