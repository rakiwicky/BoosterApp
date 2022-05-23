package com.rw.boosterapp.helper;

import android.content.Context;
import android.content.Intent;

import com.rw.boosterapp.R;

/**
 * Created by Rakhita on 4/25/2018.
 */

public class EmailHelper {

    private Context mContext;

    public EmailHelper(Context context) {
        this.mContext = context;
    }

    public void sendMail(String sendEmail, String userName, String userPhone, String userEmail) {
        try {
            Intent it = new Intent(Intent.ACTION_SEND);
            it.putExtra(Intent.EXTRA_EMAIL, new String[]{sendEmail});
            it.putExtra(Intent.EXTRA_SUBJECT, "Score Submit");
            it.putExtra(Intent.EXTRA_TEXT, String.format(mContext.getString(R.string.submitName), userName) +
                    String.format(mContext.getString(R.string.submitPhone), userPhone) +
                    String.format(mContext.getString(R.string.submitEmail), userEmail));
            it.setType("message/rfc822");
            mContext.startActivity(Intent.createChooser(it, "Choose Mail App"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
