package org.xxh.interview.interviewee.fragment.skill;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.SkillCategoryAdapter;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceError;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.json.JsonArray;
import org.xxh.interview.utils.json.JsonObject;

/**
 * Created by Administrator on 2015/3/23.
 */
public class SkillFragment extends Fragment {

    /***视图**********************************/
    @ViewInject(R.id.skill_listview)
    private ListView mListView;

    @ViewInject(R.id.skill_title_content_txt)
    private TextView mTitleTxt;
    /***控制**********************************/
    private BaseFragmentActivity mActivity;
    private SkillCategoryAdapter mAdapter;
    /*************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity =(BaseFragmentActivity)getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        ServiceProvider.getAllSkillCategory(new INetResponse(){

            @Override
            public void response(JsonObject jsonObject) {
                if(ServiceError.isError(jsonObject)) {
                    JsonArray array = jsonObject.getJsonArray("data");
                    mAdapter.setData(mAdapter.parseData(array));
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_skill,container,false);
        ViewUtils.inject(this,contentView);
        initViewData();
        return contentView;
    }

    private void initViewData() {
        mAdapter = new SkillCategoryAdapter(mActivity);
        mListView.setAdapter(mAdapter);
        mTitleTxt.setText(R.string.skill_title_bar_text);
    }
}
