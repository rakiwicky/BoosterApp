package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.daos.InvestorType;
import com.rw.boosterapp.database.ormlite.Repo;

import java.sql.SQLException;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class InvestorTypeRepo extends Repo<InvestorType> {

    private Dao<InvestorType, Integer> mInvesterTypesDao;
    public InvestorTypeRepo(Dao<InvestorType, Integer> dao) {
        super(dao);
        this.mInvesterTypesDao = dao;
    }

    public InvestorType getInvesterType(String fieldName, Object value) throws SQLException {
        return mInvesterTypesDao.queryBuilder().where().eq(fieldName, value).queryForFirst();
    }

    public InvestorType getInvesterType(int score) throws SQLException {
        return mInvesterTypesDao.queryBuilder().where().ge("max_score", score).and().le("min_score", score).queryForFirst();
    }
}
