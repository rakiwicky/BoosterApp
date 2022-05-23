package com.rw.boosterapp.helper;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class NavigationHelper {

    private Context mContext;

    public NavigationHelper(Context context) {
        this.mContext = context;
    }

    public void handleNavigation(Class nextActivity){
        Intent intent = new Intent(mContext, nextActivity);
        mContext.startActivity(intent);
    }

    public void handleNavigation(Class nextActivity, HashMap<String, Object> params){
        Intent intent = new Intent(mContext, nextActivity);
        intent.putExtra("parameters", params);
        mContext.startActivity(intent);
    }
}
