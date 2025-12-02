package com.example.basicmath.activities;


import static com.example.basicmath.utils.ProblemUtils.checkAnswer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
import com.example.basicmath.utils.Mode;
import com.example.basicmath.utils.ProblemGenerator;

public class typePracticeActivity extends AppCompatActivity {


    private TextView chalange, answerTEXT;
    public String string;

    private TextView TVcounterWrongs, TVcounterRight, TVavarageTime;

    private int count=0;

    int a, b, chooser, btnCounter=0;
    char operation;
    int rigthAnswers = 0, wrongAnsers = 0;
    long startTime, endTime;
    long timeSum=0;
    int denominator=0;
    private int ans;

    //trocar esses "mode" por algo mais geral
    private Boolean hardMode;
    private Boolean percentageMode;
    private Mode mode;
    private SettingsPreferences settingsPreferences;
    private Settings settings;
    Problem currentProblem = new Problem();

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

        settingsPreferences = new SettingsPreferences(this);
//        System.out.println("VAI PEGAR SETTINGS");
        settings = settingsPreferences.getSettings();
//        System.out.println("PEGOU SETTINGS: "+ settings.toString());
        applySettings(settings);

    }

    private void applySettings(Settings settings) {
        System.out.println("APLICANDO SEETINGS");
        System.out.println("settings: "+settings.toString());


        this.mode = settings.getMode();

        System.out.println("mode: "+mode);
        System.out.println("SETOU CHECKEDS");
    }

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
            RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(250);
            rotate.setInterpolator(new LinearInterpolator());

            TVcounterWrongs.startAnimation(rotate);
        }
    }

    public void endSection(View v){
//        System.out.println("qui0");
//        double  precision = (double) rigthAnswers /(wrongAnsers+rigthAnswers),
//                avg = (double) timeSum /denominator;
//        int quantProblemas = denominator;
//
//        System.out.println("intent:");
//
//        Intent intent = new Intent(typePracticeActivity.this, historyActivity.class);
//        intent.putExtra("precision", precision);
//        intent.putExtra("avg", avg);
//        intent.putExtra("quant", quantProblemas);
//        intent.setAction(Intent.ACTION_SEND);
//
//        System.out.println("action send");
//
//        SharedPreferences settings = getApplicationContext().getSharedPreferences("data", 0);
//        SharedPreferences.Editor editor = settings.edit();
//
//        editor.apply();
//
//        startActivity(intent);
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