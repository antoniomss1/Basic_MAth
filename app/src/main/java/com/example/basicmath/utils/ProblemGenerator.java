package com.example.basicmath.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.basicmath.environment.Settings;
import com.example.basicmath.models.Mode;
import com.example.basicmath.models.Operation;
import com.example.basicmath.models.Problem;

import java.util.Random;

public final class ProblemGenerator {
    private int difficulty;
    private static int left = 0;
    private static int right = 10;
    public static Problem generateProblem(Operation operation, Settings settings){
        //receber dados das configurações para determinar qual metodo chamar para gerar o problema
        left = settings.tableStart;
        right = settings.tableEnd;

        switch (operation) {
            case ADDITION:
                return generateAdditionProblem(left, right);

            case MULTIPLICATION:
                return generateMultiplicationProblem(left, right);

            case SUBTRACTION:
                return generateSubtractionProblem(left, right);

            case PERCENTAGE:
                return generatePercentageProblem(left, right);

            case DIVISION:
                return generateIntDivisionProblem(left, right);
            default:
//                throw new IllegalArgumentException("Unsupported operation: " + operation);
                System.out.println("OPERAÇÃO DESCONHECIDA (OU NÃO IMPLEMENTADA): "+operation.toString());
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private static Problem generateHardProblem(Settings settings){
        left = settings.tableStart;
        right = settings.tableEnd;
        Random c = new Random();
        int val = (c.nextInt(0, Integer.MAX_VALUE) % 4);
        switch (val){
            case (0):
                return generateAdditionProblem(left, right*2);
            case (1):
                return generateMultiplicationProblem(left, right);
            case (2):
                return generateSubtractionProblem(left, right);
            case (3):
                return generateIntDivisionProblem(left, right);
            default:
                System.out.println("val = " + val);
        }
        System.out.println("QUEEEEEEEEE???????");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static Problem newChalange(Settings settings){

        Mode mode = settings.getMode();
        Problem problem = new Problem();
        System.out.println("mode: "+mode);
        switch (mode){
            case HARD_TABLE:
                problem = generateHardProblem(settings);
                break;
            case PERCENTAGE:
                problem = generateProblem(Operation.PERCENTAGE, settings);
                break;
            case TIMES_TABLE:
                problem = generateProblem(Operation.MULTIPLICATION, settings);
                break;
            case DIVISION:
                problem = generateProblem(Operation.DIVISION, settings);
                break;
        }

        return problem;
    }
    //Sometimes problems might be preselected...
    private static Problem generateAdditionProblem(int left, int right ){

        Random random = new Random();
        int range = right - left;
        int a = random.nextInt(range);
        int b = random.nextInt(range);
        a++; b++;
        a += left;
        b += left;
        int ans = a+b;
        Problem problem = new Problem(a, b, Operation.ADDITION, ans);
        return problem;
    }
    private static Problem generateMultiplicationProblem(int left, int right){
        Random random = new Random();
        int range = right - left;
        int a = random.nextInt(range);
        int b = random.nextInt(range);
        a++; b++;
        a += left;
        b += left;
        int ans = a*b;
        Problem problem = new Problem(a, b, Operation.MULTIPLICATION, ans);
        System.out.println("problema gerado: "+problem.toString());
        return problem;
    }
    private static Problem generateSubtractionProblem(int left, int right ){
        Random random = new Random();
        int range = right - left;
        int a = random.nextInt(range);
        int b = random.nextInt(range);
        a++; b++;
        a += left;
        b += left;
        System.out.println("a b = " + a + " " + b);
        if (a<b){
            //troca valores
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        System.out.println("a b = " + a + " " + b);
        int ans = Math.abs(a-b);
        Problem problem = new Problem(a, b, Operation.SUBTRACTION, ans);
        return problem;
    }
    private static Problem generatePercentageProblem(int left, int right ){
        Random random = new Random();
        int range = 200;//o limite para os problemas de porcentagem podem ser bem diferentes
        //inserir configuração no SettingsActivity
        float a = random.nextInt(range);
        float b = random.nextInt(100);
        a++; b++;
        a += left;
        b += left;
        int ans = (int)(a*b/100);
        Problem problem = new Problem((int)a, (int)b, Operation.PERCENTAGE, ans);
        return problem;
    }

    private static Problem generateIntDivisionProblem(int left, int right){
        Random random = new Random();
        int range = right*10;
        int a = random.nextInt(range);
        int b = random.nextInt(19);
        a++; b++;
        a += left;
        b += left;
        if (a>b){
            //troca valores
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        int ans = (b/a);
        Problem problem = new Problem(b, a, Operation.DIVISION, ans);
        return problem;
    }

}
