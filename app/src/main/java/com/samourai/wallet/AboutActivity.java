package com.samourai.wallet;

import android.app.Activity;
import android.os.Bundle;

import com.samourai.wallet.util.AppUtil;
import com.samourai.wallet.util.LocaleUtil;

public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleUtil.updateLocalForSettings(this);
        setContentView(R.layout.activity_about);
        setTitle("Samourai, v" + getResources().getString(R.string.version_name));
    }

    @Override
    public void onResume() {
        super.onResume();

        AppUtil.getInstance(AboutActivity.this).checkTimeOut();

    }
}
