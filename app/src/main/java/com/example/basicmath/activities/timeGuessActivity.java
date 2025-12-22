package com.example.basicmath.activities;

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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class timeGuessActivity extends AppCompatActivity {
    TextView problem;
    LocalDate currentProblem;
    TextView TVcorrect;
    TextView TVwrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time_guess);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        problem     = findViewById(R.id.textViewDateProblem);
        TVcorrect   = findViewById(R.id.textViewCorrectP);
        TVwrong     = findViewById(R.id.textViewWrongP);

    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void click(View view) {
        DayOfWeek day = DayOfWeek.valueOf(view.getTag().toString());
        updatePoints(checkAnswer(day));
    }


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private void updatePoints(boolean b) {
        int wright, wrong;
        wright = Integer.parseInt(this.TVcorrect.getText().toString());
        wrong = Integer.parseInt(this.TVwrong.getText().toString());
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(250);
        rotate.setInterpolator(new LinearInterpolator());
        if(b){
            wright++;
            TVcorrect.startAnimation(rotate);
            setNewProblem();
        }else{
            wrong++;
            TVwrong.startAnimation(rotate);
        }

        TVcorrect.setText(String.valueOf(wright));
        TVwrong.setText(String.valueOf(wrong));
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private void setNewProblem() {
        Random r = new Random();


        long period = r.nextInt(1, 10000);
        boolean future = r.nextBoolean();

        this.currentProblem = future
                ? LocalDate.now().plusDays(period)
                : LocalDate.now().minusDays(period);

        this.problem.setText(currentProblem.toString());
    }


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void enterClicked(View view) {
        String prob = problem.getText().toString();
        createProblem(prob);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private void createProblem(String problem) {
        if(!problem.isEmpty()){
            return;
        }
        Random r = new Random();
        if(r.nextInt()%2 == 0){
            currentProblem = LocalDate.now().plusDays(r.nextLong(9999));
        }else{
            currentProblem = LocalDate.now().minusWeeks(r.nextLong(9999));
        }
        this.problem.setText(currentProblem.toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkAnswer(DayOfWeek ans){
        System.out.println("ans: "+ans);
        System.out.println("problrem: " + currentProblem.toString());
        System.out.println("dayof week: " + currentProblem.getDayOfWeek());
        return Objects.equals(ans, currentProblem.getDayOfWeek());
    }

}