package org.xxh.interview.interviewee.fragment.personnel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;

/**
 * Created by Administrator on 2015/5/26.
 */
public class EditFieldFragment extends Fragment {

    /***视图***************************************************/
    @ViewInject(R.id.title_txt)
    private TextView mTitleTxtTV;

    @ViewInject(R.id.edit_field_result)
    private EditText mEditFieldEt;
    /***控制***************************************************/
    private BaseFragmentActivity mActivity;
    /***数据***************************************************/
    private int mReqCode;
    public final static String TITLE_HINT = "title_hint";
    public final static String REQ_CODE = "req_code";
    public final static String DEF_ARG = "def_arg";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    public static void showResultFragment(Fragment fragment,int reqCode,String titleTxt,String defarg) {
        Bundle args = new Bundle();
        args.putString(TITLE_HINT,titleTxt);
        args.putInt(REQ_CODE, reqCode);
        args.putString(DEF_ARG, defarg);
        TerminalActivity.showFragmentForResult(fragment,EditFieldFragment.class,args,reqCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.edit_field_fragment,null);
        ViewUtils.inject(this,convertView);
        initData();
        return convertView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            mReqCode = bundle.getInt(REQ_CODE);
            mEditFieldEt.setText(bundle.getString(DEF_ARG));
            mTitleTxtTV.setText(String.format(mActivity.getResources().getString(R.string.edit_field_title_txt),bundle.getString(TITLE_HINT)));
        }
    }

    @OnClick(R.id.title_back_imgbtn)
    public void backImgClick(View view) {
        Intent intent = new Intent();
        intent.putExtra(CompleteUserInfoFragment.RESULT_STR,mEditFieldEt.getText().toString());
        mActivity.setResult(-mReqCode,intent); //结果码为负数
        mActivity.finish();
    }

    @OnClick(R.id.title_save_img)
    public void saveImgClick(View view) {
        Intent intent = new Intent();
        intent.putExtra(CompleteUserInfoFragment.RESULT_STR,mEditFieldEt.getText().toString());
        mActivity.setResult(-mReqCode,intent); //结果码为负数
        mActivity.finish();
    }
}
