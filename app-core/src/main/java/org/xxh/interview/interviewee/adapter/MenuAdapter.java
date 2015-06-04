package org.xxh.interview.interviewee.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.xxh.interview.R;
import org.xxh.interview.app.InterviewApplication;
import org.xxh.interview.interviewee.activity.TerminalActivity;
import org.xxh.interview.interviewee.fragment.ExperienceUploadFragment;
import org.xxh.interview.interviewee.fragment.menu.label.LabelFragment;
import org.xxh.interview.interviewee.fragment.PraiseFragment;
import org.xxh.interview.interviewee.fragment.ProblemUploadFragment;
import org.xxh.interview.interviewee.fragment.menu.setting.SettingFragment;
import org.xxh.interview.interviewee.fragment.personnel.UserInfo;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class MenuAdapter extends BaseAdapter {

    /***************************************************/
    List<MenuItem> datas = new ArrayList<MenuItem>();
    /***************************************************/
    Activity mActivity;


    public MenuAdapter(){};

    public MenuAdapter(Activity context) {
        mActivity = context;
        initData();
    }

    private void initData() {
        datas.add(new MenuItem(Methods.getResourceString(R.string.home_menu_label),R.drawable.menu_label));
        datas.add(new MenuItem(Methods.getResourceString(R.string.home_menu_upload_problem_content),R.drawable.menu_upload));
        datas.add(new MenuItem(Methods.getResourceString(R.string.home_menu_upload_experience_content),R.drawable.menu_upload));
        datas.add(new MenuItem(Methods.getResourceString(R.string.home_menu_praise),R.drawable.menu_praise));
        datas.add(new MenuItem(Methods.getResourceString(R.string.home_menu_store),R.drawable.menu_collect));
        datas.add(new MenuItem(Methods.getResourceString(R.string.home_menu_setting),R.drawable.menu_setting));
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
            TextView tv  = (TextView) convertView.findViewById(R.id.home_menu_tv);
            ImageView iv  = (ImageView) convertView.findViewById(R.id.home_menu_icon_iv);
            viewHolder = new ViewHolder(tv,iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mItemTv.setText(datas.get(position).itemName);
        viewHolder.mItemIv.setImageResource(datas.get(position).iconId);
        setListener(convertView,position);
        return convertView;
    }

    class ViewHolder {
        TextView mItemTv;
        ImageView mItemIv;
        public ViewHolder(TextView tv,ImageView iv) {
            mItemTv = tv;
            mItemIv = iv;
        }
        public ViewHolder(){};
    }

    private void setListener(View view,final int order) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!UserInfo.getInstance().status) {
                    Methods.showToast(R.string.home_menu_no_login,false);
                    return;
                }

                switch (order) {
                    case 0:
                        TerminalActivity.showFragment(mActivity, LabelFragment.class,null);
                        break;
                    case 1:
                        TerminalActivity.showFragment(mActivity, ProblemUploadFragment.class,null);
                        break;
                    case 2:
                        TerminalActivity.showFragment(mActivity, ExperienceUploadFragment.class,null);
                        break;
                    case 3:
                        TerminalActivity.showFragment(mActivity, PraiseFragment.class,null);
                        break;
                    case 4:
                        ServiceProvider.getUserInfo(1, new INetResponse() {
                            @Override
                            public void response(JsonObject jsonObject) {
                                System.out.println("json value = "+jsonObject);
                            }
                        });
                        break;
                    case 5:
                        SettingFragment.showFragment(mActivity,null);
                        break;
                    case 6:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        break;
                }
            }
        });
    }

    class MenuItem {
        String itemName;
        int iconId;
        public MenuItem(){}
        public MenuItem(String itemName,int iconId){
            this.itemName = itemName;
            this.iconId = iconId;
        }
    }

}
