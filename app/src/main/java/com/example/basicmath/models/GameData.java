package com.example.basicmath.models;

public class GameData {

    private String dateTime;
    private int numberOfProblems;
    private double averageTime;
    private int wrongProblems;
    private int biggestSequence;
    private long id;

    public GameData(String dateTime, int numberOfProblems, double averageTime, int wrongProblems, int biggestSequence) {
        this.dateTime = dateTime;
        this.numberOfProblems = numberOfProblems;
        this.averageTime = averageTime;
        this.wrongProblems = wrongProblems;
        this.biggestSequence = biggestSequence;
    }

    public GameData(String dateTime, int numberOfProblems, double averageTime, int wrongProblems, int biggestSequence, int id) {
        this.dateTime = dateTime;
        this.numberOfProblems = numberOfProblems;
        this.averageTime = averageTime;
        this.wrongProblems = wrongProblems;
        this.biggestSequence = biggestSequence;
        this.id = id;
    }

    public String getDateTime() { return dateTime; }
    public int getNumberOfProblems() { return numberOfProblems; }
    public double getAverageTime() { return averageTime; }
    public int getBiggestSequence() { return biggestSequence; }

    @Override
    public String toString() {
        return "GameData{" +
                "dateTime='" + dateTime + '\'' +
                ", numberOfProblems=" + numberOfProblems +
                ", averageTime=" + averageTime +
                ", wrongProblems=" + wrongProblems +
                ", biggestSequence=" + biggestSequence +
                ", id=" + id +
                '}';
    }

    public int getWrongProblems() {
        return wrongProblems;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getWrightProblems(){
        return numberOfProblems - wrongProblems;
    }

    public int getScore(){

    }

}
