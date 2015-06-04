package org.xxh.interview.interviewee.service;

import org.xxh.interview.utils.ErrorCode;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonObject;

/**
 * Created by Administrator on 2015/5/25.
 */
public class ServiceError {

    public static boolean isError(JsonObject jsonobj) {
        if(jsonobj == null) {
            return false;
        }

        int code = (int) jsonobj.getNum("code");
        String msg =jsonobj.getString("msg");
        switch (code) {
            case ErrorCode.SUCCESS:
                //这里不写，因为很多成功不需要提示
                return true;
            case ErrorCode.ERROR_CODE_PATH:
                Methods.showToast(msg,false);
                return false;
            case ErrorCode.ERROR:
                Methods.showToast(msg,false);
                return false;
        }
        return  false;
    }
}
