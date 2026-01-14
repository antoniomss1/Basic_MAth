package com.example.basicmath.prefs;

import android.text.InputType;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;

public class PrefsUtils {
    
    public static void setEditTextInputTypeNumber(EditTextPreference editTextPreference, Preference.OnPreferenceChangeListener listener){
        if (editTextPreference != null) {
            editTextPreference.setOnBindEditTextListener(editText -> {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            });

            editTextPreference.setOnPreferenceChangeListener(listener);
        }
    }
    
}
