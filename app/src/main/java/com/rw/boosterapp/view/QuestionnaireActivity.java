package com.rw.boosterapp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.rw.boosterapp.R;
import com.rw.boosterapp.database.daos.Answer;
import com.rw.boosterapp.database.daos.Question;
import com.rw.boosterapp.database.ormlite.DaoFactory;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.helper.QuestionnaireHelper;
import com.rw.boosterapp.model.QuestionRepo;
import com.rw.boosterapp.model.SelectedAnswerRepo;
import com.rw.boosterapp.presenter.QuestionnairePresenter;
import com.rw.boosterapp.system.BaseActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionnaireActivity extends BaseActivity implements IQuestionnaireView {

    private QuestionnairePresenter mQuestionnairePresenter;
    private TextView mTxtQuestionId, mTxtQuestion;
    private Spinner mSpinnerAnswers;
    private Button mButtonNextQuestion;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        final HashMap<String, Object> params = (HashMap<String, Object>) getIntent().getExtras().get("parameters");
        mCurrentIndex = (int) params.get("index");

        try {
            mQuestionnairePresenter = new QuestionnairePresenter(this, new QuestionRepo(new DaoFactory(this).getQuestionDaoInstance()), new SelectedAnswerRepo(new DaoFactory(this).getSelectedAnswerDaoInstance()), new NavigationHelper(this), new  QuestionnaireHelper(this));
            mTxtQuestionId = findViewById(R.id.txtQuestionNumber);
            mTxtQuestion = findViewById(R.id.txtQuestion);

            mSpinnerAnswers = findViewById(R.id.spinnerAnswers);
            mSpinnerAnswers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object selectedItem = parent.getAdapter().getItem(position);
                    if(selectedItem != null && selectedItem instanceof Answer) {
                        Answer answer = (Answer) parent.getAdapter().getItem(position);
                        mQuestionnairePresenter.onAnswerSelection(answer);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mButtonNextQuestion = findViewById(R.id.btnQuestionNext);
            mButtonNextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentIndex = mCurrentIndex + 1;
                    mQuestionnairePresenter.onNextButtonClick(QuestionnaireActivity.class, mCurrentIndex);
                }
            });

            mQuestionnairePresenter.loadQuestionnaireData(mCurrentIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showQuestionnaireData(Question question) {
        mTxtQuestionId.setText(String.format(getString(R.string.questionNumberFormat), String.valueOf(question.getId())));
        mTxtQuestion.setText(question.getQuestion());

        List<Object> answers = new ArrayList<Object>(question.getAnswers());
        answers.add(0, new DummyItem(getString(R.string.spinnerAnswerDefaultText)));

        final ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<Object>(
                this, R.layout.custom_spinner_dropdown_item, answers) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0)
                    textView.setTextColor(Color.GRAY);
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mSpinnerAnswers.setAdapter(spinnerArrayAdapter);
    }

    public class DummyItem {
        private String mDefaultText;

        public DummyItem(String defaultText) {
            this.mDefaultText = defaultText;
        }

        public String toString() {
            return mDefaultText;
        }
    }
}
