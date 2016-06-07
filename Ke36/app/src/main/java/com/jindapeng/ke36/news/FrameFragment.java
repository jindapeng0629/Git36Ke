package com.jindapeng.ke36.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jindapeng.ke36.R;
import com.jindapeng.ke36.base.BaseFragment;
import com.jindapeng.ke36.earlyProject.EarlyProjectFragment;
import com.jindapeng.ke36.ketv.KeTvFragment;
import com.jindapeng.ke36.search.SearchActivity;

/**
 * Created by dllo on 16/5/9.
 * 新闻页面
 */
public class FrameFragment extends BaseFragment implements View.OnClickListener {
    private ImageView menuImage, searchImage;
    private PopupWindow popupWindow;
    private RadioButton allBtn, keTvBtn, earlyBtn;
    private TextView textView;

    @Override
    public int setLayout() {
        return R.layout.fragment_news_frame;
    }

    @Override
    public void initView(View view) {
        menuImage = bindView(R.id.iv_icon_navigation);
        menuImage.setOnClickListener(this);// 监听图标点击
        searchImage = bindView(R.id.iv_icon_search);
        searchImage.setOnClickListener(this);
        textView = bindView(R.id.tv_title);
        // 让程序运行后首先显示全部的新闻页面,不显示空的布局
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.replace_view, new NewsFragment()).commit();
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon_navigation:
                DisplayMetrics dm = new DisplayMetrics();//构造函数DisplayMetrics ，用来获取屏幕信息。
                WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                // 调用getWindowManager() 之后，会取得现有Activity 的Handle ，
                // getDefaultDisplay() 方法将取得的宽高维度存放于DisplayMetrics 对象中，
                // 而取得的宽高维度是以像素为单位(Pixel) ，“像素”所指的是“绝对像素”而非“相对像素”。
                manager.getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels;    //得到宽度
                int height = dm.heightPixels;  //得到高度 

                // 获取自定义布局文件news_popupwindow.xml的视图
                View view = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_news, null);
                // 创建PopupWindow实例,分别是宽度和高度
                popupWindow = new PopupWindow(view, width / 2, height, true);
                // 设置动画效果
                popupWindow.setAnimationStyle(R.style.AnimationFade);
                // 点击PopupWindow之外区域后消失
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                // 这里是位置显示方式,在屏幕的左侧
                popupWindow.showAtLocation(v, Gravity.LEFT, 0, 0);

                ImageView imageView = (ImageView) view.findViewById(R.id.common_iv_back);//加载箭头图片
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();//消失
                        }
                    }
                });

                allBtn = (RadioButton) view.findViewById(R.id.all_rb);
                allBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().getSupportFragmentManager().beginTransaction().
                                replace(R.id.replace_view, new NewsFragment()).commit();
                        textView.setText("新闻");// 替换标题
                        popupWindow.dismiss();//消失
                    }
                });

                earlyBtn = (RadioButton) view.findViewById(R.id.early_rb);
                earlyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().getSupportFragmentManager().beginTransaction().
                                replace(R.id.replace_view, new EarlyProjectFragment()).commit();
                        textView.setText("早期项目");// 替换标题
                        popupWindow.dismiss();//消失
                    }
                });

                keTvBtn = (RadioButton) view.findViewById(R.id.ke_tv_rb);
                keTvBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().getSupportFragmentManager().beginTransaction().
                                replace(R.id.replace_view, new KeTvFragment()).commit();
                        textView.setText("氪TV");// 替换标题
                        popupWindow.dismiss();//消失
                    }
                });

                break;
            case R.id.iv_icon_search:
                Intent intentSearch = new Intent(getContext(), SearchActivity.class);
                startActivity(intentSearch);//跳转到搜索页面
        }
    }
}
