package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.daos.MenuGroup;
import com.rw.boosterapp.database.ormlite.Repo;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class MenuGroupRepo extends Repo<MenuGroup> {

    public MenuGroupRepo(Dao<MenuGroup, Integer> dao) {
        super(dao);
    }
}