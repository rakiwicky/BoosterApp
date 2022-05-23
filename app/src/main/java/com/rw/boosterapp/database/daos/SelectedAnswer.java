package com.rw.boosterapp.database.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

/**
 * Created by Rakhita on 4/24/2018.
 */

@DatabaseTable(tableName = "SelectedAnswer")
public class SelectedAnswer extends Entity {

    @DatabaseField(columnName = "question_id", canBeNull = false, foreign = true)
    private Question mQuestion;

    @DatabaseField(columnName = "answer_id", canBeNull = false, foreign = true)
    private Answer mAnswer;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Question getQuestion() {
        return mQuestion;
    }

    public void setQuestion(Question question) {
        this.mQuestion = question;
    }

    public Answer getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Answer answer) {
        this.mAnswer = answer;
    }
}
