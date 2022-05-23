package com.rw.boosterapp.database.ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.rw.boosterapp.database.daos.Answer;
import com.rw.boosterapp.database.daos.Fund;
import com.rw.boosterapp.database.daos.InvestorType;
import com.rw.boosterapp.database.daos.MenuGroup;
import com.rw.boosterapp.database.daos.MenuItem;
import com.rw.boosterapp.database.daos.Question;
import com.rw.boosterapp.database.daos.SelectedAnswer;

import java.sql.SQLException;

/**
 * Created by Rakhita on 4/20/2018.
 */

public class DaoFactory {

    private Context mContext;

    public DaoFactory(Context context) {
        mContext = context;
    }

    private static Dao<Question, Integer> mQuestionDao;
    public Dao<Question, Integer> getQuestionDaoInstance() throws SQLException {
        if (mQuestionDao == null) {
            mQuestionDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    Question.class);
        }
        return mQuestionDao;
    }

    private static Dao<Answer, Integer> mAnswerDao;
    public Dao<Answer, Integer> getAnswerDaoInstance() throws SQLException {
        if (mAnswerDao == null) {
            mAnswerDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    Answer.class);
        }
        return mAnswerDao;
    }

    private static Dao<MenuGroup, Integer> mMenuGroupDao;
    public Dao<MenuGroup, Integer> getMenuGroupDaoInstance() throws SQLException {
        if (mMenuGroupDao == null) {
            mMenuGroupDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    MenuGroup.class);
        }
        return mMenuGroupDao;
    }

    private static Dao<MenuItem, Integer> mMenuItemDao;
    public Dao<MenuItem, Integer> getMenuItemDaoInstance() throws SQLException {
        if (mMenuItemDao == null) {
            mMenuItemDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    MenuItem.class);
        }
        return mMenuItemDao;
    }

    private static Dao<InvestorType, Integer> mInvesterTypeDao;
    public Dao<InvestorType, Integer> getInvesterTypeDaoInstance() throws SQLException {
        if (mInvesterTypeDao == null) {
            mInvesterTypeDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    InvestorType.class);
        }
        return mInvesterTypeDao;
    }

    private static Dao<Fund, Integer> mFundDao;
    public Dao<Fund, Integer> getFundDaoInstance() throws SQLException {
        if (mFundDao == null) {
            mFundDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    Fund.class);
        }
        return mFundDao;
    }

    private static Dao<SelectedAnswer, Integer> mSelectedAnswerDao;
    public Dao<SelectedAnswer, Integer> getSelectedAnswerDaoInstance() throws SQLException {
        if (mSelectedAnswerDao == null) {
            mSelectedAnswerDao = DaoManager.createDao(new DBHelper(mContext).getConnectionSource(),
                    SelectedAnswer.class);
        }
        return mSelectedAnswerDao;
    }
}
