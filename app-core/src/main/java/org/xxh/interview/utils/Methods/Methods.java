package org.xxh.interview.utils.Methods;

import android.content.DialogInterface;
import android.widget.Toast;
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

    public static void showToast(String msg,boolean isLong) {
        Toast.makeText(InterviewApplication.getAppContext(),msg,isLong == true ? Toast.LENGTH_SHORT : Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int resourcesId,boolean isLong) {
        Toast.makeText(InterviewApplication.getAppContext(),resourcesId,isLong == true ? Toast.LENGTH_SHORT : Toast.LENGTH_SHORT).show();
    }
}


