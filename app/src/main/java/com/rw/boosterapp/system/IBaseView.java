package com.rw.boosterapp.system;

import com.rw.boosterapp.database.daos.MenuGroup;

import java.util.List;

/**
 * Created by Rakhita on 4/21/2018.
 */

public interface IBaseView {
    void showMenu(List<MenuGroup> menuGroups);
}
