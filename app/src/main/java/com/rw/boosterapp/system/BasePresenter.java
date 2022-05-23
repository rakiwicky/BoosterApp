package com.rw.boosterapp.system;

import com.rw.boosterapp.database.daos.MenuGroup;
import com.rw.boosterapp.database.daos.MenuItem;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.helper.QuestionnaireHelper;
import com.rw.boosterapp.helper.ScoreRecordMapper;
import com.rw.boosterapp.model.MenuGroupRepo;
import com.rw.boosterapp.model.MenuItemRepo;
import com.rw.boosterapp.view.InvestorTypeActivity;
import com.rw.boosterapp.view.QuestionnaireActivity;
import com.rw.boosterapp.view.UserDetailActivity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class BasePresenter implements IBasePresenter {

    private final IBaseView mView;
    private final MenuGroupRepo mMenuGroupRepo;
    private final MenuItemRepo mMenuItemRepo;
    private final NavigationHelper mNavigationHelper;
    private final QuestionnaireHelper mQuestionnaireHelper;

    public BasePresenter(IBaseView view, MenuGroupRepo menuGroupRepo, MenuItemRepo menuItemRepo, NavigationHelper navigationHelper, QuestionnaireHelper questionnaireHelper) {
        this.mView = view;
        this.mMenuGroupRepo = menuGroupRepo;
        this.mMenuItemRepo = menuItemRepo;
        this.mNavigationHelper = navigationHelper;
        this.mQuestionnaireHelper = questionnaireHelper;
    }

    @Override
    public void loadMenuItems() {
        try {
            List<MenuGroup> menuGroups = mMenuGroupRepo.getEntityColl();
            boolean isQuestionnaireCompleted = mQuestionnaireHelper.isQuestionnaireCompleted();
            if (!isQuestionnaireCompleted) {
                for (MenuGroup menuGroup : menuGroups) {
                    if (menuGroup.getTitle().contains("Questionnaire")) {
                        Collection<MenuItem> menuItems = menuGroup.getMenuItems();
                        for (MenuItem menuItem : menuItems) {
                            if (menuItem.getTitle().contains("Submit")) {
                                menuItem.setSkip(true);
                                break;
                            }
                        }
                    }
                }
            }
            mView.showMenu(menuGroups);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickMenuItem(int itemId) {
        try {
            MenuItem menuItem = mMenuItemRepo.getEntity(itemId);
            if (menuItem != null) {
                MenuGroup menuGroup = menuItem.getMenuGroup();
                if (menuGroup != null) {
                    String menuGroupTitle = menuGroup.getTitle();
                    if (menuGroupTitle.contains("Investor Type")) {
                        String menuItemTitle = menuItem.getTitle();
                        HashMap<String, Object> params = new HashMap<String, Object>();
                        params.put("investerType", menuItemTitle);
                        mNavigationHelper.handleNavigation(InvestorTypeActivity.class, params);
                    } else if (menuGroupTitle.contains("Questionnaire")) {
                        if(menuItem.getTitle().contains("Submit")){
                            mNavigationHelper.handleNavigation(UserDetailActivity.class);
                        }
                        else {
                            ScoreRecordMapper.instance().resetScore();
                            mQuestionnaireHelper.reset();
                            HashMap<String, Object> params = new HashMap<String, Object>();
                            params.put("index", 0);
                            mNavigationHelper.handleNavigation(QuestionnaireActivity.class, params);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
