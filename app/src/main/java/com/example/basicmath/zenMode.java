package com.example.basicmath;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
public class zenMode extends AppCompatActivity {

    private TextView chalange;
    public String string;
    private Button nextBTN;
    private Switch switchHM;
    private int count=0;
    int a, b, chooser;


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
        switchHM = findViewById(R.id.switch3);


        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0) {
                    if(switchHM.isChecked()){
                        newChalange();
                    }
                    else{
                        easyNewChalange();
                    }
                } else {
                    showAnswer();
                }
                count++;
            }
        });

    }

    public void newChalange(){

        Random random = new Random();

        chooser = random.nextInt(3);
        a = random.nextInt(19);
        b = random.nextInt(19);
        a++; b++;
        switch (chooser){
            case 0:
                a = (a%9)+1;
                b = (b%9) +1;
                string = a+" x "+b +" =";
                chalange.setText(string);
                break;
            case 1:
                string = a+" + "+b +" =";
                chalange.setText(string);
                break;
            case 2:
                if(a>b){
                    string = a+" - "+b +" =";
                    chalange.setText(string);
                }
                else{
                    string = b+" - "+ a+" =";
                    chalange.setText(string);
                }
                break;
        }


    }
    public void easyNewChalange(){

        Random random = new Random();
        chooser = 0;

        a = random.nextInt(9);
        b = random.nextInt(9);
        a++; b++;
        string = a+" x "+b +" =";
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

}