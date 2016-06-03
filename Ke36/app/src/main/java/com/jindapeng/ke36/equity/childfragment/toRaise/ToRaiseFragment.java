package com.jindapeng.ke36.equity.childfragment.toRaise;

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
 * 募资中
 */
public class ToRaiseFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ToRaiseAdapter toRaiseAdapter;
    private ToRaiseBean toRaiseBean;
    @Override
    public int setLayout() {
        return R.layout.fragment_equity_to_raise;
    }

    @Override
    public void initView(View view) {
        listView = bindView(R.id.equity_list_view);
        listView.setOnItemClickListener(this);//监听listview行布局
    }

    @Override
    public void initData() {
        toRaiseAdapter = new ToRaiseAdapter(getContext());
        listView.setAdapter(toRaiseAdapter);
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=underway&pageSize=20",
                ToRaiseBean.class, new Response.Listener<ToRaiseBean>() {
                    @Override
                    public void onResponse(ToRaiseBean response) {
                        toRaiseBean = response;
                        Log.d("AllFragment", "response.getData():" + response.getData());
                        toRaiseAdapter.setToRaiseBean(response);// 传数据到适配器
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
