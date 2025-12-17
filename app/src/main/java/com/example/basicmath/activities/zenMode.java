package com.example.basicmath.activities;

import static com.example.basicmath.utils.ProblemGenerator.newChalange;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basicmath.R;
import com.example.basicmath.environment.Settings;
import com.example.basicmath.environment.SettingsPreferences;
import com.example.basicmath.models.Problem;
import com.example.basicmath.models.Mode;

public class zenMode extends AppCompatActivity {

    private TextView chalange;
    private Button nextBTN;
    public String string;
    private Mode mode;
    private int count=0;
    int a, b, chooser;
    private SettingsPreferences settingsPreferences;
    private Settings settings;
    Problem problem = new Problem();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zen_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chalange = findViewById(R.id.textView);
        nextBTN = findViewById(R.id.buttonNEXT);


        nextBTN.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
            @Override
            public void onClick(View v) {
                if(count % 2 == 0){
                    problem = newChalange(settings);
                    a = problem.getLeftTerm();
                    b = problem.getRightTerm();
                    string = a + " " + problem.getOldSystemOperation() + " " + b;
                }
                else{
                    string = a + " " + problem.getOldSystemOperation() + " " + b + " = " + problem.getAnswer();
                }
                chalange.setText(string);
                count++;
            }
        });

        settingsPreferences = new SettingsPreferences(this);
        settings = settingsPreferences.getSettings();
        applySettings(settings);
    }

    private void applySettings(Settings settings) {
        System.out.println("APLICANDO SEETINGS");

        if(settings == null){
            System.out.println("era null");
            Settings s = new Settings(Mode.TIMES_TABLE, 1, 10);
            settingsPreferences = new SettingsPreferences(this);
            settingsPreferences.saveSettings(s);
            this.settings = settingsPreferences.getSettings();
            settings = this.settings;
            System.out.println("setttings: "+settings.toString());
        }
        System.out.println("settings: "+settings.toString());

        this.mode = settings.getMode();

        System.out.println("mode: "+mode);
        System.out.println("SETOU CHECKEDS");
    }



}