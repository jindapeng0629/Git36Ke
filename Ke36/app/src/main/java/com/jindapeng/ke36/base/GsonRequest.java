package com.jindapeng.ke36.base;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/5/11.
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener; //用于回调的接口对象
    private Gson mGson; //一会儿用来解析的Gson
    private Class<T> mClass;//这就是带泛型的实体类

    //构造方法
    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> mListener, Class<T> mClass) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mGson = new Gson();
        this.mClass = mClass;
    }

    public GsonRequest(String url, Response.ErrorListener listener, Response.Listener mListener, Class mClass) {
        this(Method.GET, url, listener, mListener, mClass);

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //首先要获取想要解析的数据
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //这里们返回的就是成功时候的回调.直接将数据解析,注意一下第二个参数是缓存入口,这里直接设置的就是HttpHeaderParser.parseCacheHeaders(response)
            return Response.success(mGson.fromJson(data, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            //若出现异常,返回失败时候的回调
            return Response.error(new ParseError(e));
        }
    }

    //这个方法是对请求的response进行的反馈.
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
