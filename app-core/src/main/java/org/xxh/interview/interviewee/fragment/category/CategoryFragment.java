package org.xxh.interview.interviewee.fragment.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.adapter.CategoryAdapter;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceError;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.json.JsonArray;
import org.xxh.interview.utils.json.JsonObject;


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

    @Override
    public void onResume() {
        super.onResume();
        ServiceProvider.getAllCagetory(new INetResponse(){

            @Override
            public void response(JsonObject jsonObject) {
                if(ServiceError.isError(jsonObject)) {
                    JsonArray array = jsonObject.getJsonArray("data");
                    mAdapter.setData(mAdapter.parseData(array));
                }
            }
        });

    }

    public void initView() {
        mAdapter = new CategoryAdapter(mActiivty);
        mListView.setAdapter(mAdapter);
    }





}
