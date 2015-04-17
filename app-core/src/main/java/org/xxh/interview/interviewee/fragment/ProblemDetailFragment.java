package org.xxh.interview.interviewee.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xxh.interview.R;
import org.xxh.interview.interviewee.activity.LevelTwoActivity;

/**
 * Created by Administrator on 2015/4/17.
 */
public class ProblemDetailFragment extends Fragment {

    /***视图***********************************************/
    /***控制***********************************************/
    /***数据***********************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void showFragment(Context context,Bundle args) {
        LevelTwoActivity.showFragment(context,ProblemDetailFragment.class,args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_detail,null);
        return view;
    }
}
