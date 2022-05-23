package com.rw.boosterapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rakhita on 4/23/2018.
 */

public class QuestionnaireHelper {

    private boolean mQuestionnaireCompleted;
    private Context mContext;

    public QuestionnaireHelper(Context context) {
        this.mContext = context;
    }

    public void setQuestionnaireCompleted(){
        SharedPreferences sharedPreferenceQueStat = mContext.getSharedPreferences("QuestionnaireStats", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferenceQueStat.edit();
        editor.putBoolean("QuestionnaireCompleted", true);
        editor.putInt("Score", ScoreRecordMapper.instance().getTotalScore());
        editor.apply();
    }

    public boolean isQuestionnaireCompleted() {
        SharedPreferences sharedPreferenceQueStat = mContext.getSharedPreferences("QuestionnaireStats", Context.MODE_PRIVATE);
        if(sharedPreferenceQueStat.contains("QuestionnaireCompleted")){
            mQuestionnaireCompleted = sharedPreferenceQueStat.getBoolean("QuestionnaireCompleted", false);
        }
        return mQuestionnaireCompleted;
    }

    public void reset(){
        SharedPreferences sharedPreferenceQueStat = mContext.getSharedPreferences("QuestionnaireStats", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferenceQueStat.edit();
        editor.clear();
        editor.apply();
    }
}
