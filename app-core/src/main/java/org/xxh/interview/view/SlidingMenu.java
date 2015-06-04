package org.xxh.interview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.lidroid.xutils.view.annotation.event.OnTouch;
import com.nineoldandroids.view.ViewHelper;
import org.xxh.interview.R;
import org.xxh.interview.utils.ScreenUtils;

/**
 * Created by Administrator on 2015/1/21.
 */
public class SlidingMenu extends HorizontalScrollView {
    private Context mContext;
    //屏幕宽度
    private int mScreenWith;
    //手势方向判断
    private GestureDetector mGestureDetector = new GestureDetector(mContext,new JudjeGestureDirection());
    //dp
    private int mMenuRightPadding = 50;
    //菜单的宽度和高度
    private int mMenuWidth;
    private int mMenuHalfWidth;
    private boolean isOnce;
    private boolean isOpen;
    private ViewGroup mMenu;
    private ViewGroup mContent;

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScreenWith = ScreenUtils.getScreenWidth(context);
        Log.i("xxh","mScreenWith = "+mScreenWith);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        for(int i=0;typedArray != null && i< typedArray.getIndexCount();i++) {
            int attrIndex = typedArray.getIndex(i);
            switch (attrIndex) {
                case R.styleable.SlidingMenu_right_padding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(R.styleable.SlidingMenu_right_padding,0);
                    break;
            }

        }
        typedArray.recycle();
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    /**
     * 用来测量 自己和内容的来确定宽度和高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("xxh","onMeasure");
        //显示的设置一个高度
        if(!isOnce) {
            LinearLayout mLinearLayout = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mLinearLayout.getChildAt(0);
             mContent = (ViewGroup) mLinearLayout.getChildAt(1);
            //dp to px
            mMenuRightPadding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    mMenuRightPadding,
                    mContent.getResources().getDisplayMetrics());
            mMenuWidth = mScreenWith - mMenuRightPadding;
            mMenuHalfWidth = mMenuWidth / 2;
            mMenu.getLayoutParams().width = mMenuWidth;
            mContent.getLayoutParams().width = mScreenWith;
        }
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    /**
     * 用力啊为当前ViewGroup的子元素的位置河大小
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed) {
            //将菜单影藏
            this.scrollTo(mMenuWidth,0);
            isOnce = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev != null && ev.getAction() ==  MotionEvent.ACTION_UP) {
                int scroolX = getScrollX();

                if(isOpen){
                    if(scroolX > mMenuHalfWidth*2*0.2) {
                        this.smoothScrollTo(mMenuWidth, 0);
                        isOpen = false;
                    } else {
                        this.smoothScrollTo(0,0);
                    }
                } else {

                if(scroolX < mMenuHalfWidth*2*0.8) {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                } else {
                    this.smoothScrollTo(mMenuWidth, 0);
                }
            }
            setContentFocus();
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**定义菜单切换**********************************/
    private void openMenu() {
        if(isOpen)
            return;
        this.smoothScrollTo(0,0);
        isOpen = true;
    }
    private void closeMenu() {
        if(isOpen) {
            this.smoothScrollTo(mMenuWidth,0);
            isOpen = false;
        }

    }
    //菜单切换
    public void menuTrigger() {
        if(isOpen) {
            closeMenu();
        }else {
            openMenu();
        }
        setContentFocus();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);

        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    class JudjeGestureDirection extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(Math.abs(distanceY) < Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }

    private void setContentFocus() {
        if(isOpen) {
            mContent.setFocusable(false);
            mContent.setFocusableInTouchMode(false);
        } else {
            mContent.setFocusable(true);
            mContent.setFocusableInTouchMode(true);
        }
    }
}
