package com.jindapeng.ke36.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/5/9.
 * 是项目里所有Fragment的基类
 */
public abstract class BaseFragment extends Fragment {
    private Context context;
    // context对象,方便toast等操作
    // 它实际上就是Fragment依附的Activity

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    // 初始化视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    // 加载组件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    // 加载数据
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract int setLayout();
    public abstract void initView(View view);
    public abstract void initData();

//    这个方法使组件实例化不需要转型
//    使用方式:
//    TextView textView = bindView(R.id.tv);
//    这样使用这个方法的时候是不需要强转的
    protected <T extends View> T bindView(int id) {
        return (T)getView().findViewById(id);
    }
}
