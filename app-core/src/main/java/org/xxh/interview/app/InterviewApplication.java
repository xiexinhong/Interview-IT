package org.xxh.interview.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/3/20.
 */
public class InterviewApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getAppContext() {
        return mContext;
    }


}
