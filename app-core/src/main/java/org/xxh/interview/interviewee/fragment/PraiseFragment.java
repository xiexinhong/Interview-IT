package org.xxh.interview.interviewee.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.PraisePagerAdapter;
import org.xxh.interview.view.SlidingButton;

/**
 * Created by Administrator on 2015/4/3.
 */
public class PraiseFragment extends Fragment {

    /***视图*********************************/
    @ViewInject(R.id.praise_viewpager)
    private ViewPager mViewPager;

    @ViewInject(R.id.title_sliding_btn)
    private SlidingButton mSlidingBtn;

    @ViewInject(R.id.title_back_imgbtn)
    private ImageView mBackImg;

    private PullToRefreshListView mProblemLv;
    private PullToRefreshListView mExperienceLv;
    /***控制*********************************/
    private BaseFragmentActivity mActivity;
    private Handler mHandler;
    private PraisePagerAdapter mPagerAdapter;
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
        initData();
        initViews();
        setListener();
        return view;
    }

    private void initViews() {

        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mProblemLv = new PullToRefreshListView(mActivity);
        mProblemLv.setLayoutParams(params1);
        mExperienceLv = new PullToRefreshListView(mActivity);
        mExperienceLv.setLayoutParams(params2);


        String[] data1 = new String[]{"aaaaaaaa","ssssssssss","ddddddddddd","wwwwwwwww","zzzzzzzzzzz","yyyyyyyyyyy"};
        String[] data2 = new String[]{"2aaaaaaaa","2ssssssssss","2ddddddddddd","2wwwwwwwww","zzzzzzzzzzz","yyyyyyyyyyy"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mActivity,android.R.layout.simple_expandable_list_item_1,data1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mActivity,android.R.layout.simple_expandable_list_item_1,data2);
        mProblemLv.setAdapter(adapter1);
        mExperienceLv.setAdapter(adapter2);
    }

    private void initData() {
        mPagerAdapter = new PraisePagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);

    }

    private void setListener() {

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });

        mSlidingBtn.SetOnChangedStateListener(new SlidingButton.OnChangedStateListener() {
            @Override
            public void onChange(int order) {
                mViewPager.setCurrentItem(order,true);
            }
        });
    }


    public View getProblemView() {
        return mProblemLv;
    }

    public View getExperienceView() {
        return mExperienceLv;
    }
}

