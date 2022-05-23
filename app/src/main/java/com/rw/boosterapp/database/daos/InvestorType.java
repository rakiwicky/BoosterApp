package com.rw.boosterapp.database.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

/**
 * Created by Rakhita on 4/21/2018.
 */

@DatabaseTable(tableName = "InvestorType")
public class InvestorType extends Entity {

    @DatabaseField(columnName = "title", canBeNull = false)
    private String mTitle;

    @DatabaseField(columnName = "description", canBeNull = false)
    private String mDescription;

    @DatabaseField(columnName = "min_score", canBeNull = false)
    private int mMinScore;

    @DatabaseField(columnName = "max_score", canBeNull = false)
    private int mMaxScore;

    @DatabaseField(columnName = "fund_id", canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Fund mFund;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public int getMinScore() {
        return mMinScore;
    }

    public void setMinScore(int minScore) {
        this.mMinScore = minScore;
    }

    public int getMaxScore() {
        return mMaxScore;
    }

    public void setMaxScore(int maxScore) {
        this.mMaxScore = maxScore;
    }

    public Fund getFund() {
        return mFund;
    }

    public void setFund(Fund fund) {
        this.mFund = fund;
    }
}
