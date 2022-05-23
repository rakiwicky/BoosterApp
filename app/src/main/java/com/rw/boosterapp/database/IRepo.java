package com.rw.boosterapp.database;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rakhita on 4/20/2018.
 */

public interface IRepo<TEntity extends IEntity> {

    List getEntityColl() throws SQLException;
    TEntity getEntity(int id) throws SQLException;
    void deleteEntity(TEntity deleteEntity) throws SQLException;
    void saveEntity(TEntity saveEntity) throws SQLException;
    void updateEntity(TEntity updateEntity) throws SQLException;
}