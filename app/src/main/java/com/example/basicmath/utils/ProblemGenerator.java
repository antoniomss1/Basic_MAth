package com.example.basicmath.utils;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.basicmath.environment.Settings;
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

            default:
//                throw new IllegalArgumentException("Unsupported operation: " + operation);
                System.out.println("OPERAÇÃO DESCONHECIDA (OU NÃO IMPLEMENTADA): "+operation.toString());
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static Problem generateHardProblem(Settings settings){
        left = settings.tableStart;
        right = settings.tableEnd;
        Random c = new Random();
        int val;
        switch (val = (c.nextInt(1, 15) % 3)){
            case (0):
                return generateAdditionProblem(left, right*2);
            case (1):
                return generateMultiplicationProblem(left, right);
            case (2):
                return generateSubtractionProblem(left, right);
            default:
                System.out.println("val = " + val);
        }
        System.out.println("QUEEEEEEEEE???????");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static Problem newChalange(int mode, Settings settings){

        Problem problem = new Problem();
        System.out.println("mode: "+mode);
        switch (mode){
            case 0:
                problem = generateHardProblem(settings);
                System.out.println("hard problem generated: "+problem.toString());
                break;
            case 1:
                problem = ProblemGenerator.generateProblem(Operation.PERCENTAGE, settings);
                break;
            case 2:
                problem = ProblemGenerator.generateProblem(Operation.MULTIPLICATION, settings);
        }

        System.out.println("PROBLEMA GERADO: "+problem.toString());
        System.out.println("QUE? NULL!");
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
//        if(a>b){
//            string = a+" - "+b +" =";
//            ans = a-b;
//            chalange.setText(string);
//        }
//        else{
//            string = b+" - "+ a+" =";
//            ans = b-a;
//            chalange.setText(string);
//        }
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

    private static Problem getHardModeProblem(int left, int right){
        Random rand = new Random();
        int sort = rand.nextInt();
        if(sort % 2 == 0)
            return generateAdditionProblem(left, right);
        else
            return generateMultiplicationProblem(left, right);
    }


}
