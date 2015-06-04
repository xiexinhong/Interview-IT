package org.xxh.interview.interviewee.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.fragment.skill.SkillListFragment;
import org.xxh.interview.utils.json.JsonArray;
import org.xxh.interview.utils.json.JsonObject;

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
    }

    public void setData(List<SkillCategoryItem> datas) {
        synchronized (this.datas) {
            this.datas.clear();
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
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
        ImageLoader.getInstance().displayImage(datas.get(position).imgUrl,holder.mCategoryImg);
        holder.mCategoryTxt.setText(datas.get(position).content);
        holder.mArrowImg.setBackgroundResource(R.drawable.icon_right_arrow);
    }



    class SkillCategoryItem {
        int id;
        String imgUrl;
        String content;
        public SkillCategoryItem() {}
    }

    public List<SkillCategoryItem> parseData(JsonArray array) {
        if(array != null) {
            List<SkillCategoryItem> items = new ArrayList<SkillCategoryItem>();
            JsonObject[] objs = new JsonObject[array.size()];
            SkillCategoryItem item = null;
            array.copyInto(objs);
            for(JsonObject obj : objs) {
                item = new SkillCategoryItem();
                item.id = (int)obj.getNum("id");
                item.content = obj.getString("name");
                item.imgUrl = obj.getString("iconUrl");
                items.add(item);
            }
            return items;
        }
        return null;
    }

    class SkillViewHolder {
        ImageView mCategoryImg;
        TextView  mCategoryTxt;
        ImageView mArrowImg;
    }


}
