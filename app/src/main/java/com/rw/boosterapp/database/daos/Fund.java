package com.rw.boosterapp.database.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

/**
 * Created by Rakhita on 4/22/2018.
 */

@DatabaseTable(tableName = "Fund")
public class Fund extends Entity {

    @DatabaseField(columnName = "title", canBeNull = false)
    private String mTitle;

    @DatabaseField(columnName = "description", canBeNull = false)
    private String mDescription;

    @DatabaseField(columnName = "chart_img_name", canBeNull = false)
    private String mChartImgName;

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

    public String getChartImgName() {
        return mChartImgName;
    }

    public void setChartImgName(String chartImgName) {
        this.mChartImgName = chartImgName;
    }
}