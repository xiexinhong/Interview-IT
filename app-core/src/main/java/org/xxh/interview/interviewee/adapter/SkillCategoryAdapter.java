package org.xxh.interview.interviewee.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.fragment.SkillListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/1.
 */
public class SkillCategoryAdapter extends BaseAdapter {

    private BaseFragmentActivity mActivity;
    private List<SkillCategoryItem> datas = new ArrayList<SkillCategoryItem>();


    public SkillCategoryAdapter(BaseFragmentActivity activity) {
        mActivity = activity;
        initData();
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
        SkillViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new SkillViewHolder();
            LayoutInflater inflater =  (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.skill_category_item,null);
            viewHolder.mCategoryImg = (ImageView) convertView.findViewById(R.id.skill_category_item_img);
            viewHolder.mCategoryTxt  = (TextView) convertView.findViewById(R.id.skill_category_item_txt);
            viewHolder.mArrowImg = (ImageView) convertView.findViewById(R.id.skill_category_item_arrow);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SkillViewHolder) convertView.getTag();
        }
        setViewData(viewHolder,position);
        setViewListener(convertView,position);
        return convertView;

    }

    private void setViewListener(View view, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putCharSequence("title_txt", datas.get(position).content);
                TerminalActivity.showFragment(mActivity, SkillListFragment.class, args);
            }
        });
    }

    private void setViewData(SkillViewHolder holder,int position) {
        System.out.println("holder.mCategoryImg = "+holder.mCategoryImg);
        holder.mCategoryImg.setBackgroundResource(R.drawable.skill_category_list_01);
        holder.mCategoryTxt.setText(datas.get(position).content);
        holder.mArrowImg.setBackgroundResource(R.drawable.icon_right_arrow);
    }

    private void initData() {
        String[] contents =  mActivity.getResources().getStringArray(R.array.skill_category_list);
        SkillCategoryItem item = null;
        for(int i=0;i<contents.length;i++) {
            item = new SkillCategoryItem();
            item.content = contents[i];
            item.drawableId = R.drawable.skill_category_list_01;
            datas.add(item);
        }
    }

    class SkillCategoryItem {
        int id;
        int drawableId;
        String imgUrl;
        String content;

        public SkillCategoryItem() {}
    }

    class SkillViewHolder {
        ImageView mCategoryImg;
        TextView  mCategoryTxt;
        ImageView mArrowImg;
    }


}
