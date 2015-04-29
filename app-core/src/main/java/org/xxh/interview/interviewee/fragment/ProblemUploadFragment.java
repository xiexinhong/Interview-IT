package org.xxh.interview.interviewee.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.utils.Methods.Methods;

/**
 * Created by Administrator on 2015/4/3.
 */
public class ProblemUploadFragment extends Fragment {

    /***视图**************************************/
    @ViewInject(R.id.title_back_imgbtn)
    private ImageView mBackImg;


    @ViewInject(R.id.title_txt)
    private TextView mTitleTv;
    /***控制**************************************/
    private BaseFragmentActivity mActivity;
    /***数据**************************************/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView =  inflater.inflate(R.layout.fragment_upload_problem,null);
        ViewUtils.inject(this,convertView);
        initData();
        setListener();
        return convertView;
    }

    private void initData() {
        mTitleTv.setText(R.string.upload_problem_title_txt);
    }

    private void setListener() {
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

}
