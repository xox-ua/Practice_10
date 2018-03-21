package studio.coon.practice_10;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    public static final String STORAGE_NAME = "StorageName";
    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init( Context cntxt ){
        context = cntxt;
    }

    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void setData ( String name, String value ){
        if( settings == null ){
            init();
        }
        editor.putString( name, value );
        editor.apply();
    }

    public static String getData ( String name ){
        if( settings == null ){
            init();
        }
        return settings.getString( name, null );
    }
}
