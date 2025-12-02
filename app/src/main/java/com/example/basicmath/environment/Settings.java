package com.example.basicmath.environment;

import com.example.basicmath.utils.Mode;

public class Settings {
    //trocar esses boolean pra uma classe enum Modes com os modos
//    public Boolean hardMode;
//    public Boolean percentageMode;
//    public Boolean DateMode;
    public int tableStart;
    public int tableEnd;

    private Mode mode;

    public Settings(Mode mode, int tableStart, int tableEnd) {
        this.mode = mode;
        this.tableStart = tableStart;
        this.tableEnd = tableEnd;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "tableStart=" + tableStart +
                ", tableEnd=" + tableEnd +
                ", mode=" + mode +
                '}';
    }


    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getTableStart() {
        return tableStart;
    }

    public void setTableStart(int tableStart) {
        this.tableStart = tableStart;
    }

    public int getTableEnd() {
        return tableEnd;
    }

    public void setTableEnd(int tableEnd) {
        this.tableEnd = tableEnd;
    }
}
