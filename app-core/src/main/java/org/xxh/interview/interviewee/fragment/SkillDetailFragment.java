package org.xxh.interview.interviewee.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.view.MeScrollView;


/**
 * Created by Administrator on 2015/4/2.
 */
public class SkillDetailFragment extends Fragment {


    /***视图*******************************/

    @ViewInject(R.id.skill_detail_vertical)
    private MeScrollView mScrollView;

    @ViewInject(R.id.skill_detail_back_imgbtn)
    private ImageView mBackImg;

    @ViewInject(R.id.skill_detail_content_txt)
    private TextView mReadTextView;

    @ViewInject(R.id.skill_detail_zoom_control)
    private ZoomControls mZoomControl;
    /***控制*******************************/
    private BaseFragmentActivity mActivity;
    private Handler mHandler;
    /***数据*******************************/
    private int mTextSize; //px
    private final int SIZE_ZOOM = 2;
    private long mOldTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity =(BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_skill_detail,null);
        ViewUtils.inject(this,convertView);
        initViewData();
        initListener();
        return convertView;
    }

    private void initViewData() {
        mHandler = new Handler();
        String temp ="分析一下上面的代码，当我们执行了Activity的finish方法，" +
                "被延迟的消息会在被处理之前存在于主线程消息队列中10分钟，而这个消息中 " +
                "又包含了Handler的引用，而Handler是一个匿名内部类的实例，" +
                "其持有外面的SampleActivity的引用，所以这导致了 SampleActivity无法回收，" +
                "进行导致SampleActivity持有的很多资源都无法回收，这就是我们常说的内存泄露。" +
                "注意上面的new Runnable这里也是匿名内部类实现的，同样也会持有SampleActivity的引用，" +
                "也会阻止SampleActivity被回收。要解决这种问题，思路就是不适用非静态内部类，继承Handler时，" +
                "要么是放在单独的类文件中，要么就是使用静态内部类。因为静态的内部类不会持有外部类的引用，" +
                "所以不会导致外部类实例的内存泄露。当你需要在静态内部类中调用外部的Activity时，" +
                "我们可以使用弱引用来处理。另外关于同样也需要将Runnable设置为静态的成员属性。" +
                "注意：一个静态的匿名内部类实例不会持有外部类的引用。 修改后不会导致内存泄露的代码如下。";
        temp = temp + temp + temp+ temp;
        mReadTextView.setText(temp);
        mTextSize = Float.valueOf(mReadTextView.getTextSize()).intValue();
    }

    private void initListener() {
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

        final ObjectAnimator disappearAnimator = ObjectAnimator.ofFloat(mZoomControl,"alpha",1f,0.3f);
        disappearAnimator.setDuration(500);

        final ObjectAnimator showAnimator = ObjectAnimator.ofFloat(mZoomControl, "alpha", mZoomControl.getAlpha(), 1f);
        showAnimator.setDuration(500);

        mZoomControl.setOnZoomInClickListener(new ZoomControls.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOldTime =  System.currentTimeMillis();
                if(mZoomControl.getAlpha() < 1f){
                    disappearAnimator.cancel();
                    showAnimator.start();
                }

                mTextSize = mTextSize + SIZE_ZOOM;
                mReadTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mOldTime+1500 < System.currentTimeMillis())
                            disappearAnimator.start();
                    }
                },1500);
            }
        });

        mZoomControl.setOnZoomOutClickListener(new ZoomControls.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOldTime =  System.currentTimeMillis();
                if(mZoomControl.getAlpha() < 1f){
                    disappearAnimator.cancel();
                    showAnimator.start();
                }
                mTextSize = mTextSize-SIZE_ZOOM;
                mReadTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mOldTime+1500 < System.currentTimeMillis())
                            disappearAnimator.start();
                    }
                },1500);
            }
        });
    }
}
