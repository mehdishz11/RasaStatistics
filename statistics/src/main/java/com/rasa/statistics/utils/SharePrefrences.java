package com.rasa.statistics.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefrences {
    private static final String STATIST_KEY_PARENT="STATIST_KEY_PARENT";

    public static final String STATIST_KEY_INSTALL="STATIST_KEY_INSTALL";
    public static final String STATIST_KEY_ACTIVE="STATIST_KEY_ACTIVE";


    public static void setValue(Context context,String key,boolean value){
        SharedPreferences settings = context.getSharedPreferences(STATIST_KEY_PARENT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getValue(Context context,String key){
        SharedPreferences settings = context.getSharedPreferences(STATIST_KEY_PARENT, Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

}
