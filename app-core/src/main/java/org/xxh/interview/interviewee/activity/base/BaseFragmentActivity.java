package org.xxh.interview.interviewee.activity.base;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class BaseFragmentActivity extends FragmentActivity {
    private static final String TAG = "BaseFragmentActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
