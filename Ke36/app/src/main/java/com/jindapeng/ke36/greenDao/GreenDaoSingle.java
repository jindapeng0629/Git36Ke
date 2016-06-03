package com.jindapeng.ke36.greenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jindapeng.ke36.base.MyApplication;

/**
 * Created by JINDAPENG on 2016/5/28.
 */
public class GreenDaoSingle {
    private SQLiteDatabase db;//数据库
    private DaoMaster daoMaster;//管理者
    private DaoSession daoSession;//回话者
    private CollectionDao collectionDao;//数据库内相应表的操作对像
    private DaoMaster.DevOpenHelper helper;
    private Context context;
    private static GreenDaoSingle ourInstance;


    private GreenDaoSingle() {
        context = MyApplication.getContext();
    }

    public static GreenDaoSingle getOurInstance() {
        if (ourInstance == null) {
            synchronized (GreenDaoSingle.class) {
                if (ourInstance == null) {
                    ourInstance = new GreenDaoSingle();
                }
            }
        }
        return ourInstance;
    }

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "Collection.db", null);
        }
        return helper;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public CollectionDao getCollectionDao() {
        if (collectionDao == null) {
            collectionDao = getDaoSession().getCollectionDao();
        }
        return collectionDao;
    }

}
