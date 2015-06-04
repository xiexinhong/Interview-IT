package org.xxh.interview.interviewee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.fragment.category.ProblemFragment;
import org.xxh.interview.utils.json.JsonArray;
import org.xxh.interview.utils.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/15.
 */
public class CategoryAdapter extends BaseAdapter {

    private BaseFragmentActivity mActivity;
    private List<CategoryItem> mData = new ArrayList<CategoryItem>();


    public CategoryAdapter(BaseFragmentActivity activity) {
        mActivity = activity;
    }

    public void setData(List<CategoryItem> data) {
        synchronized (mData) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryViewHolder holder = null;
        if(convertView == null) {
            LayoutInflater inflater =  (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_list_item,null);
            holder = new CategoryViewHolder();
            holder.mCategoryImg = (ImageView) convertView.findViewById(R.id.category_item_category_img);
            holder.mNameTxt = (TextView) convertView.findViewById(R.id.category_item_name_tx);
            convertView.setTag(holder);
        } else {
            holder = (CategoryViewHolder) convertView.getTag();
        }
        initViewData(holder,position);
        setListener(convertView);
        return convertView;
    }

    private void setListener(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProblemFragment.showFragment(mActivity,null);
            }
        });
    }

    private void initViewData(CategoryViewHolder holder,int position) {
        ImageLoader.getInstance().displayImage(mData.get(position).imgUrl,holder.mCategoryImg);
        holder.mNameTxt.setText(mData.get(position).CategoryName);
    }

    public class CategoryItem {
        int id;
        String imgUrl;
        String CategoryName;
    }

    class CategoryViewHolder {
        ImageView mCategoryImg;
        TextView mNameTxt;
    }

    public List<CategoryItem> parseData(JsonArray array){

        if(array != null) {
            List<CategoryItem> data = new ArrayList<CategoryItem>();
            JsonObject[] objs = new JsonObject[array.size()];
            array.copyInto(objs);
            CategoryItem item = null;
            for(JsonObject obj : objs ) {
                item =  new CategoryItem();
                item.id = (int)obj.getNum("id");
                item.CategoryName = obj.getString("name");
                item.imgUrl = obj.getString("iconUrl");
                data.add(item);
            }
            return data;
        }
        return null;
    }
}
