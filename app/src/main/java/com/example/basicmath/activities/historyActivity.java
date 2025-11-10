package com.example.basicmath.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basicmath.R;

public class historyActivity extends AppCompatActivity {

    LinearLayout scrollV;
    TextView tv;
    TextView TVmostP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


// Get from the SharedPreferences
//        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
//        int homeScore = settings.getInt("homeScore", 0);

        System.out.println("entrou");
        double avg =        getIntent().getDoubleExtra("avg", 0);
        avg = avg/1000;
        double precision=   getIntent().getDoubleExtra("precision", 0);
        int qnt =           getIntent().getIntExtra("quant", 0);

        String section = "Avg.: "+avg+"\t" + "precision: "+precision+"%"+"\t" + "N. problems: "+ qnt+"\n";
        System.out.println("recebeu dados ");

        tv =        findViewById(R.id.textViewBestAvarage);
        TVmostP =   findViewById(R.id.textViewMostProblems);

        System.out.println("achou textViewBestAvarage");


        double avgAtual = Double.parseDouble(tv.getText().toString());
        int qntAtual = Integer.parseInt(TVmostP.getText().toString());
        if(avg<avgAtual || avgAtual ==0){
            tv.setText(avg+"s");
        }
        if(qnt>qntAtual || qntAtual == 0){
            TVmostP.setText(qnt+"");
        }
        scrollV.findViewById(R.id.linear);
        TextView TV = new TextView(this);

//        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        textParams.topMargin = 16;
//        textParams.bottomMargin = 32;
//        adSection.setLayoutParams(textParams);
//
//        adSection.setGravity(Gravity.CENTER_HORIZONTAL);
//        adSection.setText(section);
//        adSection.setTextColor(Color.parseColor("#ff9800")); // Laranja
//        adSection.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16); // 16dp
//
//        sv.addView(adSection);
//        adSection.setText(section);
//        adSection.setId(5);
//        adSection.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));

//        sv.findViewById(R.id.linearLayout);
//        sv.addView(adSection);


        System.out.println("atribuiu valores");

    }

    public void readHistoryFile(){

    }
}