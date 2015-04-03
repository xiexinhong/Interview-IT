package org.xxh.interview;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import org.xxh.interview.data.PrefsXmlStorage;
import org.xxh.interview.utils.preferences.MapperImpl;
import org.xxh.interview.utils.preferences.SharedPreferencesUtil;

/**
 * start to use github.
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean a = false;
        SharedPreferencesUtil.writePrefs(this, PrefsXmlStorage.userInfo, new MapperImpl() {
            public void setMapper(SharedPreferences.Editor editor) {
                editor.putString("aaa","aaa");
                editor.putBoolean("bbb",true);
            }
        });

        a =  SharedPreferencesUtil.readPrefs(this,PrefsXmlStorage.userInfo,new MapperImpl<Boolean>(){
            public Boolean resultMapper(SharedPreferences prefs) {
                return prefs.getBoolean("bbb",false);
            }
        });

        Log.i("xxh a = ",""+a);



    }
}
