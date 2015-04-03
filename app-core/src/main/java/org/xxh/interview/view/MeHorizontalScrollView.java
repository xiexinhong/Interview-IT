package org.xxh.interview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2015/4/3.
 */
public class MeHorizontalScrollView extends HorizontalScrollView {
    public MeHorizontalScrollView(Context context) {
        super(context);
    }

    public MeHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
