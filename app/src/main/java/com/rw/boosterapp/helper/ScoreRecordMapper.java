package com.rw.boosterapp.helper;

import java.util.HashMap;

/**
 * Created by Rakhita on 4/25/2018.
 */

public class ScoreRecordMapper {

    private static ScoreRecordMapper mScoreRecordMapper;
    private HashMap<Integer, HashMap<Integer, Integer>> mScoreRecorderMap;
    private int mTotalScore;

    private ScoreRecordMapper() {
        this.mScoreRecorderMap = new HashMap<Integer, HashMap<Integer, Integer>>();
    }

    public static ScoreRecordMapper instance() {
        if (mScoreRecordMapper == null)
            mScoreRecordMapper = new ScoreRecordMapper();
        return mScoreRecordMapper;
    }

    public void setScore(int questionId, int answerId, int score) {
        HashMap<Integer, Integer> ansScoreMap = new HashMap<Integer, Integer>();
        ansScoreMap.put(answerId, score);
        mScoreRecorderMap.put(questionId, ansScoreMap);

        mTotalScore = mTotalScore + score;
    }

    public int getTotalScore(){
        return mTotalScore;
    }

    public void resetScore() {
        mScoreRecorderMap.clear();
        mTotalScore = 0;
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getScoreRecorderMap() {
        return mScoreRecorderMap;
    }
}
