package org.xxh.interview.interviewee.fragment.personnel;

import org.xxh.interview.utils.json.JsonObject;

/**
 * Created by Administrator on 2015/5/25.
 */
public class UserInfo {

    private static UserInfo  instance = null;

    public synchronized static UserInfo getInstance() {
        if(instance == null) {
            instance = new UserInfo();
        }
        return instance;
    }

    private UserInfo() {

    }


    public int id;
    public String name;
    public String mail;
    public String nickName;
    public String introduce;
    public String password;
    public String gender; // 0 - 女 1 - 男
    public String school;
    public String company;
    public boolean status; // false 未登陆  true 登陆

    public void loadUserInfo(JsonObject obj) {
        id = (int) obj.getNum("id");
        name = obj.getString("name");
        mail = obj.getString("mail");
        nickName = obj.getString("nickName");
        introduce = obj.getString("introduce");
        password = obj.getString("password");
        gender = obj.getString("gender");
        school = obj.getString("school");
        company = obj.getString("company");
        status = true;
    }

    public static String genderToNum(String arg) {
        if(arg == null)
            return "2";
        if(arg.equals("女")) {
            return "0";
        } else if(arg.equals("男")) {
            return "1";
        } else {
            return "2";
        }
    }

    public static String numToGender(String num) {
        if(num == null)
            return "保密";
        if(num.equals("0")) {
            return "女";
        } else if(num.equals("1")) {
            return "男";
        } else {
            return "保密";
        }
    }

    public void cleanInfo() {
        id = 0;
        name = null;
        mail = null;
        nickName = null;
        introduce = null;
        password = null;
        gender = null;
        school = null;
        company = null;
        status = false;
    }

}
