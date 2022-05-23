package com.rw.boosterapp.database.ormlite;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.IEntity;
import com.rw.boosterapp.database.IRepo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rakhita on 4/20/2018.
 */

public abstract class Repo<TEntity extends IEntity> implements IRepo<TEntity> {

    protected Dao<TEntity, Integer> dao;

    public Repo(Dao<TEntity, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public List getEntityColl() throws SQLException {
        return dao.queryForAll();
    }

    @Override
    public TEntity getEntity(int id) throws SQLException {
        return dao.queryForId(id);
    }

    @Override
    public void deleteEntity(TEntity deleteEntity) throws SQLException {
        dao.delete(deleteEntity);
    }

    @Override
    public void saveEntity(TEntity saveEntity) throws SQLException {
        dao.create(saveEntity);
    }

    @Override
    public void updateEntity(TEntity updateEntity) throws SQLException {
        dao.update(updateEntity);
    }
}
