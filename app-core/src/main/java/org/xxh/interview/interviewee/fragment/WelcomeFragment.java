package org.xxh.interview.interviewee.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.HomeActivity;
import org.xxh.interview.interviewee.activity.TerminalActivity;

/**
 * Created by Administrator on 2015/3/20.
 */
public class WelcomeFragment extends Fragment {


    /************data************/
    private final static int IMG_NUM = 3;   //欢迎页图片张数
    private int[] mImgIds = new int[IMG_NUM];
    private ImageView[] mImgViews = new ImageView[IMG_NUM];
    private RadioButton[] mRadioBtns = new RadioButton[IMG_NUM];

    /************视图***********/
    private Activity mActivity;

    @ViewInject(R.id.welcome_viewpager)
    private ViewPager mViewPager;

    @ViewInject(R.id.welcome_radiogroup)
    private RadioGroup mRadioGroup;

    @ViewInject(R.id.welcome_btn)
    private Button mButton;
    /************事件***********/

    public static void showWelcomeFragment(Context context) {
        TerminalActivity.showFragment(context,WelcomeFragment.class,null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_welcome, container,false);
        ViewUtils.inject(this,contentView);
        initDataAndView();
        setListner();
        return contentView;
    }


    /**
     * 初始话视图和数据
     */
    private void initDataAndView() {
        mImgIds[0] = R.drawable.welcome;
        mImgIds[1] = R.drawable.welcome;
        mImgIds[2] = R.drawable.welcome;

        ImageView imageView = null;
        for(int i=0;i<IMG_NUM;i++) {
            imageView = new ImageView(mActivity);
            imageView.setBackgroundResource(mImgIds[i]);
            mImgViews[i] = imageView;
            mRadioBtns[i] = (RadioButton) mRadioGroup.getChildAt(i);
        }

        mViewPager.setAdapter(new WelcomePageAdapter(mImgViews));
        mViewPager.setOnPageChangeListener(new WelcomePageChangeListener());
        ((RadioButton)mRadioGroup.getChildAt(0)).setChecked(true); //设置第一个选中
        mButton.setVisibility(View.GONE);
    }

    private void setListner() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mViewPager.setCurrentItem(checkedId-1,true); //checkedId 是从1开始的  viewPager的Item是从0开始的
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, HomeActivity.class));
            }
        });

    }

    class WelcomePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if(position == IMG_NUM-1) {
                mButton.setVisibility(View.VISIBLE);
            } else {
                mButton.setVisibility(View.GONE);
            }
            ((RadioButton)mRadioGroup.getChildAt(position)).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    class WelcomePageAdapter extends PagerAdapter {

        private ImageView[] mImgViews = new ImageView[IMG_NUM];

        public WelcomePageAdapter(ImageView[] imgViews) {
            mImgViews = imgViews;
        }

        @Override
        public int getCount() {
            return mImgViews.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImgViews[position]);
            return mImgViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImgViews[position]);
        }
    }


}
