package org.xxh.interview.interviewee.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.LabelGridViewAdapter;


/**
 * Created by Administrator on 2015/3/31.
 */
public class LabelFragment extends Fragment {
    /**视图********************************/
    @ViewInject(R.id.menu_label_gridview)
    private GridView mGridView;

    @ViewInject(R.id.menu_label_back_imgbtn)
    private ImageView mBackImg;

    @ViewInject(R.id.menu_label_save_imgbtn)
    private ImageView mSaveImg;
    /**控制********************************/
    private BaseFragmentActivity mActivity;
    /**数据********************************/
    private LabelGridViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity =(BaseFragmentActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_label,null);
        ViewUtils.inject(this,contentView);
        initView();
        initListener();
        return contentView;
    }

    private void initView() {
        mAdapter = new LabelGridViewAdapter(mActivity);
        String[] datas = new String[]{"Java","Android","IOS","Spring",
        "SpringMVC","Mybatis","Dwr","C/C++",
        "PHP","Python","MySQL","SQL"};
        mAdapter.setDatas(datas);
        mGridView.setAdapter(mAdapter);

    }

    /**
     * 设置监听
     */
    private void initListener() {
        //保存监听
        mSaveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //返回监听
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
    }


}
