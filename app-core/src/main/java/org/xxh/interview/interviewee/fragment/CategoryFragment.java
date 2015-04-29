package org.xxh.interview.interviewee.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.CategoryAdapter;


/**
 * Created by Administrator on 2015/3/23.
 */
public class CategoryFragment extends Fragment {


    /***视图***************************/
    @ViewInject(R.id.category_listview)
    private ListView mListView;
    /***控制***************************/
    private BaseFragmentActivity mActiivty;
    /***data***************************/
    private CategoryAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActiivty = (BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_category,container,false);
        ViewUtils.inject(this, contentView);
        initView();
        return contentView;
    }

    public void initView() {
        String[] data = new String[]{"Android","IOS","Java","C/C++","PHP","MySQL","Python","JavaScript","Ruby",
                "Android","IOS","Java","C/C++","PHP","MySQL","Python","JavaScript","Ruby"};
        mAdapter = new CategoryAdapter(mActiivty);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(mAdapter.parseData(data));
    }





}
