package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.daos.MenuItem;
import com.rw.boosterapp.database.ormlite.Repo;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class MenuItemRepo extends Repo<MenuItem> {

    public MenuItemRepo(Dao<MenuItem, Integer> dao) {
        super(dao);
    }
}
