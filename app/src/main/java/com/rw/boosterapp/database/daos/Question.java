package com.rw.boosterapp.database.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

import java.util.Collection;

/**
 * Created by Rakhita on 4/20/2018.
 */

@DatabaseTable(tableName = "Question")
public class Question extends Entity {

    @DatabaseField(columnName = "question", canBeNull = false)
    private String mQuestion;

    @DatabaseField(columnName = "jump_order", canBeNull = false, unique = true)
    private int mQuestionOrder;

    @ForeignCollectionField(eager = true)
    private Collection<Answer> mAnswers;

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public Collection<Answer> getAnswers() {
        return mAnswers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.mAnswers = answers;
    }

    public int getQuestionOrder() {
        return mQuestionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.mQuestionOrder = questionOrder;
    }
}
