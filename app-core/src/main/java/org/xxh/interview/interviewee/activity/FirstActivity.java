package org.xxh.interview.interviewee.activity;


import android.content.Intent;
import android.os.Bundle;
import org.xxh.interview.app.GlobalVariables;
import org.xxh.interview.app.UserVariables;
import org.xxh.interview.interviewee.activity.base.BaseActivity;
import org.xxh.interview.interviewee.fragment.WelcomeFragment;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariables.initGlobalVariables(this);
        if(UserVariables.isWelcomePageShow) {
            WelcomeFragment.showWelcomeFragment(this);
        } else {
            startActivity(new Intent(FirstActivity.this, HomeActivity.class));
        }


    }
}
