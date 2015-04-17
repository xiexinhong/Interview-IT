package org.xxh.interview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
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
        System.out.println("w = "+getMeasuredWidth());
        System.out.println("h = "+getMeasuredHeight());
        if(scale==1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.2f);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.2f);
                AnimatorSet mSet = new AnimatorSet();
                mSet.playTogether(animatorX,animatorY);
                mSet.setDuration(500);
                mSet.start();
                ViewGroup.LayoutParams params= getLayoutParams();
                params.height =(int)(params.height * 1.2f);
                params.width =(int) (params.width * 1.2f);
                setLayoutParams(params);
                scale++;
                invalidate();
            }
        }

        System.out.println("currentw = "+getMeasuredWidth());
        System.out.println("currenth = "+getMeasuredHeight());

        return super.onTouchEvent(event);


    }


}
