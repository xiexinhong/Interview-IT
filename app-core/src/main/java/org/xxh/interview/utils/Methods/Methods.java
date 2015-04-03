package org.xxh.interview.utils.Methods;

import org.xxh.interview.app.GlobalVariables;
import org.xxh.interview.app.InterviewApplication;

/**
 * Created by Administrator on 2015/3/25.
 */
public class Methods {

    /**
     * 通过id获取字符串
     * @param id
     * @return
     */
    public static String getResourceString(int id){
        return InterviewApplication.getAppContext().getResources().getString(id);
    }

    public static int computePixelsWithDensity(int dp) {
        return (int) (dp * GlobalVariables.density + 0.5);
    }

    public static int computePixelsTextSize(int sp) {
        return (int) (sp * GlobalVariables.scaledDensity + 0.5);
    }
}


