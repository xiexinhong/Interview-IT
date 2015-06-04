package org.xxh.interview.app;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by xinhong.xie on 2015/3/20.
 */
public class GlobalVariables  {

    /********************************/
    public final static String LOCAL_SECURITY_KEY = "k10k2k5mlo";
    public final static String HTTP_URL_PREFIX = "http://10.2.43.199:8081/interview/client/";
    public static float density;// 屏幕密度（0.75 / 1.0 / 1.5）
    public static float scaledDensity;// 屏幕密度（0.75 / 1.0 / 1.5）


    public static void initGlobalVariables(Activity activity) {
        /*设置屏幕密度*/
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        density = metric.density;
        scaledDensity = metric.scaledDensity;
    }

}
