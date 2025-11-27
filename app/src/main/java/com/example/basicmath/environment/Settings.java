package com.example.basicmath.environment;

public class Settings {
    //trocar esses boolean pra uma classe enum Modes com os modos
    public Boolean hardMode;
    public Boolean percentageMode;
    public Boolean DateMode;
    public int tableStart;
    public int tableEnd;

    public Settings(Boolean hardMode, Boolean percentageMode, Boolean dateMode, int tableStart, int tableEnd) {
        this.hardMode = hardMode;
        this.percentageMode = percentageMode;
        DateMode = dateMode;
        this.tableStart = tableStart;
        this.tableEnd = tableEnd;
    }

    @Override
    public String toString() {
        return "AllSettings{" +
                "hardMode=" + hardMode +
                ", percentageMode=" + percentageMode +
                ", DateMode=" + DateMode +
                ", tableStart=" + tableStart +
                ", tableEnd=" + tableEnd +
                '}';
    }

    public Boolean getHardMode() {
        return hardMode;
    }

    public void setHardMode(Boolean hardMode) {
        this.hardMode = hardMode;
    }

    public Boolean getPercentageMode() {
        return percentageMode;
    }

    public void setPercentageMode(Boolean percentageMode) {
        this.percentageMode = percentageMode;
    }

    public Boolean getDateMode() {
        return DateMode;
    }

    public void setDateMode(Boolean dateMode) {
        DateMode = dateMode;
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
