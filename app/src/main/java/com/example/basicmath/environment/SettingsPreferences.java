package com.example.basicmath.environment;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SettingsPreferences {
    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();
    private String PREFS_NAME = "settings";

    public SettingsPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSettings(Settings settings){
        System.out.println("VAI SALVAR");
        String json =gson.toJson(settings);
        System.out.println("SALVOU: "+json);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("settings", json);
        editor.apply();
    }

    public Settings getSettings(){
        String json =sharedPreferences.getString("settings", null);

        return gson.fromJson(json, Settings.class);
    }



}
