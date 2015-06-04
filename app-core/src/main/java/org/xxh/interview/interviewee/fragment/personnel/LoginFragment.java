package org.xxh.interview.interviewee.fragment.personnel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceError;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonObject;

/**
 * Created by Administrator on 2015/5/21.
 */
public class LoginFragment extends Fragment {

    /***视图**************************************/
    @ViewInject(R.id.login_username_et)
    private EditText mUserNameEt;

    @ViewInject(R.id.login_password_et)
    private EditText mPasswordEt;



    /***控制**************************************/
    private BaseFragmentActivity mActivity;
    /***数据**************************************/

    public static void showLoginFragment(Context context,Bundle args) {
        TerminalActivity.showFragment(context,LoginFragment.class,args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_login,null);
        ViewUtils.inject(this,convertView);
        return convertView;
    }

    @OnClick(R.id.title_back_imgbtn)
    public void backImgClick  (View v) {
        mActivity.finish();
    }

    @OnClick(R.id.login_submit_btn)
    public void loginBtnClick(View v) {
        if(LoginCheckInfo.checkUserName(mUserNameEt.getText().toString()) && LoginCheckInfo.checkPassword(mPasswordEt.getText().toString())) {
            ServiceProvider.login(mUserNameEt.getText().toString(), mPasswordEt.getText().toString(), new INetResponse() {
                @Override
                public void response(JsonObject jsonObject) {
                    if(ServiceError.isError(jsonObject)) {
                        Methods.showToast(R.string.login_success,false);
                        UserInfo.getInstance().loadUserInfo(jsonObject.getJsonObject("data"));
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActivity.finish();
                            }
                        });
                    }
                }
            });
        }

    }

    @OnClick(R.id.login_register_tv)
    public void loginRegisterClick(View v) {
        RegisterFragment.showFragment(mActivity,null);
    }




}
