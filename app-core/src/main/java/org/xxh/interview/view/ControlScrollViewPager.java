package org.xxh.interview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/3/28.
 */
public class ControlScrollViewPager extends ViewPager {

    private boolean mCanScroll = false;

    public ControlScrollViewPager(Context context) {
        super(context);
    }

    public ControlScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        mCanScroll = isCanScroll;
    }

    public boolean getCanScroll() {
        return mCanScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x,y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mCanScroll)
            return super.onTouchEvent(ev);
        return false;
    }
}
