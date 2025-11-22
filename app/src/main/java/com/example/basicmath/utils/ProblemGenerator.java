package com.example.basicmath.utils;

import com.example.basicmath.models.Operation;
import com.example.basicmath.models.Problem;

public final class ProblemGenerator {
    private int difficulty;
    public Problem generateProblem(Operation operation, int difficulty){
        //receber dados das configurações para determinar qual metodo chamar para gerar o problema
        Problem problem = new Problem();
        this.difficulty = difficulty;
        switch (operation) {
            case ADDITION:
//                return generateAdditionProblem();

            case MULTIPLICATION:
//                return generateMultiplicationProblem(difficulty);

            case SUBTRACTION:
//                return generateSubtractionProblem(difficulty);

            case PERCENTAGE:
//                return generatePercentageProblem(difficulty);

            default:
//                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
        return null;
    }

    //Sometimes problems might be preselected...
    private Problem generateAditionProblem(int left, int right, int difficulty){
        if(left == Integer.MAX_VALUE){

        }
        return null;
    }
    private Problem generateMultiplicationProblem(int left, int right, int difficulty){
        return null;
    }
    private Problem generateSubtractionProblem(int left, int right, int difficulty){
        return null;
    }
    private Problem generatePercentageProblem(int left, int right, int difficulty){
        return null;
    }

}
