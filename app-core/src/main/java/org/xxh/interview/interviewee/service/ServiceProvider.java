package org.xxh.interview.interviewee.service;

import com.lidroid.xutils.http.RequestParams;
import org.xxh.interview.interviewee.fragment.personnel.UserInfo;

/**
 * Created by Administrator on 2015/5/22.
 */
public class ServiceProvider {

    public static void getUserInfo(int id,INetResponse response) {
        String url = "user";
        RequestParams params = new RequestParams();
        params.addBodyParameter("id",""+id);
        HttpProvider.addPostRequest(url,params, response);
    }

    /**
     * 用户登录网络请求
     * @param username
     * @param password
     * @param response
     */
    public static void login(String username,String password,INetResponse response) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("username",username);
        params.addBodyParameter("password",password);
        HttpProvider.addPostRequest("user/login", params, response);
    }

    /**
     * 用户注册
     * @param username
     * @param mail
     * @param password
     * @param response
     */
    public static void register(String username,String mail,String password,INetResponse response) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("username",username);
        params.addBodyParameter("mail",mail);
        params.addBodyParameter("password",password);
        HttpProvider.addPostRequest("user/register",params,response);
    }

    /**
     * 编辑用户信息
     * @param nickName
     * @param gender
     * @param introduce
     * @param school
     * @param company
     * @param response
     */
    public static void completeUserInfo(String nickName,String gender,String introduce,String school,String company,INetResponse response) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", UserInfo.getInstance().id+"");
        params.addBodyParameter("nickName",nickName);
        params.addBodyParameter("gender",gender);
        params.addBodyParameter("introduce",introduce);
        params.addBodyParameter("school",school);
        params.addBodyParameter("company",company);
        HttpProvider.addPostRequest("user/complete_info",params,response);
    }

    /**
     * 获取所有label 如果有用户id label多一个choose属性
     * @param userId
     * @param response
     */
    public static void getAllLabelByUserId(int userId,INetResponse response) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("userId",""+userId);
        HttpProvider.addPostRequest("label/query_all_label",params,response);
    }

    /**
     * 修改保存后的label
     * @param array
     * @param response
     */
    public static void updateUserChooseLabel(String array,INetResponse response) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("array",array);
        HttpProvider.addPostRequest("label/update_user_label",params,response);
    }

    /**
     * 获取全部问题分类
     * @param response
     */
    public static void getAllCagetory(INetResponse response) {
        RequestParams params = new RequestParams();
        HttpProvider.addPostRequest("category/querry_all",params,response);
    }

    public static  void getAllSkillCategory(INetResponse response) {
        RequestParams params = new RequestParams();
        HttpProvider.addPostRequest("skill/querry_all",params,response);
    }


}
