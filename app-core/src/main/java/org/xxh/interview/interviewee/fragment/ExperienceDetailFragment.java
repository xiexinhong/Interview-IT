package org.xxh.interview.interviewee.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;

/**
 * Created by Administrator on 2015/4/17.
 */
public class ExperienceDetailFragment extends Fragment{
    /***视图*************************************************/
    @ViewInject(R.id.experience_detail_back_imgbtn)
    private ImageView mBackImg;
    /***控制*************************************************/
    private BaseFragmentActivity mActivity;
    /***数据*************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    public static void showFragment(Context context,Bundle args) {
        TerminalActivity.showFragment(context,ExperienceDetailFragment.class,args);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_detail,null);
        ViewUtils.inject(this,view);
        setListener();
        return view;
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
