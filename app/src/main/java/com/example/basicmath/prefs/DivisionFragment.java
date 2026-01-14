package com.example.basicmath.prefs;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.basicmath.R;

public class DivisionFragment extends PreferenceFragmentCompat {

    private static final String KEY_START = "division_start";
    private static final String KEY_END = "division_end";
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.division, rootKey);


        EditTextPreference startPref = findPreference(KEY_START);
        EditTextPreference endPref   = findPreference(KEY_END);

        PrefsUtils.setEditTextInputTypeNumber(startPref,(preference, newValue) ->
                validateStart((String) newValue) );
        PrefsUtils.setEditTextInputTypeNumber(endPref, (preference, newValue) ->
                validateEnd((String) newValue));
    }


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
                .getString(KEY_START, "1");

        return Integer.parseInt(value);
    }

    private int getCurrentEnd() {
        String value = PreferenceManager
                .getDefaultSharedPreferences(requireContext())
                .getString(KEY_END, "10");

        return Integer.parseInt(value);
    }

    private void toast(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }
}