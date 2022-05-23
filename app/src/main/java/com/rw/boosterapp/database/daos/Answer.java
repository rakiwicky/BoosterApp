package com.rw.boosterapp.database.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

/**
 * Created by Rakhita on 4/20/2018.
 */

@DatabaseTable(tableName = "Answer")
public class Answer extends Entity {

    @DatabaseField(columnName = "answer", canBeNull = false)
    private String mAnswer;

    @DatabaseField(columnName = "score", canBeNull = false)
    private int mScore;

    @DatabaseField(columnName = "question_id", canBeNull = false, foreign = true)
    private Question mQuestion;

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        this.mScore = score;
    }

    public Question getQuestion() {
        return mQuestion;
    }

    public void setQuestion(Question question) {
        this.mQuestion = question;
    }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return mAnswer;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Answer){
            Answer answer = (Answer )obj;
            if(answer.getAnswer().equals(mAnswer) && answer.getId() == super.getId() ) return true;
        }

        return false;
    }
}
