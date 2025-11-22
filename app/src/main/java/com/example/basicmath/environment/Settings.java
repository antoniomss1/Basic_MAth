package com.example.basicmath.environment;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Settings {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private String PREFS_NAME = "settings";

    public Settings(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSettings(){

    }


}
