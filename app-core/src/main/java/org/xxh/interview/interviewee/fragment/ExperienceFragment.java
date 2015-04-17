package org.xxh.interview.interviewee.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.TerminalActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class ExperienceFragment extends Fragment {

    /***视图*******************************************/
    @ViewInject(R.id.experience_pull_listview)
    private PullToRefreshListView mListVew;
    /***控制*******************************************/
    private ArrayAdapter<String> mAdapter;
    private Handler mHandler;
    /***数据*******************************************/
    private List<String> mData = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void showFragment(Context context,Bundle args) {
        TerminalActivity.showFragment(context, ExperienceFragment.class, args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience,null);
        ViewUtils.inject(this, view);
        initViewData();
        mHandler = new Handler();
        return view;
    }
    String[] dataOne = new String[]{"aaaaaaa","bbbbbbbb","ccccccc","dddddddd"};
    String[] dataTwo = new String[]{"中文aaaaaaa","中文bbbbbbbb"};
    private void initViewData() {
        parseData(dataOne);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,mData);
        mListVew.setAdapter(mAdapter);
        mListVew.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parseData(dataTwo);
                        mAdapter.notifyDataSetChanged();
                        mListVew.onRefreshComplete();
                    }
                },1000);
            }
        });

        mListVew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExperienceDetailFragment.showFragment(getActivity(), null);
            }
        });
    }


    private void parseData(String[] data) {
        for(String str : data) {
            mData.add(str);
        }
    }
}
