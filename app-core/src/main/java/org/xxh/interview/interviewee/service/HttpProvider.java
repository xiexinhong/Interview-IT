package org.xxh.interview.interviewee.service;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import org.xxh.interview.R;
import org.xxh.interview.app.GlobalVariables;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonObject;
import org.xxh.interview.utils.json.JsonParser;

/**
 * Created by Administrator on 2015/5/22.
 */
public class HttpProvider {

    public static void addPostRequest(String url,RequestParams params, final INetResponse response) {
        //网络有问题就不发送
        if(!Methods.isMobileConnectedNet()) {
            Methods.showToast(R.string.net_no_connect,false);
            return;
        }

        HttpUtils http = new HttpUtils();
        params.addBodyParameter("key",GlobalVariables.LOCAL_SECURITY_KEY);
        http.send(HttpRequest.HttpMethod.POST, GlobalVariables.HTTP_URL_PREFIX+url,params,new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                response.response(JsonObject.parseObject(responseInfo.result));
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Methods.showToast(R.string.net_request_failure,false);
            }
        });

    }
}
