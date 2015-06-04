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
import org.xxh.interview.interviewee.activity.LevelTwoActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceError;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonObject;

/**
 * Created by Administrator on 2015/5/25.
 */
public class RegisterFragment extends Fragment {

    /***视图********************************************/
    @ViewInject(R.id.register_username_et)
    private EditText mUsernameEt;

    @ViewInject(R.id.register_mail_et)
    private EditText mMailEt;

    @ViewInject(R.id.register_password_et)
    private EditText mPasswordEt;

    /***控制********************************************/
    private BaseFragmentActivity mActivity;
    /***数据********************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    public static void showFragment(Context context,Bundle args) {
        LevelTwoActivity.showFragment(context,RegisterFragment.class,args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_user_register,null);
        ViewUtils.inject(this,convertView);
        return convertView;
    }

    @OnClick(R.id.title_back_imgbtn)
    public void backImgClick(View v) {
        mActivity.finish();
    }

    @OnClick(R.id.register_submit_btn)
    public void registerSubmitClick(View v) {
        if(LoginCheckInfo.checkUserName(mUsernameEt.getText().toString())
                && LoginCheckInfo.checkMail(mMailEt.getText().toString())
                && LoginCheckInfo.checkPassword(mPasswordEt.getText().toString())) {

            ServiceProvider.register(mUsernameEt.getText().toString(), mMailEt.getText().toString(), mPasswordEt.getText().toString(), new INetResponse() {

                @Override
                public void response(JsonObject jsonObject) {
                    if (ServiceError.isError(jsonObject)) {
                        Methods.showToast(R.string.register_success,false);
                        UserInfo.getInstance().loadUserInfo(jsonObject.getJsonObject("data"));
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                CompleteUserInfoFragment.showFragment(mActivity,null);
                            }
                        });
                    }
                }
            });

        }
    }
}
