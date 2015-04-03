package org.xxh.interview.interviewee.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;

/**
 * Created by Administrator on 2015/4/3.
 */
public class PraiseFragment extends Fragment {

    /***视图*********************************/
    @ViewInject(R.id.praise_scrollview)
    private ScrollView mScrollView;

    @ViewInject(R.id.praise_linear_layout)
    private LinearLayout mLinearLayout;
    /***控制*********************************/
    private BaseFragmentActivity mActivity;
    /***数据*********************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_praise,null);
        ViewUtils.inject(this,view);
        initListener();
        return view;
    }

    private void initListener() {
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = mLinearLayout.getLayoutParams();
                params.height =(int)(params.height * 1.1);
                //params.width =(int) (params.width * 1.1);
                mLinearLayout.setLayoutParams(params);
            }
        });
    }
}

