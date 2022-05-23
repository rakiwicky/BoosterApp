package com.rw.boosterapp.view;

/**
 * Created by Rakhita on 4/25/2018.
 */

public interface IUserDetailView {
    void showErrorDetailsForEmptyUserName();
    void showErrorDetailsForInvalidEmail();
    void showErrorDetailsForEmptyEmail();
    void showErrorDetailsForInvalidPhone();
    void showErrorDetailsForEmptyPhone();
}
