package com.jindapeng.ke36.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/5/4.
 * // 获得屏幕的宽
 */
public class WidthScreen {
    //获得屏幕的宽
    public static int getScreenWidth(Context context){
        WindowManager manager =
                (WindowManager) context
                        .getSystemService(Context
                                .WINDOW_SERVICE);

        //创建一个DisplayMetrics
        //屏幕的一些参数通过该类获得
        DisplayMetrics outMetrics = new DisplayMetrics();
        //调用WindowManager的getDefaultDisplay()
        //.getMetrics(outMetrics);方法
        //对这个outMetrics里的各个参数进行赋值
        manager.getDefaultDisplay().getMetrics(outMetrics);
        //输出屏幕的宽
        return outMetrics.widthPixels;

    }
}


