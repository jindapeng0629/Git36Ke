package com.jindapeng.ke36.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jindapeng.ke36.utils.MD5Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dllo on 16/4/12.
 * 硬盘缓存类
 */
public class DiskCache {
    String cacheDir;// 文件缓存的路径

    public DiskCache(Context context) {
        // 初始化文件的目录
        cacheDir = context.getCacheDir() + "/Image/";

    }

    public Bitmap getBitmap(String url) {
        //将图片的url转换成MD5格式
        url = MD5Util.getMD5String(url);
        return BitmapFactory.decodeFile(cacheDir + url + ".png");
    }

    public void putBitmap(String url, Bitmap bitmap) throws IOException {
        // 存图片方法
        // 先获取Image文件夹
        File file = new File(cacheDir);
        if (!file.exists()) {
            // 如果该文件夹不存在,就新建一个文件夹
            file.mkdir();
        }

        // 处理一下文件名:从网站转成文件md5
        url = MD5Util.getMD5String(url);
        File imageFile = new File(cacheDir, url + ".png");
        if (!imageFile.exists()) {
            // 如果图片不存在 就开始保存图片
            FileOutputStream fileOutputStream = null;
            try {
                // 创建新文件
                imageFile.createNewFile();
                // 将输出流和刚刚创建的文件联系起来
                // 之后所有通过该输出流的输出的数据 都会写入这个文件
                fileOutputStream = new FileOutputStream(imageFile);
                // 该方法将bitmap转换成fileOutputStream
                //
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
