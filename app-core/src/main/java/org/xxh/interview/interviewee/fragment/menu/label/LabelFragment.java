package org.xxh.interview.interviewee.fragment.menu.label;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;
import org.xxh.interview.interviewee.fragment.personnel.UserInfo;
import org.xxh.interview.interviewee.service.INetResponse;
import org.xxh.interview.interviewee.service.ServiceError;
import org.xxh.interview.interviewee.service.ServiceProvider;
import org.xxh.interview.utils.Methods.Methods;
import org.xxh.interview.utils.json.JsonArray;
import org.xxh.interview.utils.json.JsonObject;

import java.util.List;


/**
 * Created by Administrator on 2015/3/31.
 */
public class LabelFragment extends Fragment {
    /**视图********************************/
    @ViewInject(R.id.menu_label_gridview)
    private GridView mGridView;

    @ViewInject(R.id.title_back_imgbtn)
    private ImageView mBackImg;

    @ViewInject(R.id.title_save_img)
    private ImageView mSaveImg;

    @ViewInject(R.id.title_txt)
    private TextView mTitleTxt;
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

    @Override
    public void onResume() {
        super.onResume();
        ServiceProvider.getAllLabelByUserId(UserInfo.getInstance().id,new INetResponse(){
            @Override
            public void response(JsonObject jsonObject) {
                if(ServiceError.isError(jsonObject)) {
                    JsonArray jsonArray = jsonObject.getJsonArray("data");
                    mAdapter.setDatas(mAdapter.parserLabelItem(jsonArray));
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initView() {
        mTitleTxt.setText(R.string.home_menu_label);
        mAdapter = new LabelGridViewAdapter(mActivity);
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
                String array = createUploadArray(mAdapter.getData());
                ServiceProvider.updateUserChooseLabel(array,new INetResponse(){

                    @Override
                    public void response(JsonObject jsonObject) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Methods.showToast(R.string.label_save_success,false);
                                mActivity.onBackPressed();
                            }
                        });
                    }
                });
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

    private String createUploadArray(List<LabelGridViewAdapter.LabelItem> datas) {
        JsonObject jsonObject = new JsonObject();
        JsonArray array = new JsonArray();
        JsonObject obj = null;
        for(LabelGridViewAdapter.LabelItem item : datas) {
            if(item.isChoosed) {
                obj = new JsonObject();
                obj.put("userId",UserInfo.getInstance().id);
                obj.put("userLabelId",item.id);
                array.add(obj);
            }
        }
        jsonObject.put("data", array);
        return jsonObject.toJsonString();
    }

}
