package com.example.basicmath.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.preference.PreferenceManager;

import com.example.basicmath.environment.Settings;
import com.example.basicmath.models.Operation;
import com.example.basicmath.models.Problem;

import java.util.ArrayList;
import java.util.Random;

public final class ProblemGenerator {
    private int difficulty;
    private static int left = 0;
    private static int right = 10;
    public static Problem generateProblem(Operation operation, Settings settings, Context context){
        //receber dados das configurações para determinar qual metodo chamar para gerar o problema
        left = settings.multiplicationBegin;
        right = settings.multiplicationEnd;

        switch (operation) {
            case ADDITION:
                return generateAdditionProblem(context);

            case MULTIPLICATION:
                return generateMultiplicationProblem(context);

            case SUBTRACTION:
                return generateSubtractionProblem(context);

            case PERCENTAGE:
                return generatePercentageProblem(context);

            case DIVISION:
                return generateIntDivisionProblem(context);
            default:
//                throw new IllegalArgumentException("Unsupported operation: " + operation);
                System.out.println("OPERAÇÃO DESCONHECIDA (OU NÃO IMPLEMENTADA): "+operation.toString());
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private static Problem generateHardProblem(Context c){
        Random d = new Random();
        int val = (d.nextInt(0, Integer.MAX_VALUE) % 4);
        switch (val){
            case (0):
                return generateAdditionProblem(c);
            case (1):
                return generateMultiplicationProblem(c);
            case (2):
                return generateSubtractionProblem(c);
            case (3):
                return generateIntDivisionProblem(c);
            default:
                System.out.println("val = " + val);
        }
        System.out.println("QUEEEEEEEEE???????");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static Problem newChalange(Settings settings, Context context){

        ArrayList<Operation> operations = settings.getModes();

        int numModes = operations.size();
        Problem problem = new Problem();
        System.out.println("mode: "+operations);
        Random r = new Random();;
        int sort = r.nextInt(0, numModes);
        Operation mode = operations.get(sort);
        problem = generateProblem(mode, settings, context);


        return problem;
    }
    //Sometimes problems might be preselected...
    private static Problem generateAdditionProblem(Context context ){


        int left = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("addition_start", "1")
        );
        int right = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("addition_end", "10")
        );

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
    private static Problem generateMultiplicationProblem(Context context) {

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        int left = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("table_start", "1")
        );
        int right = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("table_end", "10")
        );

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
    private static Problem generateSubtractionProblem(Context context) {

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        int left = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("subtraction_start", "1")
        );
        int right = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("subtraction_end", "10")
        );

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
    private static Problem generatePercentageProblem(Context context) {

        int left = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("percentage_start", "1")
        );
        int right = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("percentage_end", "200")
        );

        Random random = new Random();
        int range = right-left;//o limite para os problemas de porcentagem podem ser bem diferentes
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

    private static Problem generateIntDivisionProblem(Context context) {

        int left = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("division_start", "1")
        );
        int right = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(context)
                        .getString("division_end", "10")
        );

        Random random = new Random();

        int a = random.nextInt(right);
        int b = random.nextInt(right);

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

    public static String convertToBaseTwelve(int val){
        Character dek   = 'X';
        Character el    = 'Ɛ';

        String number = "";
        int v = val;
        int last = v%12;
        number = number + last;

        while (v != 0){

            v = v/12;
            last = v%12;
            if(last ==10){
                number = number + dek;
            } else if (last==11) {
                number = number + el;
            }
            else{
                number = number + last;
            }

        }
        number = number + v;

        StringBuilder res = new StringBuilder();
        res.append(number);
        res.reverse();

        return res.toString();
    }

}
