package org.xxh.interview.interviewee.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.utils.Methods.Methods;

/**
 * Created by Administrator on 2015/4/21.
 */
public class ExperienceUploadFragment extends Fragment {

    /***视图************************************************/
    @ViewInject(R.id.title_back_imgbtn)
    private ImageView mBackImg;

    @ViewInject(R.id.title_txt)
    private TextView mTitleTxt;
    /***控制************************************************/
    private BaseFragmentActivity mActivity;
    /***数据************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_experience,null);
        ViewUtils.inject(this,view);
        initData();
        setListener();
        return view;
    }

    private void initData() {
        mTitleTxt.setText(R.string.upload_experience_title_txt);
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
