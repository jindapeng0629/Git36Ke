package com.jindapeng.ke36.greenDao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig collectionDaoConfig;

    private final CollectionDao collectionDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        collectionDaoConfig = daoConfigMap.get(CollectionDao.class).clone();
        collectionDaoConfig.initIdentityScope(type);

        collectionDao = new CollectionDao(collectionDaoConfig, this);

        registerDao(Collection.class, collectionDao);
    }
    
    public void clear() {
        collectionDaoConfig.getIdentityScope().clear();
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

}
