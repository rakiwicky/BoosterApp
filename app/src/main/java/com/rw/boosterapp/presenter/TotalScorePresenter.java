package com.rw.boosterapp.presenter;

import com.rw.boosterapp.database.daos.InvestorType;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.helper.ScoreRecordMapper;
import com.rw.boosterapp.model.InvestorTypeRepo;
import com.rw.boosterapp.model.SelectedAnswerRepo;
import com.rw.boosterapp.view.ITotalScoreView;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Rakhita on 4/24/2018.
 */

public class TotalScorePresenter implements ITotalScorePresenter {

    private final ITotalScoreView mTotalScoreView;
    private final SelectedAnswerRepo mSelectedAnswerRepo;
    private final InvestorTypeRepo mInvestorTypeRepo;
    private final NavigationHelper mNavigationHelper;
    private String mCurrentInvestorType;

    public TotalScorePresenter(ITotalScoreView totalScoreView, SelectedAnswerRepo selectedAnswerRepo, InvestorTypeRepo investorTypeRepo, NavigationHelper navigationHelper){
        this.mTotalScoreView = totalScoreView;
        this.mSelectedAnswerRepo = selectedAnswerRepo;
        this.mInvestorTypeRepo = investorTypeRepo;
        this.mNavigationHelper = navigationHelper;
    }

    @Override
    public void loadTotalScore() {
        int totalScore = ScoreRecordMapper.instance().getTotalScore();
        InvestorType investorType = null;
        try {
            investorType = mInvestorTypeRepo.getInvesterType(totalScore);
            mCurrentInvestorType = investorType.getTitle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mTotalScoreView.showTotalScoreAndInvestor(totalScore, mCurrentInvestorType);
    }

    @Override
    public void onShowButtonClick(Class nextActivity) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("investerType", mCurrentInvestorType);
        mNavigationHelper.handleNavigation(nextActivity, params);
    }
}
