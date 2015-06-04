package org.xxh.interview.interviewee.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.LevelTwoActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.fragment.skill.SkillDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
public class SkillListAdapter extends BaseAdapter {

    private BaseFragmentActivity mActivity;
    private List<SkillListItem> datas = new ArrayList<SkillListItem>();
    public SkillListAdapter(BaseFragmentActivity activity) {
        this.mActivity = activity;
        initDatas();
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
        SkillListViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            convertView  = inflater.inflate(R.layout.skill_list_item,null);
            viewHolder = new SkillListViewHolder();
            viewHolder.mSkillListImg = (ImageView) convertView.findViewById(R.id.skill_list_item_img);
            viewHolder.mSkillTextView = (TextView) convertView.findViewById(R.id.skill_list_item_txt);
            viewHolder.mSkillListArrow = (ImageView) convertView.findViewById(R.id.skill_list_item_arrow);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SkillListViewHolder) convertView.getTag();
        }
        initViewDatas(viewHolder,position);
        initViewListener(convertView,position);
        return convertView;
    }

    private void initViewListener(View view,int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                LevelTwoActivity.showFragment(mActivity, SkillDetailFragment.class,args);
            }
        });
    }

    private void initViewDatas(SkillListViewHolder holder,int position) {
         holder.mSkillListImg.setImageResource(datas.get(position).listImg);
         holder.mSkillTextView.setText(datas.get(position).content);
         holder.mSkillListArrow.setImageResource(datas.get(position).arrawImg);
    }

    private void initDatas() {
        String[] strs = new String[]{"1aaaaa","2aaaaa","3aaaaa","4aaaaa","5aaaaa","6aaaaa","7aaaaa"};
        SkillListItem item = null;
        for(int i=0;i<strs.length;i++) {
            item = new SkillListItem();
            item.content = strs[i];
            item.listImg = R.drawable.skill_list_01;
            item.arrawImg = R.drawable.icon_right_arrow;
            datas.add(item);
        }
    }

    class SkillListViewHolder {
        ImageView mSkillListImg;
        TextView mSkillTextView;
        ImageView mSkillListArrow;
    }

    class SkillListItem {
        int id;
        int listImg;
        int arrawImg;
        String content;
    }
}
