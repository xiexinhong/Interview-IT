package org.xxh.interview.interviewee.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ListView;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.MenuAdapter;
import org.xxh.interview.interviewee.fragment.CategoryFragment;
import org.xxh.interview.interviewee.fragment.ExperienceFragment;
import org.xxh.interview.interviewee.fragment.HomeFragmet;
import org.xxh.interview.interviewee.fragment.SkillFragment;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.view.SlidingMenu;

/**
 * Created by Administrator on 2015/3/23.
 */
public class HomeActivity extends BaseFragmentActivity {


    /**视图****************************************/
    private FragmentTabHost mTabHost;
    private SlidingMenu mSlidingMenu;
    private ListView mMenuListView;
    private MenuAdapter mMenuAdapter;
    /***控制**************************************/
    private long mBackOldTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.home_menu);
        mMenuListView = (ListView) findViewById(R.id.home_menu_listview);
        mMenuAdapter = new MenuAdapter(this);
        mMenuListView.setAdapter(mMenuAdapter);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator("主页")
                ,HomeFragmet.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("skill").setIndicator("技巧")
                ,SkillFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("category").setIndicator("分类")
                ,CategoryFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("experience").setIndicator("经历")
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
}
