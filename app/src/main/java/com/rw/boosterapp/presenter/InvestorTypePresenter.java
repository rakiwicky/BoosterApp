package com.rw.boosterapp.presenter;

import com.rw.boosterapp.database.daos.InvestorType;
import com.rw.boosterapp.model.InvestorTypeRepo;
import com.rw.boosterapp.view.IInvestorTypeView;

import java.sql.SQLException;

/**
 * Created by Rakhita on 4/22/2018.
 */

public class InvestorTypePresenter implements IInvestorTypePresenter {

    private final IInvestorTypeView mIInvestorTypeView;
    private final InvestorTypeRepo mInvestorTypeRepo;

    public InvestorTypePresenter(IInvestorTypeView investerTypeView, InvestorTypeRepo investorTypeRepo){
        this.mIInvestorTypeView = investerTypeView;
        this.mInvestorTypeRepo = investorTypeRepo;
    }

    @Override
    public void loadInvesterTypeData(String investerTypeTitle) {
        try {
            InvestorType investorType = mInvestorTypeRepo.getInvesterType("title", investerTypeTitle);
            mIInvestorTypeView.showInvesterTypeData(investorType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
