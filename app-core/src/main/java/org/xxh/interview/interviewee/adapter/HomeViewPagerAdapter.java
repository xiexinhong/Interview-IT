package org.xxh.interview.interviewee.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import org.xxh.interview.interviewee.fragment.HomeFragmet;

/**
 * Created by Administrator on 2015/3/26.
 */
public class HomeViewPagerAdapter extends PagerAdapter {

    private HomeFragmet mFragment;
    public HomeViewPagerAdapter(HomeFragmet fragment) {
        this.mFragment = fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if(position == 0) {
            view = mFragment.getmProblemFrame();
        } else {
            view = mFragment.getmExperienceFrame();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }


}
