package com.rw.boosterapp.database.daos;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

import java.util.Collection;

/**
 * Created by Rakhita on 4/21/2018.
 */

@DatabaseTable(tableName = "MenuGroup")
public class MenuGroup extends Entity {

    @DatabaseField(columnName = "group_id", canBeNull = false, unique = true)
    private int mGroupId;

    @DatabaseField(columnName = "title", canBeNull = false)
    private String mTitle;

    @ForeignCollectionField(eager = true)
    private Collection<MenuItem> mMenuItems;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Collection<MenuItem> getMenuItems() {
        return mMenuItems;
    }

    public void setmMenuItems(ForeignCollection<MenuItem> menuItems) {
        this.mMenuItems = menuItems;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public void setGroupId(int groupId) {
        this.mGroupId = groupId;
    }
}
