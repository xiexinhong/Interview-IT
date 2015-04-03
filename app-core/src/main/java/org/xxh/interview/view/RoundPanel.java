package org.xxh.interview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import org.xxh.interview.R;

/**
 * Created by Administrator on 2015/4/1.
 */
public class RoundPanel extends FrameLayout {

    private Paint paint = new Paint();
    private int mDefaultColor = 0;
    private Context mContext;
    public RoundPanel(Context context) {
        super(context);
        mContext = context;
    }

    public RoundPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public RoundPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setDefaultColor(int color) {
        mDefaultColor = color;
    }

    public void setDefaultColorForInvalidate(int color) {
        mDefaultColor = color;
        invalidate();
    }

    public int getDefaultColor() {
        if(mDefaultColor == 0) {
            mDefaultColor = mContext.getResources().getColor(R.color.label_item_cancel);
        }
        return mDefaultColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.reset();
            paint.setColor(getDefaultColor());
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            RectF outerRectf = new RectF(0,0,getWidth(),getHeight());
            canvas.drawRoundRect(outerRectf, getHeight()/2, getHeight()/2,paint);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
}
