package com.jindapeng.ke36.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.VolleySingle;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {
    private SearchView searchView;
    private ListView listView;
    private SearchBean searchBean;
    private SearchAdapter searchAdapter;
    private TextView textView;
    // 自动完成的列表
    private final String[] string = {};
    //定义一个String数组用来显示ListView的内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.sv_list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, string));
        // 用来绑定一个数组，支持\泛型操作

        listView.setTextFilterEnabled(true);
        // 这个方法的作用是用来过滤选项的.
        // 例如在软键盘上打出一个a,则会过滤掉除了a开头的所有选项.
        searchAdapter = new SearchAdapter(this);

        searchView = (SearchView) findViewById(R.id.search);

        searchView.isIconfiedByDefault();
        // 设置该SearchView默认是否自动缩小为图标

        searchView.setOnQueryTextListener(this);
        // 为该SearchView组件设置事件监听器

        searchView.setSubmitButtonEnabled(true);
        // 设置该SearchView显示搜索按钮

        searchView.setQueryHint("查找");
        // 设置该SearchView内默认显示的提示文本

        textView = (TextView) findViewById(R.id.close_search);
        textView.setOnClickListener(this);
    }

    // 用户点击搜索按钮时激发该方法
    @Override
    public boolean onQueryTextSubmit(final String query) {
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news/search?keyword=" + query + "&page=1&pagesize=30", SearchBean.class, new Response.Listener<SearchBean>() {
            @Override
            public void onResponse(SearchBean response) {
                searchBean = response;
                if (searchBean.getData().getTotalCount() < 1) {
                    Toast.makeText(SearchActivity.this, "搜索不到你要找的数据", Toast.LENGTH_SHORT).show();

                } else {
                    searchAdapter.setSearchBean(searchBean);
                    listView.setAdapter(searchAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // 实际应用中应该在该方法内执行实际查询
        // 此处仅使用Toast显示用户数的查询内容
        Toast.makeText(SearchActivity.this, "您查找的是" + query, Toast.LENGTH_SHORT).show();
        return true;
    }

    // 用户输入字符时激发该方法
    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            // 清除listview的过滤
            listView.clearTextFilter();
        } else {
            // 使用用户输入的内容对listview的列表项进行过滤
            listView.setFilterText(newText);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_search:
                finish();
                break;
        }
    }
}

