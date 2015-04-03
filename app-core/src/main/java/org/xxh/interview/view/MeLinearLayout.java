package org.xxh.interview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/4/3.
 */
public class MeLinearLayout extends LinearLayout {

    public HorizontalScrollView horizontalScrollView;
    public ScrollView scrollView;
    private float scale = 1;

    public MeLinearLayout(Context context) {
        super(context);
    }

    public MeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViews(HorizontalScrollView h,ScrollView v) {
        horizontalScrollView = h;
        scrollView = v;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("w = "+getWidth());
        System.out.println("h = "+getHeight());
           if(scale==1) {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.1f);
                   ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.1f);
                   AnimatorSet mSet = new AnimatorSet();
                   mSet.playTogether(animatorX, animatorY);
                   mSet.setDuration(500);
                   mSet.start();
                   ViewGroup.LayoutParams params= getLayoutParams();
                   params.height =(int)(params.height * 1.1f);
                   params.width =(int) (params.width * 1.1f);
                   setLayoutParams(params);
                   scale++;
                   scrollView.invalidate();
                   horizontalScrollView.invalidate();

                   invalidate();

               }
           }


               System.out.println("currentw = "+getWidth());
               System.out.println("currenth = "+getHeight());

        return super.onTouchEvent(event);
    }
}
