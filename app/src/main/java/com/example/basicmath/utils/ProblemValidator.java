package com.example.basicmath.utils;

import com.example.basicmath.models.Problem;

public class ProblemValidator {
    public static Boolean checkProblem(Problem problem, int answer){
        return (problem.getAnswer() == answer);
    }
}
