package org.xxh.interview.interviewee.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.view.MeLinearLayout;

/**
 * Created by Administrator on 2015/4/3.
 */
public class UploadFragment extends Fragment {

    /***视图**************************************/
    @ViewInject(R.id.text)
    private TextView mTextView;

    @ViewInject(R.id.linerlayout)
    private MeLinearLayout mMeLinearLayout;

    @ViewInject(R.id.horizontal_v)
    private HorizontalScrollView mHorizontalScrollView;
    @ViewInject(R.id.vertical_v)
    private ScrollView mScrollView;
    /***控制**************************************/
    private BaseFragmentActivity mActivity;
    /***数据**************************************/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView =  inflater.inflate(R.layout.fragment_upload,null);
        ViewUtils.inject(this,convertView);
        mHorizontalScrollView.setFillViewport(true);
        mScrollView.setFillViewport(true);
        mMeLinearLayout.setViews(mHorizontalScrollView,mScrollView);
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
        temp = temp + temp + temp+ temp+ temp + temp+ temp;
        temp = "adfa";
        mTextView.setText(temp);
        return convertView;

    }
}
