package com.example.basicmath.environment;

import com.example.basicmath.models.Mode;
import com.example.basicmath.models.Operation;

import java.util.ArrayList;

public class Settings {
    public int multiplicationBegin;
    public int multiplicationEnd;
    public Boolean base12;
    public Mode mode;

    private ArrayList<Operation> operations;

    public Settings(Operation operation, int tableStart, int tableEnd, Mode mode, Boolean base12) {

        this.operations= new ArrayList<>();
        this.operations.add(operation);

        this.multiplicationBegin = tableStart;
        this.multiplicationEnd = tableEnd;
        this.mode = mode;
        this.base12 = base12;
    }

    public Boolean getBase12() {
        return base12;
    }

    public void setBase12(Boolean base12) {
        this.base12 = base12;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Settings(ArrayList<Operation> modes, int tableStart, int tableEnd, Boolean base12) {

        this.operations= new ArrayList<>();
        this.operations.addAll(modes);

        this.multiplicationBegin = tableStart;
        this.multiplicationEnd = tableEnd;
        this.base12 = base12;
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
