package com.rw.boosterapp.presenter;

import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.view.IWelcomeView;

/**
 * Created by Rakhita on 4/19/2018.
 */

public class WelcomePresenter implements IWelcomePresenter {

    private IWelcomeView mIWelcomeView;
    private NavigationHelper mNavigationHelper;

    public WelcomePresenter(IWelcomeView welcomeView, NavigationHelper navigationHelper){
        mIWelcomeView = welcomeView;
        mNavigationHelper = navigationHelper;
    }

    @Override
    public void onClick(Class nextActivityClass) {
        mNavigationHelper.handleNavigation(nextActivityClass);
    }
}
