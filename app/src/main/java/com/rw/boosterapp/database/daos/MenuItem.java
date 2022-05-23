package com.rw.boosterapp.database.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rw.boosterapp.database.Entity;

/**
 * Created by Rakhita on 4/21/2018.
 */

@DatabaseTable(tableName = "MenuItem")
public class MenuItem extends Entity {

    @DatabaseField(columnName = "title", canBeNull = false)
    private String mTitle;

    @DatabaseField(columnName = "menu_group_id", canBeNull = false, foreign = true, foreignColumnName = "group_id", foreignAutoRefresh = true)
    private MenuGroup mMenuGroup;

    private boolean mSkip;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public MenuGroup getMenuGroup() {
        return mMenuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.mMenuGroup = menuGroup;
    }

    public boolean isSkip() {
        return mSkip;
    }

    public void setSkip(boolean mSkip) {
        this.mSkip = mSkip;
    }
}
