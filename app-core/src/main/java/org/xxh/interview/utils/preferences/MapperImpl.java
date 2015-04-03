package org.xxh.interview.utils.preferences;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/3/20.
 */
public class MapperImpl<T> implements Mapper<T> {
    @Override
    public void setMapper(SharedPreferences.Editor editor) {

    }

    @Override
    public T resultMapper(SharedPreferences prefs) {
        return null;
    }
}
