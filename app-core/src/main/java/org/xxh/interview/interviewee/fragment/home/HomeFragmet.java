package org.xxh.interview.interviewee.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.HomeActivity;
import org.xxh.interview.interviewee.adapter.HomeViewPagerAdapter;
import org.xxh.interview.view.*;

/**
 * Created by Administrator on 2015/3/23.
 */
public class HomeFragmet extends Fragment {


    private Activity mActivity;

    /***视图************************************/
    @ViewInject(R.id.homef_head_img)
    private RoundImageView mHeadImg;

    @ViewInject(R.id.homef_sliding_btn)
    private SlidingButton mSlidingButton;

    @ViewInject(R.id.homef_search_img)
    private ImageView mSerarchImg;

    @ViewInject(R.id.homef_view_pager)
    private ControlScrollViewPager mViewPager;


    private FrameLayout mProblemFrame;
    private FrameLayout mExperienceFrame;
    private MeListView mProblemListView;
    private MeListView mExperienceListView;

    private HomeViewPagerAdapter mPagerAdapter;
    /******************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_home,container,false);
        ViewUtils.inject(this,contentView);
        initView();
        setLister();
        return contentView;
    }

    /**
     * 初始化view
     */
    private void initView() {
        String[] datas = new String[]{"aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",};
        String[] datas1 = new String[]{"aaaaaa中aa","中bbbbbbbbb","cccc中ccccc","dddd中dddd",
                "aaaaaa中aa","中bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",
                "aaaaaaaa","bbbbbbbbb","ccccccccc","dddddddd",};
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        mProblemFrame = (FrameLayout) inflater.inflate(R.layout.homef_problem_frame,null);
        mExperienceFrame = (FrameLayout) inflater.inflate(R.layout.homef_experience_frame,null);
        mProblemListView = (MeListView) mProblemFrame.findViewById(R.id.homef_problem_listview);
        mExperienceListView = (MeListView) mExperienceFrame.findViewById(R.id.homef_experience_listview);
        mProblemListView.setAdapter(new ArrayAdapter<String>(mActivity,android.R.layout.simple_expandable_list_item_1,datas));
        mExperienceListView.setAdapter(new ArrayAdapter<String>(mActivity,android.R.layout.simple_expandable_list_item_1,datas1));
        mPagerAdapter = new HomeViewPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);

    }

    public FrameLayout getmProblemFrame(){
        return mProblemFrame;
    }

    public FrameLayout getmExperienceFrame() {
        return mExperienceFrame;
    }



    private void setLister() {
        //home页 头像点击事件
        mHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivity instanceof HomeActivity) {
                    ((HomeActivity)mActivity).headImgTrigger();
                }
            }
        });
        //viewPager切换控制
        mSlidingButton.SetOnChangedStateListener(new SlidingButton.OnChangedStateListener() {
            @Override
            public void onChange(int order) {
                mViewPager.setCurrentItem(order,false);
            }
        });
    }





}
