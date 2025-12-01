package com.example.basicmath.environment;

public class Settings {
    //trocar esses boolean pra uma classe enum Modes com os modos
//    public Boolean hardMode;
//    public Boolean percentageMode;
//    public Boolean DateMode;
    public int tableStart;
    public int tableEnd;

    public int mode;
//    public Settings(Boolean hardMode, Boolean percentageMode, Boolean dateMode, int tableStart, int tableEnd) {
//        this.hardMode = hardMode;
//        this.percentageMode = percentageMode;
//        DateMode = dateMode;
//        this.tableStart = tableStart;
//        this.tableEnd = tableEnd;
//    }

    public Settings(int mode, int tableStart, int tableEnd) {
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


    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
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
