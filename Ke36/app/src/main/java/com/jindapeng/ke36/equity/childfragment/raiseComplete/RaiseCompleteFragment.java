package com.jindapeng.ke36.equity.childfragment.raiseComplete;

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
 * 募资完成
 */
public class RaiseCompleteFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private RaiseCompleteAdapter raiseCompleteAdapter;
    private RaiseCompleteBean raiseCompleteBean;
    @Override
    public int setLayout() {
        return R.layout.fragment_equity_raise_complete;
    }

    @Override
    public void initView(View view) {
        listView = bindView(R.id.equity_list_view);
        listView.setOnItemClickListener(this);//监听listview行布局
    }

    @Override
    public void initData() {
        raiseCompleteAdapter = new RaiseCompleteAdapter(getContext());
        listView.setAdapter(raiseCompleteAdapter);
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=raise&pageSize=20",
                RaiseCompleteBean.class, new Response.Listener<RaiseCompleteBean>() {
                    @Override
                    public void onResponse(RaiseCompleteBean response) {
                        raiseCompleteBean = response;
                        Log.d("RaiseCompleteFragment", "response.getData():" + response.getData());
                        raiseCompleteAdapter.setRaiseCompleteBean(response);// 传数据到适配器
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
