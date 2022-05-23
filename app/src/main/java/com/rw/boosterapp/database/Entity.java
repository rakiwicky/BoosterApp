package com.rw.boosterapp.database;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Rakhita on 4/21/2018.
 */

public abstract class Entity implements IEntity {

    @DatabaseField(columnName = "id", id = true, canBeNull = false)
    protected int mId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
