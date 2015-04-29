package org.xxh.interview.interviewee.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import org.xxh.interview.interviewee.fragment.PraiseFragment;

/**
 * Created by Administrator on 2015/4/21.
 */
public class PraisePagerAdapter extends PagerAdapter {

    private PraiseFragment mFragment;

    public PraisePagerAdapter(PraiseFragment fragment) {
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
        if(position == 0 ) {
            view = mFragment.getProblemView();
        } else {
            view = mFragment.getExperienceView();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.instantiateItem(container,position);
        container.removeView((View)object);
    }
}
