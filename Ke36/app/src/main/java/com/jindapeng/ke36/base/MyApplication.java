package com.jindapeng.ke36.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/4/14.
 * 我们自己的Application
 * 在使用的时候,需要在清单文件中注册
 */
public class MyApplication extends Application {
    public static Context context;
    //Application创建的原因是因为我们需要一个属于自己的大
    // "环境"(context)
    //保证自己的App拥有单独的一个context对象

    //第一个生命周期中我们队context赋值

    @Override
    public void onCreate() {
        super.onCreate();
        //this代表当前的环境
        context = this;
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);        //初始化JPush
        Log.d("方法进入", "onCreate: 赋值");
    }

    //对外提供一个方法 这个方法就是让别的类获取自己的context对象
    public static Context getContext() {
        return context;
    }
}


