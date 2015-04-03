package org.xxh.interview.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Administrator on 2015/3/20.
 */
public class SharedPreferencesUtil {
    /**************************************************************/
    private static SharedPreferences pref = null;
    private static SharedPreferences.Editor  editor = null;
    /**************************************************************/
    /**
     * 初始化 一个pref
     * @param context
     * @param prefName
     */
    public static void initSharedPreferences(Context context,String prefName) {
        pref = context.getSharedPreferences(prefName,Context.MODE_APPEND);
        editor = pref.edit();
    }


    /**
     * 向 prefName 文件中写数据
     * @param context
     * @param prefName
     * @param mapper
     */
    public synchronized static void writePrefs(Context context,String prefName,Mapper mapper){
        initSharedPreferences(context,prefName);
        mapper.setMapper(editor);
        editor.commit();
    }

    /**
     *从 prefName 文件中读取数据
     * @param context
     * @param prefName
     * @param mapper
     * @param <T>
     * @return
     */
    public synchronized static  <T> T readPrefs(Context context,String prefName,Mapper<T> mapper) {
        initSharedPreferences(context,prefName);
        return mapper.resultMapper(pref);
    }

    /**
     * 清空某个文件数据
     * @param context
     * @param prefName
     */
    public static void clear(Context context,String prefName){
        initSharedPreferences(context,prefName);
        editor.clear();
        editor.commit();
    }

    /**
     * 谨慎使用 注意上下文状态
     */
    public static void clear() {
        if(editor != null) {
            editor.clear();
            editor.commit();
        }
    }

}
