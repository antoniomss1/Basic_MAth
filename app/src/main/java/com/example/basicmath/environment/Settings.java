package com.example.basicmath.environment;

import com.example.basicmath.models.Mode;
import com.example.basicmath.models.Operation;

import java.util.ArrayList;

public class Settings {
    public int multiplicationBegin;
    public int multiplicationEnd;
    public Mode mode;

    private ArrayList<Operation> operations;

    public Settings(Operation operation, int tableStart, int tableEnd, Mode mode) {

        this.operations= new ArrayList<>();
        this.operations.add(operation);

        this.multiplicationBegin = tableStart;
        this.multiplicationEnd = tableEnd;
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Settings(ArrayList<Operation> modes, int tableStart, int tableEnd) {

        this.operations= new ArrayList<>();
        this.operations.addAll(modes);

        this.multiplicationBegin = tableStart;
        this.multiplicationEnd = tableEnd;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "tableStart=" + multiplicationBegin +
                ", tableEnd=" + multiplicationEnd +
                ", mode=" + operations +
                '}';
    }


    public ArrayList<Operation> getModes() {
        return operations;
    }

    public void setMode(ArrayList<Operation> modes) {
        this.operations = modes;
    }

    public int getMultiplicationBegin() {
        return multiplicationBegin;
    }

    public void setMultiplicationBegin(int multiplicationBegin) {
        this.multiplicationBegin = multiplicationBegin;
    }

    public int getMultiplicationEnd() {
        return multiplicationEnd;
    }

    public void setMultiplicationEnd(int multiplicationEnd) {
        this.multiplicationEnd = multiplicationEnd;
    }
}
