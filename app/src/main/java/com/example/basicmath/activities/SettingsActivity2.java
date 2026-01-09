package com.example.basicmath.activities;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.basicmath.R;
import com.example.basicmath.models.Mode;

import java.util.ArrayList;

public class SettingsActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        private static final String KEY_TABLE_START = "table_start";

        private static final String KEY_TABLE_END   = "table_end";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            EditTextPreference startPref = findPreference(KEY_TABLE_START);
            EditTextPreference endPref   = findPreference(KEY_TABLE_END);

            if (startPref != null) {
                startPref.setOnBindEditTextListener(editText -> {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                });

                startPref.setOnPreferenceChangeListener((preference, newValue) ->
                        validateStart((String) newValue)
                );
            }

            if (endPref != null) {
                endPref.setOnBindEditTextListener(editText -> {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                });

                endPref.setOnPreferenceChangeListener((preference, newValue) ->
                        validateEnd((String) newValue)
                );
            }
        }


        // ===== VALIDAÇÕES =====
        private boolean validateStart(String newValue) {
            try {
                int start = Integer.parseInt(newValue);

                if (start <= 0) {
                    toast("Start must be greater than 0");
                    return false;
                }

                int end = getCurrentEnd();

                if (start >= end) {
                    toast("Start must be smaller than End");
                    return false;
                }

                return true;

            } catch (NumberFormatException e) {
                toast("Only numbers are allowed");
                return false;
            }
        }

        private boolean validateEnd(String newValue) {
            try {
                int end = Integer.parseInt(newValue);

                int start = getCurrentStart();

                if (end <= start) {
                    toast("End must be greater than Start");
                    return false;
                }

                return true;

            } catch (NumberFormatException e) {
                toast("Only numbers are allowed");
                return false;
            }
        }

        private int getCurrentStart() {
            String value = PreferenceManager
                    .getDefaultSharedPreferences(requireContext())
                    .getString(KEY_TABLE_START, "1");

            return Integer.parseInt(value);
        }

        private int getCurrentEnd() {
            String value = PreferenceManager
                    .getDefaultSharedPreferences(requireContext())
                    .getString(KEY_TABLE_END, "10");

            return Integer.parseInt(value);
        }

        private void toast(String msg) {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }



}
