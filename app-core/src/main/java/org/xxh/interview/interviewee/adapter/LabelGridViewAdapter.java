package org.xxh.interview.interviewee.adapter;

import android.animation.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.xxh.interview.R;
import org.xxh.interview.app.InterviewApplication;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.view.RoundPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/31.
 */
public class LabelGridViewAdapter extends BaseAdapter {

    private BaseFragmentActivity mActivity;
    List<LableItem> datas = new ArrayList<LableItem>();

    public LabelGridViewAdapter(BaseFragmentActivity activity) {
                mActivity = activity;
    }

    public void setDatas(String[] strs) {
        LableItem item = null;
        for(int i=0;strs != null && i<strs.length;i++) {
            item = new LableItem();
            item.content = strs[i];
            datas.add(item);
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LabelViewHolder viewHolder = null;
        if(convertView == null) {
            convertView =  LayoutInflater.from(InterviewApplication.getAppContext()).inflate(R.layout.label_gridview_item, null);
            viewHolder = new LabelViewHolder();
            viewHolder.mContentTxt = (TextView) convertView.findViewById(R.id.label_text);
            viewHolder.mShellPanel = (RoundPanel) convertView.findViewById(R.id.label_shell);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LabelViewHolder) convertView.getTag();
        }
        initData(viewHolder,position);
        initListener(viewHolder,position);
        return convertView;
    }


    /**
     * 初始化数据
     * @param viewHolder
     * @param position
     */
    private void initData(LabelViewHolder viewHolder,int position) {
        viewHolder.mContentTxt.setText(datas.get(position).content);
    }

    private void initListener(final LabelViewHolder viewHolder, final int position) {
        //选中动画
        Integer colorFrom = mActivity.getResources().getColor(R.color.label_item_cancel);
        Integer colorTo = mActivity.getResources().getColor(R.color.label_item_choosed);
        final ObjectAnimator animator = ObjectAnimator.ofFloat(viewHolder.mShellPanel, "rotationX", 0.0f, 180.0f);
        final ObjectAnimator animatorTxt = ObjectAnimator.ofFloat(viewHolder.mContentTxt,"rotationX",0.0f,360.0f);
        final ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),colorFrom,colorTo);
        //翻牌颜色渐变
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                viewHolder.mShellPanel.setDefaultColorForInvalidate((Integer) colorAnimator.getAnimatedValue());
            }
        });
        final  AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator,animatorTxt,colorAnimator);
        animatorSet.setDuration(250);

        //取消选中动画
        Integer colorFromCancel = mActivity.getResources().getColor(R.color.label_item_choosed);
        Integer colorToCancel = mActivity.getResources().getColor(R.color.label_item_cancel);
        final ObjectAnimator animatorCancel = ObjectAnimator.ofFloat(viewHolder.mShellPanel,"rotationX",180.0f,0.0f);
        final ObjectAnimator animatorTxtCancel = ObjectAnimator.ofFloat(viewHolder.mContentTxt,"rotationX",360.0f,0.0f);
        final ValueAnimator colorAnimatorCancel = ValueAnimator.ofObject(new ArgbEvaluator(),colorFromCancel,colorToCancel);
        //翻牌颜色渐变
        colorAnimatorCancel.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                viewHolder.mShellPanel.setDefaultColorForInvalidate((Integer) colorAnimatorCancel.getAnimatedValue());
            }
        });
        final  AnimatorSet animatorSetCancel = new AnimatorSet();
        animatorSetCancel.playTogether(animatorCancel,animatorTxtCancel,colorAnimatorCancel);
        animatorSetCancel.setDuration(250);
        //点击事件中实现翻牌
        viewHolder.mContentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datas.get(position).isChoosed) {
                    animatorSetCancel.start();
                    datas.get(position).isChoosed = false;
                } else {
                    animatorSet.start();
                    datas.get(position).isChoosed = true;
                }
            }
        });
    }

    class LableItem {
        int id;
        boolean isChoosed;
        String content;
    }

    class LabelViewHolder {
        RoundPanel mShellPanel;
        TextView mContentTxt;
    }
}
