package com.rw.boosterapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rw.boosterapp.R;
import com.rw.boosterapp.helper.EmailHelper;
import com.rw.boosterapp.presenter.UserDetailPresenter;
import com.rw.boosterapp.system.BaseActivity;

public class UserDetailActivity extends BaseActivity implements IUserDetailView {

    private UserDetailPresenter mUserDetailPresenter;
    private EditText editTextUserName, editTextPhone, editTextEmail;
    private Button mButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        mUserDetailPresenter = new UserDetailPresenter(this, new EmailHelper(this));
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);

        mButtonSubmit = findViewById(R.id.btnDetailSubmit);
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserDetailPresenter.onSubmitClick(editTextUserName.getText().toString(),
                        editTextEmail.getText().toString(), editTextPhone.getText().toString());
            }
        });
    }

    @Override
    public void showErrorDetailsForEmptyUserName() {
        Toast.makeText(this, R.string.nameEmptyError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorDetailsForInvalidEmail() {
        Toast.makeText(this, R.string.invalidEmailError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorDetailsForEmptyEmail() {
        Toast.makeText(this, R.string.emailEmptyError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorDetailsForInvalidPhone() {
        Toast.makeText(this, R.string.invalidPhoneNumberError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorDetailsForEmptyPhone() {
        Toast.makeText(this, R.string.phoneNumberEmptyError, Toast.LENGTH_SHORT).show();
    }
}
