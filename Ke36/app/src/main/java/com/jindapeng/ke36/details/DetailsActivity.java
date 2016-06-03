package com.jindapeng.ke36.details;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.author.AuthorActivity;
import com.jindapeng.ke36.author.AuthorBean;
import com.jindapeng.ke36.base.BaseActivity;
import com.jindapeng.ke36.base.MyApplication;
import com.jindapeng.ke36.base.VolleySingle;
import com.jindapeng.ke36.greenDao.Collection;
import com.jindapeng.ke36.greenDao.CollectionDao;
import com.jindapeng.ke36.greenDao.GreenDaoSingle;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/19.
 * 新闻的详情页面
 */
public class DetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imgBtn, userImg, collectionImg, popImg, shareImg;
    private TextView userTv, titleTv, timeTv, summaryTv, contentTv, totalCount, totalView;
    private DetailsBean detailsBean;
    private String postId;
    private long id;

    private PopupWindow popupWindow;
    private AuthorBean authorBean;
    private AuthorPopAdapter authorPopAdapter;
    private ListView listView;
    private View views;

    private CollectionDao collectionDao;
    private long feedId;
    private String title;
    private String name;
    private long publishTime;
    private String columnName;
    private String featureImg;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        titleTv = bindView(R.id.details_title);// 文章标题
        timeTv = bindView(R.id.details_publishTime);// 更新时间
        summaryTv = bindView(R.id.details_summary);// 详情总结
        contentTv = bindView(R.id.details_content);// 文章的主体

        views = bindView(R.id.details_view);
        imgBtn = bindView(R.id.news_toolbar_icon_back);
        imgBtn.setOnClickListener(this);
        userImg = bindView(R.id.details_user_avatar);
        userImg.setOnClickListener(this);
        userTv = bindView(R.id.details_user_name);
        userTv.setOnClickListener(this);
        popImg = bindView(R.id.details_up_down);
        popImg.setOnClickListener(this);
        collectionImg = bindView(R.id.details_collection);//收藏
        collectionImg.setOnClickListener(this);
        shareImg = bindView(R.id.details_toolbar_share);//分享
        shareImg.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        feedId = getIntent().getLongExtra("feedId", feedId);
        SharedPreferences sharedPreferences = getSharedPreferences("collection", MODE_PRIVATE);
        //得到传递过来的intent参数
        Bundle bundle1 = this.getIntent().getExtras();//新闻和收藏的页面都是在这里接受传过来的id值
        postId = String.valueOf(bundle1.getLong("feedId"));

        if (sharedPreferences.getBoolean("isCollection" + feedId, false)) {
            collectionImg.setImageResource(R.mipmap.news_toolbar_icon_favorite_blue);//传过来的boolean值是false才执行
        } else {
            collectionImg.setImageResource(R.mipmap.news_toolbar_icon_favorite);
        }
        struct();
        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news/" + String.valueOf(postId),
                DetailsBean.class, new Response.Listener<DetailsBean>() {
                    @Override
                    public void onResponse(DetailsBean response) {
                        detailsBean = response;
                        Log.d("DetailsActivity", "response.getData():" + response.getData());
                        userTv.setText(response.getData().getUser().getName());
                        titleTv.setText(response.getData().getTitle());
                        summaryTv.setText(response.getData().getSummary());

                        //设置转换html类型格式
                        String datas = response.getData().getContent();
                        contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());
                        contentTv.setMovementMethod(LinkMovementMethod.getInstance());
                        contentTv.setText(Html.fromHtml(datas, imgGetter, null));

                        // 转换时间格式
                        long time = response.getData().getPublishTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String times = simpleDateFormat.format(new Date(Long.valueOf(time)));
                        timeTv.setText(times);

                        // 获取图片数据
                        Picasso.with(MyApplication.context).load(response.getData().getUser().getAvatar()).into(userImg);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());// 设置组件宽高

            return drawable;
        }
    };

    public static void struct() {
        // 设置严苛模式（StrictMode）的线程策略
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        // 设置严苛模式（StrictMode）的虚拟机策略
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.news_toolbar_icon_back:
                finish();
                break;

            //跳转到作者页面
            case R.id.details_user_avatar:
                Bundle bundle = new Bundle();
                bundle.putString("postId", postId);// 将postId存入bundle中
                Intent intent = new Intent(this, AuthorActivity.class);
                intent.putExtras(bundle);//将bundle放入intent中
                startActivity(intent);// 开始跳转传值
                break;

            case R.id.details_collection:// 点击图片收藏文章
                Bundle bundle1 = this.getIntent().getExtras();
                feedId = bundle1.getLong("feedId");
                title = bundle1.getString("title");
                publishTime = bundle1.getLong("publishTime");
                columnName = bundle1.getString("columnName");
                name = bundle1.getString("name");
                featureImg = bundle1.getString("featureImg");

                SharedPreferences sharedPreferences = getSharedPreferences("collection", MODE_PRIVATE);
                //传过来的值是true执行下面的代码，说明已经收藏过了，再点击就是取消收藏
                if (sharedPreferences.getBoolean("isCollection" + feedId, false)) {
                    collectionDao = GreenDaoSingle.getOurInstance().getCollectionDao();// 获取单例
                    collectionImg.setImageResource(R.mipmap.news_toolbar_icon_favorite);// 设置图片
                    Toast.makeText(DetailsActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    Log.d("CollectionArticle", "删除的feedId:+++" + feedId);
                    if (collectionDao != null) {
                        Log.d("CollectionArticle", "删除了");
                        collectionDao.deleteByKey(feedId);// 如果已有数据,就删除它
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isCollection" + feedId, false);//存假
                    editor.commit();

                } else {
                    //传过来的值f是alse执行下面的代码，说明还没有收藏过，点击就是收藏
                    collectionImg.setImageResource(R.mipmap.news_toolbar_icon_favorite_blue);
                    collectionDao = GreenDaoSingle.getOurInstance().getCollectionDao();
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                    Log.d("CollectionArticle", "保存的ids:" + feedId);
                    Collection collection = new Collection(feedId, title, publishTime, columnName, name, featureImg);
                    if (collectionDao != null) {
                        //TODO 判断数据库是否存在 如果存在就不再加入
                        collectionDao.insert(collection);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isCollection" + feedId, true);//存真
                    editor.commit();
                }
                break;

            case R.id.details_up_down:
                DisplayMetrics dm = new DisplayMetrics();
                WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
                manager.getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels;    //得到宽度
                int height = dm.heightPixels;  //得到高度

                // 获取自定义布局文件popupwindow_details.xml的视图
                View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_details, null);
                totalCount = (TextView) view.findViewById(R.id.total_count);
                totalView = (TextView) view.findViewById(R.id.total_view);
                listView = (ListView) view.findViewById(R.id.pop_list_view);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow(view, width, height / 2, true);
                // 设置动画效果
                popupWindow.setAnimationStyle(R.style.AnimationFade);
                // 为Popupwindow窗口设置背景
                ColorDrawable cd = new ColorDrawable(0x70000000);
                popupWindow.setBackgroundDrawable(cd);
                // 点击PopupWindow之外区域后消失
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                // 这里是位置显示方式,从屏幕的左侧进入，在横线底下
                popupWindow.showAsDropDown(views);
                isShowPopupWindow();
                break;

            case R.id.details_toolbar_share:
                showShare();
        }
    }

    //解析popupwindow数据
    private void isShowPopupWindow() {

        VolleySingle.addRequest("http://rong.36kr.com/api/mobi/news/" + String.valueOf(postId) + "/author-region",
                AuthorBean.class, new Response.Listener<AuthorBean>() {
                    @Override
                    public void onResponse(AuthorBean response) {
                        authorBean = response;
                        Log.d("篇", "篇" + authorBean);
                        authorPopAdapter = new AuthorPopAdapter(getBaseContext());
                        totalCount.setText(authorBean.getData().getTotalCount() + "篇");
                        totalView.setText(authorBean.getData().getTotalView() + "次");
                        authorPopAdapter.setAuthorBean(authorBean);
                        listView.setAdapter(authorPopAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        // oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");// 启动分享GUI
        oks.show(this);
    }
}