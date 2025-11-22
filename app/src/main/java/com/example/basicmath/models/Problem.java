package com.example.basicmath.models;

public class Problem {
    private int leftTerm;
    private int rightTerm;
    private Operation operation;


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    private int answer;

    public int getAnswer() {
        return answer;
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


}
