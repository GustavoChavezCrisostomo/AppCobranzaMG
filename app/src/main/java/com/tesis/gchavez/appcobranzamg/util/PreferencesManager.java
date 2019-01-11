package com.tesis.gchavez.appcobranzamg.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferencesManager {

    private static final String PREF_NAME =  PreferencesManager.class.getName();

    private static PreferencesManager sInstance;

    private SharedPreferences sharedPreferences;

    private PreferencesManager(Context context){
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Preferences attributes
     */
    public static final String PREF_ID = "id";

    public static final String PREF_USERNAME = "username";

    public static final String PREF_FULLNAME = "fullname";

    public static final String PREF_ISLOGGED = "islogged";

    public static final String PREF_ROLE = "role";

    public static synchronized PreferencesManager getInstance(Context context){
        if(sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void set(String param, String value){
        sharedPreferences.edit().putString(param, value).commit();
        Log.d(PreferencesManager.class.getSimpleName(), "set: "+param+"-"+value);
    }

    public String get(String param){
        String email = sharedPreferences.getString(param, null);
        Log.d(PreferencesManager.class.getSimpleName(), "get: "+param+"-"+email);
        return email;
    }

    public void remove(String param) {
        sharedPreferences.edit().putString(param, null).commit();
        Log.d(PreferencesManager.class.getSimpleName(), "remove: "+param);
    }

    public void clear() {
        sharedPreferences.edit().clear().commit();
        Log.d(PreferencesManager.class.getSimpleName(), "clear");
    }

}
