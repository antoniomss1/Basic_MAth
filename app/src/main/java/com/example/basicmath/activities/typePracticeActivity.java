package com.example.basicmath.activities;


import static android.view.View.GONE;
import static com.example.basicmath.models.Mode.TIMED;
import static com.example.basicmath.utils.ProblemUtils.checkAnswer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.basicmath.utils.ProblemGenerator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class typePracticeActivity extends AppCompatActivity {


    private TextView chalange, answerTEXT;
    public String string;

    private TextView TVcounterWrongs, TVcounterRight, TVavarageTime;
    private TextView TVTimeLimit;

    private int count=0;

    int a, b;
    char operation;
    int rigthAnswers = 0, wrongAnsers = 0;
    long startTime, endTime;
    long timeSum=0;
    int denominator=0;
    private int ans;

    private Mode mode;
    private Settings settings;
    Problem currentProblem = new Problem();
    private int lifes;
    int startMultiplication;
    int endMultiplication;
    ArrayList<Operation> activeOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_type_practice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chalange = findViewById(R.id.textView);
        answerTEXT = findViewById(R.id.textViewAnswer);
        TVavarageTime = findViewById(R.id.textViewAvarageTime);
        TVcounterWrongs = findViewById(R.id.textViewWrongAnswers);
        TVcounterRight = findViewById(R.id.textViewRightAnswers);

        //ViewModel
        String sTT = PreferenceManager.getDefaultSharedPreferences(this).getString("table_start","1");
        String eTT = PreferenceManager.getDefaultSharedPreferences(this).getString("table_end","10");

        //getting default settings
        startMultiplication = Integer.parseInt(sTT);
        endMultiplication   = Integer.parseInt(eTT);
        activeOperations = new ArrayList<>();
        activeOperations = getActiveOperations();

        settings = new Settings(activeOperations, startMultiplication, endMultiplication);
        this.mode = Mode.NORMAL;




        int timeSecs = getIntent().getIntExtra("time_seconds", 0);
        lifes = getIntent().getIntExtra("lives_number", 0);

        long timeMilliseconds = timeSecs * 1000L;
        if(timeMilliseconds != 0){
            this.mode = TIMED;
            findViewById(R.id.buttonReset).setVisibility(GONE);
            TVTimeLimit = TVavarageTime;
            new CountDownTimer(timeMilliseconds, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Used for formatting digit to be in 2 digits only
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    TVTimeLimit.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                }
                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    TVTimeLimit.setText("00:00:00");
                    Toast.makeText(typePracticeActivity.this, "Tempo acabou!!!", Toast.LENGTH_SHORT).show();
                    endSection(null);
                }
            }.start();
        }

        if(lifes != 0){
            this.mode = Mode.SURVIVAL;
            findViewById(R.id.buttonReset).setVisibility(GONE);
            String newText = "❤\uFE0F "+(lifes - wrongAnsers);
            TVcounterWrongs.setText(newText);
        }
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


//    private void applySettings(Settings settings) {
//        System.out.println("APLICANDO SEETINGS");
//
//        if(settings == null){
//            System.out.println("era null");
//
//            Settings s = new Settings(Mode.TIMES_TABLE, 1, 10);
//
//            settingsPreferences = new SettingsPreferences(this);
//            settingsPreferences.saveSettings(s);
//            this.settings = settingsPreferences.getSettings();
//            settings = this.settings;
//            System.out.println("settings: "+settings.toString());
//        }
//        System.out.println("settings: "+settings.toString());
//
//        this.mode = settings.getModes();
//
//        System.out.println("mode: "+mode);
//        System.out.println("SETOU CHECKEDS");
//
//    }
//    private void applySettings(Settings settings) {
//        if (settings == null) {
//            Settings s = new Settings(Mode.TIMES_TABLE, 1, 10);
//            settingsPreferences.saveSettings(s);
//            this.settings = settingsPreferences.getSettings();
//        } else {
//            this.settings = settings;
//        }
//    }


    public void reset(View v){
        timeSum=0;
        denominator=0;
        rigthAnswers = 0;
        wrongAnsers = 0;

        answerTEXT.setText("");
        chalange.setText("");
        TVavarageTime.setText("");
        TVcounterRight.setText("0");
        TVcounterWrongs.setText("0");
    }

    public void numberClicked(String number){

        String oldT, newT;
        System.out.println("antes e depois");
        oldT = answerTEXT.getText().toString();
        newT = oldT+number;
        answerTEXT.setText(newT);

    }
    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void click(View v){
        System.out.println("clicked");
        String text;
        text = v.getTag().toString();
        System.out.println("text = "+text);
        System.out.println("answer text: "+answerTEXT.getText());

        numberClicked(text);
        if(checkAnswer(currentProblem, answerTEXT)) {

            currentProblem = ProblemGenerator.newChalange(settings);
            a = currentProblem.getLeftTerm();
            b = currentProblem.getRightTerm();
            string = currentProblem.getString();
            operation = currentProblem.getOldSystemOperation();
            ans = currentProblem.getAnswer();
            chalange.setText(string);

            //reseta contagem do tempo ao acertar resposta
            endTime = System.currentTimeMillis();
            long took = endTime - startTime;
            timeSum += took;
            denominator++;
            double avarage = timeSum/denominator;
            System.out.println(avarage);
            avarage = avarage/1000;
            System.out.println(avarage);

            TVavarageTime.setText("Avg. = "+avarage);

            //reinicia bem no final do anterior
            startTime = endTime;
            answerTEXT.setText("");
            updatePointBoxes(true);
        }
        else{
            if(answerTEXT.getText().length() == 1 && chalange.getText().toString().equals("") == false){
                if(ans-9<=0){
                    updatePointBoxes(false);
                    answerTEXT.setText("");
                }

            }
            else if(chalange.getText().toString().equals("") == false && answerTEXT.getText().length() == Math.floor(Math.log10(ans)+1)){
                updatePointBoxes(false);
                answerTEXT.setText("");
            }
        }


    }

    public void updatePointBoxes(boolean b){
        if (b){
            rigthAnswers++;
            String newText = ""+rigthAnswers;
            TVcounterRight.setText(newText);
            RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(250);
            rotate.setInterpolator(new LinearInterpolator());

            TVcounterRight.startAnimation(rotate);
        }
        else{
            wrongAnsers++;
            String newText = ""+wrongAnsers;
            TVcounterWrongs.setText(newText);

            if(this.lifes != 0){
                newText = "❤\uFE0F "+(lifes - wrongAnsers);
                TVcounterWrongs.setText(newText);

                if(this.lifes - wrongAnsers <=0){
                    Toast.makeText(this, "Oh No! You lost All your lives! ", Toast.LENGTH_SHORT).show();
                    endSection(null);
                }
                //animation
                Animation object = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinks);
                TVcounterWrongs.startAnimation(object);

            }else{
                //default animation
                RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(250);
                rotate.setInterpolator(new LinearInterpolator());
                TVcounterWrongs.startAnimation(rotate);
            }
        }
    }

    public void endSection(View v){
        double  precision = (double) rigthAnswers /(wrongAnsers+rigthAnswers),
                avg = (double) timeSum /denominator;

        int quantProblemas = denominator;

        avg = Math.round(avg);
        avg = avg/1000;

        System.out.println("intent:");

        Intent intent = new Intent(typePracticeActivity.this, historyActivity.class);

        intent.putExtra("precision", precision);
        intent.putExtra("avg", avg);
        intent.putExtra("quant", quantProblemas);
        intent.putExtra("wrongs", this.wrongAnsers);
        intent.putExtra("gameMode", this.mode);
        intent.setAction(Intent.ACTION_SEND);

        System.out.println("action send");

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }


    public void clearText(View view) {
        answerTEXT.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void enterClicked(View view) {
        System.out.println("ENTER CLICKED");
        if(chalange.getText().equals("")==false) return;

        System.out.println("VAI CRIAR NOVO PROBLEMA");
        currentProblem = ProblemGenerator.newChalange(settings);
        System.out.println("CRIOU: "+ currentProblem.toString());
        a = currentProblem.getLeftTerm();
        b = currentProblem.getRightTerm();
        string = currentProblem.getString();
        operation = currentProblem.getOldSystemOperation();
        ans = currentProblem.getAnswer();
        chalange.setText(string);

        //começa a contagem do tempo
        startTime = System.currentTimeMillis();

        if(answerTEXT.getText().isEmpty()){
            return;
        }
        if(answerTEXT.getText().length()==1){
            return;
        }

        answerTEXT.setText("");
        count++;

    }
}