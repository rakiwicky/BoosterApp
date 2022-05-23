package com.rw.boosterapp.presenter;

import android.content.Intent;

import com.rw.boosterapp.helper.EmailHelper;
import com.rw.boosterapp.view.IUserDetailView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rakhita on 4/25/2018.
 */

public class UserDetailPresenter implements IUserDetailPresenter {

    private final IUserDetailView mUserDetailView;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    private EmailHelper mEmailHelper;

    public UserDetailPresenter(IUserDetailView userDetailView, EmailHelper emailHelper) {
        this.mUserDetailView = userDetailView;
        this.mEmailHelper = emailHelper;
    }

    @Override
    public void onSubmitClick(String userName, String email, String phone) {
        try {
            if (userName == null || (userName != null && userName.isEmpty())) {
                mUserDetailView.showErrorDetailsForEmptyUserName();
                return;
            }

            if (email != null && !email.isEmpty()) {
                if (!isValidMail(email)) {
                    mUserDetailView.showErrorDetailsForInvalidEmail();
                    return;
                }
            } else {
                mUserDetailView.showErrorDetailsForEmptyEmail();
                return;
            }

            if (phone != null && !phone.isEmpty()) {
                if (!isValidMobile(phone)) {
                    mUserDetailView.showErrorDetailsForInvalidPhone();
                    return;
                }
            } else {
                mUserDetailView.showErrorDetailsForEmptyPhone();
                return;
            }

            mEmailHelper.sendMail("me@example.com ", userName, email, phone);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidMail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidMobile(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
