package com.rw.boosterapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rw.boosterapp.R;
import com.rw.boosterapp.database.ormlite.DaoFactory;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.model.InvestorTypeRepo;
import com.rw.boosterapp.model.SelectedAnswerRepo;
import com.rw.boosterapp.presenter.TotalScorePresenter;
import com.rw.boosterapp.system.BaseActivity;

import java.sql.SQLException;

public class TotalScoreActivity extends BaseActivity implements ITotalScoreView {

    private TotalScorePresenter mTotalScorePresenter;
    private TextView txtYourScroeDesc, txtYourScore, txtYourInvesterTypeDesc, txtYourInvesterType;
    private Button mButtonShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_score);

        try {
            mTotalScorePresenter = new TotalScorePresenter(this,
                    new SelectedAnswerRepo(new DaoFactory(this).getSelectedAnswerDaoInstance()),
                    new InvestorTypeRepo(new DaoFactory(this).getInvesterTypeDaoInstance()),
                    new NavigationHelper(this));

            txtYourScroeDesc = findViewById(R.id.txtYourScroeDesc);
            txtYourScore = findViewById(R.id.txtYourScore);
            txtYourInvesterTypeDesc = findViewById(R.id.txtYourInvesterTypeDesc);
            txtYourInvesterType = findViewById(R.id.txtYourInvesterType);

            mButtonShow = findViewById(R.id.btnShow);
            mButtonShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTotalScorePresenter.onShowButtonClick(InvestorTypeActivity.class);
                }
            });

            mTotalScorePresenter.loadTotalScore();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showTotalScoreAndInvestor(int totalScore, String investorType) {
        txtYourScroeDesc.setText(getString(R.string.yourTotalScoreDesc));
        txtYourScore.setText(String.valueOf(totalScore));
        txtYourInvesterTypeDesc.setText(getString(R.string.youAreAnInvesterTypeDesc));
        txtYourInvesterType.setText(String.format(getString(R.string.youAreAnInvesterType), investorType));
    }
}
