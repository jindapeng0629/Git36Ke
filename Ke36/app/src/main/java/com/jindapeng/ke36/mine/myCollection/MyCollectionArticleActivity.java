package com.jindapeng.ke36.mine.myCollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseActivity;
import com.jindapeng.ke36.details.DetailsActivity;
import com.jindapeng.ke36.greenDao.Collection;
import com.jindapeng.ke36.greenDao.CollectionDao;
import com.jindapeng.ke36.greenDao.GreenDaoSingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/27.
 * 我收藏的文章
 */
public class MyCollectionArticleActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView backImg;
    private ListView listView;
    private List<Collection> collectionList;
    private CollectionDao collectionDao;
    private MyCollectionAdapter myCollectionAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_collection_article;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        backImg = bindView(R.id.collection_backImg);
        backImg.setOnClickListener(this);
        listView = bindView(R.id.collection_list_view);
        listView.setOnItemClickListener(this);

        }

        @Override
        protected void initData() {
            myCollectionAdapter = new MyCollectionAdapter(this);
            collectionList = new ArrayList<>();
            collectionDao = GreenDaoSingle.getOurInstance().getCollectionDao();
            collectionList = collectionDao.queryBuilder().list();
            myCollectionAdapter.setCollectionList(collectionList);
            listView.setAdapter(myCollectionAdapter);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.collection_backImg:
                    finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //用bundle向详情页面传值
        Bundle bundle = new Bundle();
        bundle.putLong("feedId", collectionList.get(position).getId());
        bundle.putBoolean("isCollection", true);
        Intent intent = new Intent(MyCollectionArticleActivity.this, DetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
