package com.jindapeng.ke36.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.author.AuthorActivity;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.details.DetailsActivity;
import com.jindapeng.ke36.mine.myCollection.MyCollectionArticleActivity;
import com.jindapeng.ke36.utils.RoundImage;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/5/9.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout rlLogin, collection;
    private RoundImage mineImg;
    private TextView name;
    private Platform qq;

    @Override
    public int setLayout() {
        ShareSDK.initSDK(getContext());
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        rlLogin = bindView(R.id.mine_rl_login);
        rlLogin.setOnClickListener(this);
        collection = bindView(R.id.mine_collection_article);
        collection.setOnClickListener(this);
        mineImg = bindView(R.id.mine_common_avatar);
        name = bindView(R.id.mine_name);
    }

    @Override
    public void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("onLoad", Context.MODE_PRIVATE);

        if (sp.getBoolean("online", false)) {
            qq = ShareSDK.getPlatform(getContext(), QQ.NAME);
            Picasso.with(getContext()).load(qq.getDb().getUserIcon()).into(mineImg);
            name.setText(qq.getDb().getUserName());

        } else {
            qq = ShareSDK.getPlatform(getContext(), QQ.NAME);
        }


    }

    @Override
    public void onClick(View v) {
switch (v.getId()) {
        case R.id.mine_rl_login:
        if (qq.getDb().getUserIcon() == null && qq.getDb().getUserName() == null) {
        Intent intentLogin = new Intent(getContext(), MineFrameActivity.class);
        startActivity(intentLogin);
        } else {
        Intent intent = new Intent(getContext(), AuthorActivity.class);
        startActivity(intent);
        }

        break;
        case R.id.mine_collection_article:
        Intent collectionIntent = new Intent(getContext(), MyCollectionArticleActivity.class);
        startActivity(collectionIntent);
        break;
        }
        }
        }
