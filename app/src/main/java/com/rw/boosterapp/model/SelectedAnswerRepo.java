package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.rw.boosterapp.database.daos.SelectedAnswer;
import com.rw.boosterapp.database.ormlite.Repo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rakhita on 4/24/2018.
 */

public class SelectedAnswerRepo extends Repo<SelectedAnswer> {

    private Dao<SelectedAnswer, Integer> mSelectedAnswerDao;

    public SelectedAnswerRepo(Dao<SelectedAnswer, Integer> dao) {
        super(dao);
        mSelectedAnswerDao = dao;
    }

    public int getTotalScore(){
        GenericRawResults<String[]> rawResults =
                null;
        try {
            rawResults = mSelectedAnswerDao.queryRaw(
                    "select sum() from selectedanswer inner join answer on selectedanswer.answer_id = answer.id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String[]> results = null;
        try {
            results = rawResults.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] resultArray = results.get(0);

        return Integer.parseInt(resultArray[0]);
    }
}
