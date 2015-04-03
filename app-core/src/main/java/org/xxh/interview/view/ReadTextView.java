package org.xxh.interview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2015/4/2.
 */
public class ReadTextView extends TextView {

    private Context mContext;
    private float scale = 1;

    public ReadTextView(Context context) {
        super(context);
        mContext = context;
    }

    public ReadTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ReadTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            scale = scale + 0.1f;
//            ViewHelper.setScaleX(ReadTextView.this, scale);
//            ViewHelper.setScaleY(ReadTextView.this, scale);
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP){
//            scale = scale - 0.1f;
//            ViewHelper.setScaleX(ReadTextView.this, scale);
//            ViewHelper.setScaleY(ReadTextView.this, scale);
//        }
        return super.onTouchEvent(event);

    }


}
