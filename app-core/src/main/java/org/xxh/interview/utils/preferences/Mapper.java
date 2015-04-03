package org.xxh.interview.utils.preferences;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/3/20.
 */
interface Mapper<T> {
    public void setMapper(SharedPreferences.Editor editor);
    public T resultMapper(SharedPreferences prefs);
}