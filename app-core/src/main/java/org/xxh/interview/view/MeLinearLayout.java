package org.xxh.interview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/4/3.
 */
public class MeLinearLayout extends FrameLayout {

    public HorizontalScrollView horizontalScrollView;
    public ScrollView scrollView;
    private float scale = 1;
    private int mOldWidth;
    private int mOldHeight;

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
        if(scale == 1) {
            mOldHeight = getMeasuredHeight();
            mOldWidth = getMeasuredWidth();
        }
        System.out.println("w = "+getMeasuredWidth());
        System.out.println("h = "+getMeasuredHeight());
           if(scale < 1.4) {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {

                   ObjectAnimator animatorX = ObjectAnimator.ofFloat(this,"scaleX",scale,scale+0.1f);
                   animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                       @Override
                       public void onAnimationUpdate(ValueAnimator animation) {

                                LayoutParams params = (LayoutParams) getLayoutParams();
                                params.width = ((Float) (mOldWidth * (Float) animation.getAnimatedValue())).intValue();
                                setLayoutParams(params);
                       }
                   });
                   ObjectAnimator animatorY = ObjectAnimator.ofFloat(this,"scaleY",scale,scale+0.1f);
                   animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                       @Override
                       public void onAnimationUpdate(ValueAnimator animation) {
                               System.out.println((Float) animation.getAnimatedValue());
                               LayoutParams params = (LayoutParams) getLayoutParams();
                               params.height = ((Float) (mOldHeight * (Float) animation.getAnimatedValue())).intValue();
                               setLayoutParams(params);
                       }
                   });
                   AnimatorSet mset = new AnimatorSet();
                   mset.playTogether(animatorX,animatorY);
                   mset.setDuration(500);
                   mset.start();
                   scale = scale + 0.1f;
                   invalidate();


               }
           }


               System.out.println("currentw = "+getMeasuredWidth());
               System.out.println("currenth = "+getMeasuredHeight());

        return super.onTouchEvent(event);
    }
}
