package com.rw.boosterapp.model;

import com.j256.ormlite.dao.Dao;
import com.rw.boosterapp.database.daos.Fund;
import com.rw.boosterapp.database.ormlite.Repo;

/**
 * Created by Rakhita on 4/22/2018.
 */

public class FundRepo extends Repo<Fund> {

    public FundRepo(Dao<Fund, Integer> dao) {
        super(dao);
    }
}
