package org.xxh.interview.interviewee.fragment.personnel;

import org.xxh.interview.R;
import org.xxh.interview.utils.Methods.Methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/5/25.
 */
public class LoginCheckInfo {

    public static boolean checkUserName(String username) {
        String reg = "^[a-zA-Z0-9_-]{3,15}$";
        if(username != null && matcher(reg,username)) {
            return true;
        } else {
            Methods.showToast(R.string.login_password_check_failure,false);
            return false;
        }
    }

    public static boolean checkPassword(String password) {
        String reg = "^[a-zA-Z0-9_-]{6,15}$";
        if(password != null && matcher(reg,password)) {
            return true;
        } else {
            Methods.showToast(R.string.login_username_check_failure,false);
            return false;
        }
    }

    public static boolean checkMail(String mail) {
        String reg = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";
        if(mail != null && matcher(reg,mail)) {
            return true;
        } else {
            Methods.showToast(R.string.register_mail_check_failure,false);
            return false;
        }
    }

    public static boolean matcher(String reg,String arg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(arg);
        return matcher.matches();
    }
}
