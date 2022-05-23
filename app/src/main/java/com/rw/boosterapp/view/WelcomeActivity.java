package com.rw.boosterapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rw.boosterapp.R;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.presenter.WelcomePresenter;

/**
 * Created by Rakhita on 4/19/2018.
 */

public class WelcomeActivity extends Activity implements IWelcomeView {

    private Button mButtonWelcome;
    private WelcomePresenter mWelcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mWelcomePresenter = new WelcomePresenter(this, new NavigationHelper(getApplicationContext()));

        mButtonWelcome = findViewById(R.id.btnWelcome);
        mButtonWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWelcomePresenter.onClick(MainActivity.class);
            }
        });
    }
}
