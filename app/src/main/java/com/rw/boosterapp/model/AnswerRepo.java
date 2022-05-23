package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.daos.Answer;
import com.rw.boosterapp.database.ormlite.Repo;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class AnswerRepo extends Repo<Answer> {

    public AnswerRepo(Dao<Answer, Integer> dao) {
        super(dao);
    }
}
