package org.xxh.interview.interviewee.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import org.xxh.interview.R;
import org.xxh.interview.app.InterviewApplication;
import org.xxh.interview.interviewee.activity.base.BaseFragmentActivity;

/**
 * 公用的Activity，用来承载fragment
 *
 */
public class TerminalActivity extends BaseFragmentActivity{

    private static final String TAG = "TerminalActivity";
    public static final String ARG_BOOL_BACKTOMAIN = "arg_bool_backtomain"; // 需要返回MainActivity

    // 内部参数
    public static final String INNER_ARG_FRAGMENT_CLASS_NAME = "arg_fragment_class_name";
    public static final String INNER_ARG_FRAHMENT_ARGS = "arg_fragment_args";

    /**
     * 新建WrapIntent
     * @param context
     * @param cls
     * @param args
     * @return
     */
    public static WrapIntent newWrapIntent(final Context context, final Class<? extends Fragment> cls, final Bundle args) {
        return new WrapIntent(context, cls, args, TerminalActivity.class);
    }

    /**
     * 打开fragment
     * @param context
     * @param cls
     * @param args
     */
    public static void showFragment(final Context context,
                                    final Class<? extends Fragment> cls, final Bundle args) {
        newWrapIntent(context, cls, args).show();
    }

    /**
     * 打开fragment
     * @param context
     * @param cls
     * @param args
     * @param requestCode
     */
    public static void showFragmentForResult(final Context context,
                                             final Class<? extends Fragment> cls, final Bundle args,
                                             final int requestCode) {
        newWrapIntent(context, cls, args).showForResult(requestCode);
    }

    /**
     * 打开fragment（通过fragment打开, 为的是从fragment.onActivityForResult()接收回调）
     * @param fragment
     * @param cls
     * @param args
     * @param requestCode
     */
    public static void showFragmentForResult(final Fragment fragment,
                                             final Class<? extends Fragment> cls, final Bundle args,
                                             final int requestCode) {
        newWrapIntent(fragment.getActivity(), cls, args).showForResultFromFragment(requestCode, fragment);
    }

    /**
     * Intent属性封装, 使用该类, 可增大TerminalIAcitvity的扩展性
     */
    public static class WrapIntent {
        private Context context;
        private Intent intent = null;

        public WrapIntent(Context context, final Class<? extends Fragment> cls,
                          Bundle args) {
            this(context, cls, args, TerminalActivity.class);
        }

        public WrapIntent(Context context, final Class<? extends Fragment> cls,
                          Bundle args, final Class<?> activityclss) {
            this(context, cls.getName(), args, activityclss);
        }

        public WrapIntent(Context context, final String className, Bundle args,
                          final Class<?> activityclss) {
            if (context == null) {
                context = InterviewApplication.getAppContext();
            }
            this.context = context;
            intent = new Intent(context, activityclss);
            intent.putExtra(INNER_ARG_FRAGMENT_CLASS_NAME, className);
            intent.putExtra(INNER_ARG_FRAHMENT_ARGS, args);
        }

        public Intent getIntent() {
            return intent;
        }

        //
        public void show() {
            if (!(context instanceof Activity))
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        //
        public void showForResult(int requestCode) {
            if (!(context instanceof Activity))
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((Activity) context).startActivityForResult(intent, requestCode);
        }

        //
        public void showForResultFromFragment(int requestCode, Fragment fragment) {
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    // 是否需要在返回的时候检查是否返回MainActivity
    private boolean backToMainActivity = false;

    /**
     * 设置是否在finish时返回MainActivity
     */
    public void setBackToMainActivity(boolean backToMainActivity) {
        this.backToMainActivity = backToMainActivity;
    }

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        setContentView(R.layout.activity_wrapper);
        initActivityArgs(args);
        initFragment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ARG_BOOL_BACKTOMAIN, backToMainActivity);
    }

    @Override
    public void onBackPressed() {
        // 可能从notification直接跳转到当前页面，所以在退出的时候需要回到首页
//		if (backToMainActivity) {
//			// 使用之后马上复原, 防止再次调用
//			backToMainActivity = false;
//			int size = ActivityStack.getInstance().getSize();
//			if (size <= 1) {
//				// actvity栈中只有一个activity，说明首页没有进入，需要跳转到首页
//				HomeActivity.openHomeActivity(this);
//			}
//		}
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        // 可能从notification直接跳转到当前页面，所以在退出的时候需要回到首页
//		if (backToMainActivity) {
//			// 使用之后马上复原, 防止再次调用
//			backToMainActivity = false;
//			int size = ActivityStack.getInstance().getSize();
//			if (size <= 1) {
//				// actvity栈中只有一个activity，说明首页没有进入，需要跳转到首页
//                HomeActivity.openHomeActivity(this);
//			}
//		}
    }

    /**
     * 初始化Activity参数
     * @param args
     */
    private void initActivityArgs(Bundle args) {
        if (args != null) {
            backToMainActivity = args.getBoolean(ARG_BOOL_BACKTOMAIN, backToMainActivity);
        }
    }

    /**
     * 初始化fragment参数
     */
    private void initFragment() {
        Intent intent = getIntent();
        String fragmentClassName = intent.getStringExtra(INNER_ARG_FRAGMENT_CLASS_NAME);
        if (TextUtils.isEmpty(fragmentClassName)) {
            Log.e(TAG, "invalid fragment class name");
            finish();
            return;
        }
        final Fragment fragment;
        try {
            Class<? extends Fragment> fragmentCls = (Class<? extends Fragment>)getClassLoader().loadClass(fragmentClassName);
            fragment = fragmentCls.newInstance();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            finish();
            return;
        } catch (InstantiationException e) {
            e.printStackTrace();
            finish();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            finish();
            return;
        }
        Bundle fragmentArgs = intent.getBundleExtra(INNER_ARG_FRAHMENT_ARGS);
        if (fragmentArgs != null) {
            fragment.setArguments(fragmentArgs);
            initActivityArgs(fragmentArgs);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commitAllowingStateLoss();
    }
}
