package org.xxh.interview.interviewee.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.MenuAdapter;
import org.xxh.interview.interviewee.fragment.category.CategoryFragment;
import org.xxh.interview.interviewee.fragment.experience.ExperienceFragment;
import org.xxh.interview.interviewee.fragment.home.HomeFragmet;
import org.xxh.interview.interviewee.fragment.personnel.CompleteUserInfoFragment;
import org.xxh.interview.interviewee.fragment.personnel.LoginFragment;
import org.xxh.interview.interviewee.fragment.personnel.UserInfo;
import org.xxh.interview.interviewee.fragment.skill.SkillFragment;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.view.SlidingMenu;

/**
 * Created by Administrator on 2015/3/23.
 */
public class HomeActivity extends BaseFragmentActivity {


    /**视图****************************************/
    private TextView mNickNameTv;
    private FragmentTabHost mTabHost;
    private SlidingMenu mSlidingMenu;
    private ListView mMenuListView;
    private MenuAdapter mMenuAdapter;
    private TextView mLoginAndRegisterTv;
    /***控制**************************************/
    private BaseFragmentActivity mActivity;
    private long mBackOldTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_home);
        initView();
        setListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
        reLoadView();
    }



    private void initView() {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.home_menu);
        mMenuListView = (ListView) findViewById(R.id.home_menu_listview);
        mLoginAndRegisterTv = (TextView) findViewById(R.id.home_login_and_register_tv);
        mNickNameTv = (TextView) findViewById(R.id.home_username_tv);
        mMenuAdapter = new MenuAdapter(this);
        mMenuListView.setAdapter(mMenuAdapter);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getIndicatorView(R.string.home_home,R.drawable.home_tab_home_bg))
                ,HomeFragmet.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("skill").setIndicator(getIndicatorView(R.string.home_technology, R.drawable.home_tab_technology_bg))
                ,SkillFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("category").setIndicator(getIndicatorView(R.string.home_category, R.drawable.home_tab_category_bg))
                ,CategoryFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("experience").setIndicator(getIndicatorView(R.string.home_experience,R.drawable.home_tab_experience_bg))
                ,ExperienceFragment.class,null);
    }

    /**
     *
     */
    public void headImgTrigger(){
        mSlidingMenu.menuTrigger();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        long tmp = 0l;
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            tmp = System.currentTimeMillis();
            if(mBackOldTime + 1000 < tmp) {
                Methods.showToast(R.string.str_exit_app,false);
            } else {
                return super.onKeyUp(keyCode, event);
            }
            mBackOldTime = tmp;
        }
        return false;
    }

    public void setListener() {
        mLoginAndRegisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment.showLoginFragment(HomeActivity.this, null);
            }
        });
        mNickNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteUserInfoFragment.showFragment(mActivity,null);
            }
        });

    }

    private void reLoadView() {
        UserInfo info = UserInfo.getInstance();
        if(info.id > 0 ) {
            mLoginAndRegisterTv.setVisibility(View.GONE);
            mNickNameTv.setVisibility(View.VISIBLE);
            mNickNameTv.setText(info.nickName);
        } else {
            mLoginAndRegisterTv.setVisibility(View.VISIBLE);
            mNickNameTv.setVisibility(View.GONE);
        }
    }

    private View getIndicatorView(int txtId,int iconId) {
        View  view = getLayoutInflater().inflate(R.layout.home_tab_view, null);
        TabViewHolder holder = new TabViewHolder(view);
        holder.mIconIv.setImageResource(iconId);
        holder.mTxtTV.setText(txtId);
        return view;
    }

    class TabViewHolder{
        public ImageView mIconIv;
        public TextView mTxtTV;
        public TabViewHolder(View view) {
            mIconIv = (ImageView) view.findViewById(R.id.home_tab_icon_iv);
            mTxtTV = (TextView) view.findViewById(R.id.home_tab_txt_tv);
        }
    }
}
