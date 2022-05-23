package com.rw.boosterapp.presenter;

import com.rw.boosterapp.database.daos.Answer;
import com.rw.boosterapp.database.daos.Question;
import com.rw.boosterapp.database.daos.SelectedAnswer;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.helper.QuestionnaireHelper;
import com.rw.boosterapp.helper.ScoreRecordMapper;
import com.rw.boosterapp.model.QuestionRepo;
import com.rw.boosterapp.model.SelectedAnswerRepo;
import com.rw.boosterapp.view.IQuestionnaireView;
import com.rw.boosterapp.view.TotalScoreActivity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rakhita on 4/23/2018.
 */

public class QuestionnairePresenter implements IQuestionnairePresenter {

    private final IQuestionnaireView mQuestionnaireView;
    private final QuestionRepo mQuestionRepo;
    private final SelectedAnswerRepo mSelectedAnswerRepo;
    private final NavigationHelper mNavigationHelper;
    private List<Question> mQuestionList;
    private final QuestionnaireHelper mQuestionnaireHelper;

    public QuestionnairePresenter(IQuestionnaireView questionnaireView, QuestionRepo questionRepo, SelectedAnswerRepo selectedAnswerRepo, NavigationHelper navigationHelper, QuestionnaireHelper questionnaireHelper) {
        this.mQuestionnaireView = questionnaireView;
        this.mQuestionRepo = questionRepo;
        this.mSelectedAnswerRepo = selectedAnswerRepo;
        this.mNavigationHelper = navigationHelper;
        this.mQuestionnaireHelper = questionnaireHelper;
    }

    @Override
    public void loadQuestionnaireData(int index) {
        try {
            mQuestionList = mQuestionRepo.getEntityColl();
            Question question = mQuestionList.get(index);
            mQuestionnaireView.showQuestionnaireData(question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNextButtonClick(Class nextActivity, int index) {
        if(index < mQuestionList.size()) {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("index", index);
            mNavigationHelper.handleNavigation(nextActivity, params);
        }
        else{
            mQuestionnaireHelper.setQuestionnaireCompleted();
            mNavigationHelper.handleNavigation(TotalScoreActivity.class);
        }
    }

    @Override
    public void onAnswerSelection(Answer answer) {
        if(answer != null) {
            int score = answer.getScore();
            Question question = answer.getQuestion();

            SelectedAnswer selectedAnswer = new SelectedAnswer();
            selectedAnswer.setId(question.getId());
            selectedAnswer.setQuestion(question);
            selectedAnswer.setAnswer(answer);
            try {
                mSelectedAnswerRepo.saveEntity(selectedAnswer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ScoreRecordMapper.instance().setScore(answer.getQuestion().getId(), answer.getId(), score);
        }
    }
}