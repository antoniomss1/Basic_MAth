package com.example.basicmath.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basicmath.R;
import com.example.basicmath.environment.Settings;
import com.example.basicmath.environment.SettingsPreferences;

public class SettingsActivity extends AppCompatActivity {

    Switch switchHardMode;
    Switch switchDateMode;
    Switch switchPercentage;
    EditText preferedTableStart;
    EditText preferedTableEnd;

    SettingsPreferences settingsPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switchHardMode = findViewById(R.id.switchHardMode);
        switchDateMode = findViewById(R.id.switchDate);
        switchPercentage = findViewById(R.id.switchPercentageMode);

        preferedTableStart = findViewById(R.id.editTextNumberSTART);
        preferedTableEnd = findViewById(R.id.editTextNumberEND);

        settingsPreferences = new SettingsPreferences(this);

    }

    @Override
    public void finish() {
        applyPreferences();
        super.finish();
    }

    private void applyPreferences() {
        System.out.println("APLY PREFERENCES");
        Boolean h = switchHardMode.isChecked();//0
        Boolean d = switchDateMode.isChecked();//1
        Boolean p = switchPercentage.isChecked();//2

        int mode = 0;

        if(h){
            mode = 0;
        } else if (p) {
            mode = 1;
        }
        else{
            mode = 2;
        }

        System.out.println("3 primeiros: "+h.toString()+" "+d.toString()+" "+p.toString());
        int beg = Integer.parseInt(preferedTableStart.getText().toString());
        int end = Integer.parseInt(preferedTableEnd.getText().toString());
        System.out.println("beg: "+beg);
        System.out.println("end: "+end);
        Settings settings = new Settings(mode, beg, end);
        System.out.println("SETTINGS: "+settings.toString());

        settingsPreferences.saveSettings(settings);

    }
}