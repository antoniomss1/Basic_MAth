package com.example.basicmath.utils;

import android.widget.TextView;

import com.example.basicmath.models.Problem;

public class ProblemUtils {
    public static Boolean checkProblem(Problem problem, int answer){
        return (problem.getAnswer() == answer);
    }
    public static boolean checkAnswer(Problem currentProblem, TextView answerTEXT){
        String text = answerTEXT.getText().toString();
        System.out.println("CHECKING, TEXT = "+text);
        System.out.println("RESPOSTA: "+currentProblem.toString());
        if(text.isEmpty()) {
            return false;
        }
        if(Integer.parseInt(text) == currentProblem.getAnswer())
            return true;

        return false;
    }
}
