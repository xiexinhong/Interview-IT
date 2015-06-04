package org.xxh.interview.interviewee.fragment.menu.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;

/**
 * Created by Administrator on 2015/6/2.
 */
public class SettingFragment extends Fragment {

    /***视图************************************************************/
    @ViewInject(R.id.title_txt)
    private TextView mTitleTv;
    /***控制************************************************************/
    private BaseFragmentActivity mActivity;
    /***数据************************************************************/

    public static void showFragment(Context context,Bundle args) {
        TerminalActivity.showFragment(context,SettingFragment.class,args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_setting,null);
        ViewUtils.inject(this,convertView);
        initView();
        return  convertView;
    }

    private void initView() {
        mTitleTv.setText(R.string.app_name);
    }

    @OnClick(R.id.title_back_imgbtn)
    public void backImgClick(View view) {
        mActivity.finish();
    }


}
