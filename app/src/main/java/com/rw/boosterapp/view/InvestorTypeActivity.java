package com.rw.boosterapp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rw.boosterapp.R;
import com.rw.boosterapp.database.daos.InvestorType;
import com.rw.boosterapp.database.ormlite.DaoFactory;
import com.rw.boosterapp.model.InvestorTypeRepo;
import com.rw.boosterapp.presenter.InvestorTypePresenter;
import com.rw.boosterapp.system.BaseActivity;

import java.sql.SQLException;
import java.util.HashMap;

public class InvestorTypeActivity extends BaseActivity implements IInvestorTypeView {

    private InvestorTypePresenter mInvesterTypePresenter;
    private TextView mTxtInvesterType, mTxtFundType, mTxtFundTypeDesc;
    private ImageView mImageViewGraph;
    private String investerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invester_type);

        HashMap<String, Object> obj = (HashMap<String, Object>) getIntent().getExtras().get("parameters");
        investerType = (String) obj.get("investerType");

        try {
            mInvesterTypePresenter = new InvestorTypePresenter(this, new InvestorTypeRepo(new DaoFactory(this).getInvesterTypeDaoInstance()));
            mTxtInvesterType = findViewById(R.id.txtInvesterType);
            mTxtFundType = findViewById(R.id.txtFundType);
            mImageViewGraph = findViewById(R.id.imgGraph);
            mTxtFundTypeDesc = findViewById(R.id.txtFundTypeDesc);
            mInvesterTypePresenter.loadInvesterTypeData(investerType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showInvesterTypeData(InvestorType investorType) {
        int resID = getResources().getIdentifier(investorType.getFund().getChartImgName(), "drawable",  getPackageName());
        mImageViewGraph.setImageResource(resID);
        mTxtInvesterType.setText(investorType.getTitle());
        mTxtFundType.setText(investorType.getFund().getTitle());
        mTxtFundTypeDesc.setText(investorType.getFund().getDescription());
    }
}
