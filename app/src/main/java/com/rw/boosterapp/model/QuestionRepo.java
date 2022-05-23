package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.daos.Question;
import com.rw.boosterapp.database.ormlite.Repo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class QuestionRepo extends Repo<Question> {

    private Dao<Question, Integer> mQuestionDao;

    public QuestionRepo(Dao<Question, Integer> dao) {
        super(dao);
        mQuestionDao = dao;
    }

    @Override
    public List getEntityColl() throws SQLException {
        return mQuestionDao.queryBuilder().orderBy("jump_order", true).query();
    }
}
