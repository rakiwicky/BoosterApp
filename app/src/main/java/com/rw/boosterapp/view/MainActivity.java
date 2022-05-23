package com.rw.boosterapp.view;

import android.os.Bundle;

import com.rw.boosterapp.R;
import com.rw.boosterapp.system.BaseActivity;

public class MainActivity extends BaseActivity implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart() {
        super.onStart();
    }
}
