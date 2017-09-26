package com.faviotorres.acopiomx.utils;

import android.content.SharedPreferences;
import android.util.Log;

public class PreferencesUtils {

    private static final String TOKEN = "token";
    private static final String TAG = "PREFERENCES";

    private static PreferencesUtils instance;

    public static PreferencesUtils getInstance() {
        if (instance == null){
            instance = new PreferencesUtils();
        }
        return instance;
    }

    /* ID / TOKEN */
    public void saveToken(SharedPreferences editor, String from, String token) {
        editor.edit().putString(TOKEN, token).apply();
        Log.d(TAG, from+" ---> saved token: "+token);
    }

    public String loadToken(SharedPreferences editor) {
        String token = editor.getString(TOKEN, null);
        Log.d(TAG, " ---> loaded token: "+token);
        return token;
    }
}
