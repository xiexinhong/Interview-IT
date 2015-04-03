package org.xxh.interview.interviewee.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import org.xxh.interview.R;

/**
 * Created by Administrator on 2015/3/23.
 */
public class CategoryFragment extends Fragment {

    private Activity mActiivty;
    private ObjectAnimator animator;
    private AnimatorSet mSet;

    @ViewInject(R.id.category_top)
    private LinearLayout topLayout;

    @ViewInject(R.id.category_down)
    private LinearLayout downLayout;

    @ViewInject(R.id.category_listview)
    private ListView mListView;

    private GestureDetector mGestureDetector;

    /***data***************************/
    private String[] datas = new String[]{"aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc",
            "aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc",
            "aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc",
            "aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc",
            "aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc",
            "aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc",
            "aaaaaaaaaaaaa","bbbbbbbbbbbbbbb","ccccccccccc"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActiivty = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_category,container,false);
        ViewUtils.inject(this,contentView);
        initAninimation();
        mListView.setAdapter(new ArrayAdapter<String>(mActiivty,android.R.layout.simple_expandable_list_item_1,datas));
        return contentView;
    }

    public void initAninimation() {
//        PropertyValuesHolder tpropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleY",1f,0.5f);
//        PropertyValuesHolder dpropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleY",1f,1.2f);
//        animator = ObjectAnimator.ofPropertyValuesHolder(topLayout,tpropertyValuesHolder);
//
//        animator.setDuration(1000);

        mSet = new AnimatorSet();
        mSet.playTogether(
                ObjectAnimator.ofFloat(topLayout, "translationY", 0, -90),
//                ObjectAnimator.ofFloat(topLayout, "scaleY", 1, 0.5f),

                ObjectAnimator.ofFloat(downLayout, "translationY", 0, -90)
//                ObjectAnimator.ofFloat(downLayout, "scaleY", 1, 1.5f)
        );
        mSet.setDuration(1000);


        mGestureDetector= new GestureDetector(mActiivty,new MeGestureDetector());
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mSet.start();
            }
        });

    }

    class MeGestureDetector implements GestureDetector.OnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            animator.start();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(0,0,0,0);
            downLayout.setLayoutParams(params);
            return false;
        }
    }



}
