package org.xxh.interview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/3/28.
 */
public class MeListView extends ListView {


    public MeListView(Context context) {
        super(context);
    }

    public MeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  super.onInterceptTouchEvent(ev);
    }


}
