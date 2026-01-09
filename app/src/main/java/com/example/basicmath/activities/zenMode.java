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
import androidx.preference.PreferenceManager;

import com.example.basicmath.R;
import com.example.basicmath.environment.Settings;
import com.example.basicmath.models.Operation;
import com.example.basicmath.models.Problem;
import com.example.basicmath.models.Mode;

import java.util.ArrayList;

public class zenMode extends AppCompatActivity {

    private TextView chalange;
    private Button nextBTN;
    public String string;
    private Mode mode;
    private int count=0;
    int a, b, chooser;
    private Settings settings;
    private Problem problem = new Problem();
    private int startMultiplication;
    private int endMultiplication;
    private ArrayList<Operation> activeOperations;

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



        String sTT = PreferenceManager.getDefaultSharedPreferences(this).getString("table_start","1");
        String eTT = PreferenceManager.getDefaultSharedPreferences(this).getString("table_end","10");
        //getting default settings
        startMultiplication = Integer.parseInt(sTT);
        endMultiplication   = Integer.parseInt(eTT);
        activeOperations = new ArrayList<>();
        activeOperations = getActiveOperations();

        settings = new Settings(activeOperations, startMultiplication, endMultiplication);
    }


    private ArrayList<Operation> getActiveOperations() {
        ArrayList<Operation> activeModes = new ArrayList<>();
        Boolean b;

        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("mode_times_table",true)){
            activeModes.add(Operation.MULTIPLICATION);
        }
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("mode_division",false)){
            activeModes.add(Operation.DIVISION);
        }
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("mode_percentage",false)){
            activeModes.add(Operation.PERCENTAGE);
        }
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("mode_addition",false)){
            activeModes.add(Operation.ADDITION);
        }
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("mode_subtraction",false)){
            activeModes.add(Operation.SUBTRACTION);
        }

        return  activeModes;
    }


}