package org.xxh.interview.interviewee.fragment.skill;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.SkillListAdapter;

/**
 * Created by Administrator on 2015/4/2.
 */
public class SkillListFragment extends Fragment {
    /***视图************************/
    @ViewInject(R.id.skill_list_listview)
    private ListView mListView;

    @ViewInject(R.id.skill_list_back_imgbtn)
    private ImageView mBackImg;

    @ViewInject(R.id.skill_list_title_txt)
    private TextView mTitleTxt;
    /***控制************************/
    private SkillListAdapter mAdapter;
    private BaseFragmentActivity mActvity;
    /***数据************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActvity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_skill_list, null);
        ViewUtils.inject(this, convertView);
        initViewData(getArguments());
        initListenner();
        return convertView;
    }

    private void initViewData(Bundle args) {
        mAdapter = new SkillListAdapter(mActvity);
        mListView.setAdapter(mAdapter);
        mTitleTxt.setText(args.getCharSequence("title_txt"));
    }

    private void initListenner() {
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActvity.onBackPressed();
            }
        });
    }





}
