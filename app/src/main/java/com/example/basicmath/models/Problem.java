package com.example.basicmath.models;

import com.example.basicmath.utils.ProblemGenerator;

public class Problem {
    private int leftTerm;
    private int rightTerm;
    private Operation operation;

    private int answer;

    public Problem(int leftTerm, int rightTerm, Operation operation, int answer) {
        this.leftTerm = leftTerm;
        this.rightTerm = rightTerm;
        this.operation = operation;
        this.answer = answer;
    }

    public Problem() {}

    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getAnswer() {
        return answer;
    }
    public String getBase12Answer(){
        return ProblemGenerator.convertToBaseTwelve(answer);
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getLeftTerm() {
        return leftTerm;
    }

    public void setLeftTerm(int leftTerm) {
        this.leftTerm = leftTerm;
    }

    public int getRightTerm() {
        return rightTerm;
    }

    public void setRightTerm(int rightTerm) {
        this.rightTerm = rightTerm;
    }

    public String getString(){
        String string;
        switch (this.operation){
            case ADDITION:
                string = this.leftTerm+" + "+this.rightTerm +" =";
                break;
            case PERCENTAGE:
                string = this.leftTerm+"% de "+this.rightTerm +" =";
                break;
            case SUBTRACTION:
                string = this.leftTerm+" - "+this.rightTerm +" =";
                break;
            case MULTIPLICATION:
                string = this.leftTerm+" x "+this.rightTerm +" =";
                break;
            case DIVISION:
                string = this.leftTerm+" / "+this.rightTerm +" =";
                break;
            default:
                return "there is some error";
        }
        return string;
    }

    public String getBase12String(){
        String string;
        String left = ProblemGenerator.convertToBaseTwelve(this.leftTerm);
        String right = ProblemGenerator.convertToBaseTwelve(this.rightTerm);
        switch (this.operation){
            case ADDITION:
                string = left+" + "+right +" =";
                break;
            case PERCENTAGE:
                string = left+"% de "+right +" =";
                break;
            case SUBTRACTION:
                string = left+" - "+right +" =";
                break;
            case MULTIPLICATION:
                string = left+" x "+right +" =";
                break;
            case DIVISION:
                string = left+" / "+right +" =";
                break;
            default:
                return "there is some error";
        }
        return string;
    }

    public char getOldSystemOperation(){
        char string;
        switch (this.operation){
            case ADDITION:
                string = '+';
                break;
            case PERCENTAGE:
                string = '%';
                break;
            case SUBTRACTION:
                string = '-';
                break;
            case MULTIPLICATION:
                string = 'X';
                break;
            case DIVISION:
                string = '/';
                break;
            default:
                return 'e';
        }
        return string;

    }

    @Override
    public String toString() {
        return "Problem{" +
                "leftTerm=" + leftTerm +
                ", rightTerm=" + rightTerm +
                ", operation=" + operation +
                ", answer=" + answer +
                '}';
    }
}
