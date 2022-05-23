package com.rw.boosterapp.presenter;

import com.rw.boosterapp.database.daos.Answer;

/**
 * Created by Rakhita on 4/23/2018.
 */

public interface IQuestionnairePresenter {
    void loadQuestionnaireData(int questionId);
    void onNextButtonClick(Class nextActivity, int jumpOrder);
    void onAnswerSelection(Answer answer);
}
