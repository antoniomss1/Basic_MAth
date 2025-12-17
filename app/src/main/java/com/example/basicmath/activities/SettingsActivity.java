package com.example.basicmath.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basicmath.R;
import com.example.basicmath.environment.Settings;
import com.example.basicmath.environment.SettingsPreferences;
import com.example.basicmath.models.Mode;

public class SettingsActivity extends AppCompatActivity {

    EditText preferedTableStart;
    EditText preferedTableEnd;

    SettingsPreferences settingsPreferences;
    RadioGroup radioGroup;
    RadioButton rb;

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

//        switchHardMode = findViewById(R.id.switchHardMode);
//        switchDateMode = findViewById(R.id.switchDate);
//        switchPercentage = findViewById(R.id.switchPercentageMode);

        preferedTableStart = findViewById(R.id.editTextNumberSTART);
        preferedTableEnd = findViewById(R.id.editTextNumberEND);

        settingsPreferences = new SettingsPreferences(this);

        radioGroup = findViewById(R.id.radioGroup);
        rb = findViewById(radioGroup.getCheckedRadioButtonId());
    }

    @Override
    public void finish() {
        applyPreferences();
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        applySettings(settingsPreferences);
    }

    private void applySettings(SettingsPreferences settings) {

        System.out.println("vai aplicar as settings");
        Settings s = settings.getSettings();
        System.out.println("vai check");
        if(s != null){
            checkRBfromMode(radioGroup, s.getMode());
            System.out.println("vai aplicar intervalos");
            System.out.println("start: " + s.getTableStart() + " " + s.getTableEnd());
            applyIntervals(s.getTableStart(), s.getTableEnd());
            System.out.println("aplicou");

        }

    }

    private void applyIntervals(int tableStart, int tableEnd) {
        preferedTableStart.setText(String.valueOf(tableStart));
        preferedTableEnd.setText(String.valueOf(tableEnd));
    }

    private void checkRBfromMode(RadioGroup radioGroup, Mode mode) {

        int idToCheck = -1;

        switch (mode) {
            case TIMES_TABLE:
                idToCheck = R.id.rbTIMES_TABLE;
                break;

            case HARD_TABLE:
                idToCheck = R.id.rbHardMode;
                break;

            case PERCENTAGE:
                idToCheck = R.id.rbPERCENTAGE;
                break;

            case DIVISION:
                idToCheck = R.id.rbDIVISION;
                break;

            case DATE_MODE:
                idToCheck = R.id.switchDate;
                break;
        }

        if (idToCheck != -1) {
            radioGroup.check(idToCheck);
        }
    }


    private void applyPreferences() {

        Object tag = rb.getTag();
        Mode mode = this.getCheckedMode();

//        if (tag.equals("Table")) {
//            mode=Mode.TIMES_TABLE;
//        } else if (tag.equals("Hard")) {
//            mode=Mode.HARD_TABLE;
//        } else if (tag.equals("Percentage")) {
//            mode=Mode.PERCENTAGE;
//        } else if (tag.equals("Division")) {
//            mode=Mode.DIVISION;
//        } else
////            (tag.equals("Date"))
//        {
//            mode=Mode.DATE_MODE;
//        }

//        System.out.println("3 primeiros: "+h.toString()+" "+d.toString()+" "+p.toString());
        int beg = Integer.parseInt(preferedTableStart.getText().toString());
        int end = Integer.parseInt(preferedTableEnd.getText().toString());

        Settings settings = new Settings(mode, beg, end);

        System.out.println("SETTINGS: "+settings.toString());

        settingsPreferences.saveSettings(settings);


    }

    private Mode getCheckedMode(){

        int id = radioGroup.getCheckedRadioButtonId();

        if (id == R.id.rbTIMES_TABLE) return Mode.TIMES_TABLE;
        if (id == R.id.rbHardMode) return Mode.HARD_TABLE;
        if (id == R.id.rbPERCENTAGE) return Mode.PERCENTAGE;
        if (id == R.id.rbDIVISION) return Mode.DIVISION;
        if (id == R.id.switchDate) return Mode.DATE_MODE;

        return Mode.TIMES_TABLE; // fallback
    }
}
