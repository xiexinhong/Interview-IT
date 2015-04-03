package org.xxh.interview.interviewee.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import org.xxh.interview.MainActivity;
import org.xxh.interview.R;
import org.xxh.interview.app.InterviewApplication;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.fragment.LabelFragment;
import org.xxh.interview.interviewee.fragment.PraiseFragment;
import org.xxh.interview.interviewee.fragment.UploadFragment;
import org.xxh.interview.utils.Methods.Methods;

import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class MenuAdapter extends BaseAdapter {

    /***************************************************/
    List<String> datas = new ArrayList<String>();
    /***************************************************/
    Activity mActivity;


    public MenuAdapter(){};

    public MenuAdapter(Activity context) {
        mActivity = context;
        initData();
    }

    private void initData() {
        datas.add(Methods.getResourceString(R.string.home_menu_label));
        datas.add(Methods.getResourceString(R.string.home_menu_upload_content));
        datas.add(Methods.getResourceString(R.string.home_menu_praise));
        datas.add(Methods.getResourceString(R.string.home_menu_store));
        datas.add(Methods.getResourceString(R.string.home_menu_setting));
        datas.add(Methods.getResourceString(R.string.home_menu_close));
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null) {
            convertView =  LayoutInflater.from(InterviewApplication.getAppContext()).inflate(R.layout.home_menu_item, null);
            Button button  = (Button) convertView.findViewById(R.id.home_menu_btn);
            viewHolder = new ViewHolder(button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mButton.setText(datas.get(position));
        setListener(viewHolder,position);
        return convertView;
    }

    class ViewHolder {
        Button mButton;
        public ViewHolder(Button button) {
            mButton = button;
        }
        public ViewHolder(){};
    }

    private void setListener(ViewHolder viewHolder,final int order) {

        viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (order) {
                    case 0:
                        TerminalActivity.showFragment(mActivity, LabelFragment.class,null);
                        break;
                    case 1:
                        TerminalActivity.showFragment(mActivity, UploadFragment.class,null);
                        break;
                    case 2:
                        TerminalActivity.showFragment(mActivity, PraiseFragment.class,null);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

}
