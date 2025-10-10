package com.example.basicmath;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class typePractice extends AppCompatActivity {


    private TextView chalange, answerTEXT;
    public String string;

    private Button[] keyBoard = new Button[12];
    private Switch switchHM;
    private TextView TVcounterWrongs, TVcounterRight, TVavarageTime;

    private int count=0;

    int a, b, chooser, btnCounter=0;
    char operation;
    int rigthAnswers = 0, wrongAnsers = 0;
    long startTime, endTime;
    long timeSum=0;
    int denominator=0;
    private int ans;

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
        switchHM = findViewById(R.id.switch3);
        TVavarageTime = findViewById(R.id.textViewAvarageTime);

        keyBoard[0] = findViewById(R.id.button0);
        keyBoard[1] = findViewById(R.id.button1);
        keyBoard[2] = findViewById(R.id.button2);
        keyBoard[3] = findViewById(R.id.button3);
        keyBoard[4] = findViewById(R.id.button4);
        keyBoard[5] = findViewById(R.id.button5);
        keyBoard[6] = findViewById(R.id.button6);
        keyBoard[7] = findViewById(R.id.button7);
        keyBoard[8] = findViewById(R.id.button8);
        keyBoard[9] = findViewById(R.id.button9);
        keyBoard[10] = findViewById(R.id.buttonDEL);
        keyBoard[11] = findViewById(R.id.buttonCLEAR);

        TVcounterWrongs = findViewById(R.id.textViewWrongAnswers);
        TVcounterRight = findViewById(R.id.textViewRightAnswers);

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

    public void newChalange(int tabuada){

        Random random = new Random();

        chooser = random.nextInt(3);
        a = random.nextInt(19);
        b = random.nextInt(19);
        a++; b++;
        switch (chooser){
            case 0:
                a = (a%tabuada)+1;
                b = (b%tabuada) +1;
                string = a+" x "+b +" =";
                operation = 'X';
                ans = a*b;
                chalange.setText(string);
                break;
            case 1:
                string = a+" + "+b +" =";
                operation = '+';
                chalange.setText(string);
                ans = a+b;
                break;
            case 2:
                if(a>b){
                    string = a+" - "+b +" =";
                    ans = a-b;
                    chalange.setText(string);
                }
                else{
                    string = b+" - "+ a+" =";
                    ans = b-a;
                    chalange.setText(string);
                }
                operation = '-';
                break;
        }


    }
    public void easyNewChalange(int tabuada){

        Random random = new Random();
        chooser = 0;

        a = random.nextInt(tabuada);
        b = random.nextInt(tabuada);
        a++; b++;
        string = a+" x "+b +" =";
        ans = a*b;
        operation = 'X';
        chalange.setText(string);


    }
    public void showAnswer(){
        int asw;

        switch (chooser){
            case 0:
                asw = a*b;
                string = a+" x "+b +" = "+asw;
                chalange.setText(string);
                break;
            case 1:
                asw = a+b;
                string = a+" + "+b +" = "+asw;
                chalange.setText(string);
                break;
            case 2:
                if(a>b){
                    asw = a-b;
                    string = a+" - "+b +" = "+asw;
                    chalange.setText(string);
                }
                else{
                    asw = b-a;
                    string = b+" - "+ a+" = "+asw;
                    chalange.setText(string);
                }
                break;
        }

    }
    public boolean checkAnswer(){
        String text = answerTEXT.getText().toString();
        if(text.equals("")){
            return false;
        }
        switch (operation){
            case ('+'):
                if (Integer.parseInt(text) == a + b){
                    return true;
                }
                break;
            case ('-'):
                if (Integer.parseInt(text) == (a - b) || Integer.parseInt(text) == (b - a) ){
                    return true;
                }
                break;
            case('X'):
                if(Integer.parseInt(text) == a * b){
                    return true;
                }
                break;
        }
//        if(text.length() >1){
        return false;
//        }
//        return true;
    }
    public void numberClicked(String number){

//        btnCounter++;
        String oldT, newT;
        oldT = answerTEXT.getText().toString();
        newT = oldT+number;
        answerTEXT.setText(newT);

    }
    public void click(View v){
        System.out.println("clicked");
        String text;
        text = v.getTag().toString();
        System.out.println("text = "+text);
        if(answerTEXT.length()>=2){
            answerTEXT.setText("");
            return;
        }

        switch(text){
            case ("E"):
//                checkAnswer();
//                if (count % 2 == 0) {
                if(chalange.getText().equals("")==false){
                    return;
                }

                if(switchHM.isChecked()){
                    newChalange(9);
                }
                else{
                    easyNewChalange(9);
                }

                startTime = System.currentTimeMillis();


                if(answerTEXT.getText().equals("")){
                    return;
                }
                if(answerTEXT.getText().length()==1){
                    return;
                }

                if(checkAnswer()) {
                    showAnswer();
                    updatePointBoxes(true);
                }
                else{
                    showAnswer();
                    updatePointBoxes(false);
                }
                answerTEXT.setText("");
                count++;
                break;
            case ("C"):
                answerTEXT.setText("");
                break;
            default:
                numberClicked(text);
                if(checkAnswer()) {
                    showAnswer();
                    if(switchHM.isChecked()){
                        newChalange(9);
                    }
                    else{
                        easyNewChalange(9);
                    }
                    endTime = System.currentTimeMillis();
                    long took = endTime - startTime;
                    timeSum += took;
                    denominator++;
                    double avarage = timeSum/denominator;
                    System.out.println(avarage);
                    avarage = avarage/1000;
                    System.out.println(avarage);

                    TVavarageTime.setText("Avg. = "+avarage);

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
                    else if(chalange.getText().toString().equals("") == false){
                        updatePointBoxes(false);
                        answerTEXT.setText("");
                    }
                }
                break;
        }

//        showAnswer();
    }

    public void nextBTN(){
        if (count % 2 == 0) {
            if(switchHM.isChecked()){
                newChalange(9);
            }
            else{
                easyNewChalange(9);
            }
        } else {
            showAnswer();
        }
        count++;
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
        System.out.println("qui0");
        double precision = (double) rigthAnswers /(wrongAnsers+rigthAnswers),
                avg = (double) timeSum /denominator;
        int quantProblemas = denominator;

        System.out.println("intent:");

        Intent intent = new Intent(typePractice.this, history.class);
        intent.putExtra("precision", precision);
        intent.putExtra("avg", avg);
        intent.putExtra("quant", quantProblemas);
        intent.setAction(Intent.ACTION_SEND);

        System.out.println("action send");

        SharedPreferences settings = getApplicationContext().getSharedPreferences("data", 0);
        SharedPreferences.Editor editor = settings.edit();
//        editor.putInt("quantidade ", quantProblemas);

        // Apply the edits!
        editor.apply();

        startActivity(intent);
    }


}