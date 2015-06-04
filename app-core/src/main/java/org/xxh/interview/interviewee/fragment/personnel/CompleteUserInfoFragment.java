package org.xxh.interview.interviewee.fragment.personnel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.RequestCode;
import org.xxh.interview.interviewee.activity.HomeActivity;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceError;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonObject;

/**
 * Created by Administrator on 2015/5/26.
 */
public class CompleteUserInfoFragment extends Fragment {

    /***视图****************************************************/
    @ViewInject(R.id.title_back_imgbtn)
    private ImageView mBackImg;
    @ViewInject(R.id.title_txt)
    private TextView mTitleTxtTv;
    @ViewInject(R.id.title_save_img)
    private ImageView mSaceImg;

    @ViewInject(R.id.complete_user_name_tv)
    private TextView mNameTv;
    @ViewInject(R.id.complete_user_nickname_tv)
    private TextView mNickNameTv;
    @ViewInject(R.id.complete_user_gender_tv)
    private TextView mGenderTv;
    @ViewInject(R.id.complete_user_introduce_tv)
    private TextView mIntroduceTv;
    @ViewInject(R.id.complete_user_school_tv)
    private TextView mSchoolTv;
    @ViewInject(R.id.complete_user_company_tv)
    private TextView mCompanyTv;
    /***控制****************************************************/
    private BaseFragmentActivity mActivity;
    /***数据****************************************************/
    public final static String RESULT_STR = "result_str";
    public final static String[] GENDER_ITEMS = new String[]{"男","女","保密"};

    public static void showFragment(Context context,Bundle args) {
        TerminalActivity.showFragment(context,CompleteUserInfoFragment.class,args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_complete_user_info,null);
        ViewUtils.inject(this,convertView);
        initData();
        return convertView;
    }

    private void initData() {
        mTitleTxtTv.setText(R.string.complete_title_txt);
        UserInfo user = UserInfo.getInstance();
        mNameTv.setText(user.name);
        mNickNameTv.setText(user.nickName);
        mGenderTv.setText(UserInfo.numToGender(user.gender));
        mIntroduceTv.setText(user.introduce);
        mSchoolTv.setText(user.school);
        mCompanyTv.setText(user.company);
    }

    @OnClick(R.id.title_back_imgbtn)
    public void backImgClick(View view) {
        mActivity.finish();
    }


    @OnClick(R.id.complete_user_nickname_tv)
    public void nameClick(View v) {
        EditFieldFragment.showResultFragment(this,RequestCode.COMPLETE_USER_NICK_NAME, Methods.getResourceString(R.string.complete_user_nickname),mNickNameTv.getText().toString());
    }

    @OnClick(R.id.complete_user_introduce_tv)
    public void introduceClick(View v) {
        EditFieldFragment.showResultFragment(this,RequestCode.COMPLETE_USER_INTRODUCE_NAME, Methods.getResourceString(R.string.complete_user_introduce),mIntroduceTv.getText().toString());
    }

    @OnClick(R.id.complete_user_school_tv)
    public void schoolClick(View v) {
        EditFieldFragment.showResultFragment(this,RequestCode.COMPLETE_USER_SCHOOL_NAME, Methods.getResourceString(R.string.complete_user_school),mSchoolTv.getText().toString());
    }

    @OnClick(R.id.complete_user_company_tv)
    public void companyClick(View v) {
        EditFieldFragment.showResultFragment(this,RequestCode.COMPLETE_USER_COMPANY_NAME, Methods.getResourceString(R.string.complete_user_company),mCompanyTv.getText().toString());
    }

    @OnClick(R.id.complete_user_gender_tv)
    public void genderClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setItems(GENDER_ITEMS, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mGenderTv.setText(GENDER_ITEMS[which]);
            }
        });
        builder.show();
    }

    @OnClick(R.id.title_save_img)
    public void saveImgClick(View v) {
        ServiceProvider.completeUserInfo(mNickNameTv.getText().toString(),UserInfo.genderToNum(mGenderTv.getText().toString()),mIntroduceTv.getText().toString(),mSchoolTv.getText().toString(),
                mCompanyTv.getText().toString(), new INetResponse() {
                    @Override
                    public void response(JsonObject jsonObject) {
                        if(ServiceError.isError(jsonObject)) {
                            UserInfo user = UserInfo.getInstance();
                            user.nickName = mNickNameTv.getText().toString();
                            user.gender = mGenderTv.getText().toString();
                            user.introduce = mIntroduceTv.getText().toString();
                            user.school = mSchoolTv.getText().toString();
                            user.company = mCompanyTv.getText().toString();
                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mActivity.startActivity(new Intent(mActivity,HomeActivity.class));
                                }
                            });
                        }
                    }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String tmp = data.getStringExtra(RESULT_STR);
        switch (requestCode) {
            case RequestCode.COMPLETE_USER_NICK_NAME:
                mNickNameTv.setText(tmp);
                break;
            case RequestCode.COMPLETE_USER_INTRODUCE_NAME:
                mIntroduceTv.setText(tmp);
                break;
            case RequestCode.COMPLETE_USER_SCHOOL_NAME:
                mSchoolTv.setText(tmp);
                break;
            case  RequestCode.COMPLETE_USER_COMPANY_NAME:
                mCompanyTv.setText(tmp);
                break;
        }
    }

}
