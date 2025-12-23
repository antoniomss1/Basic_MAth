package com.example.basicmath.models;

public class GameHistory {

    private int numberInSequence;
    private String dateTime;
    private int numberOfProblems;
    private String averageTime;
    private String timeSpent;
    private String accuracy;
    private int biggestSequence;

    public GameHistory(int numberInSequence, String dateTime,
                       int numberOfProblems, String averageTime,
                       String timeSpent, String accuracy,
                       int biggestSequence) {
        this.numberInSequence = numberInSequence;
        this.dateTime = dateTime;
        this.numberOfProblems = numberOfProblems;
        this.averageTime = averageTime;
        this.timeSpent = timeSpent;
        this.accuracy = accuracy;
        this.biggestSequence = biggestSequence;
    }

    public int getNumberInSequence() { return numberInSequence; }
    public String getDateTime() { return dateTime; }
    public int getNumberOfProblems() { return numberOfProblems; }
    public String getAverageTime() { return averageTime; }
    public String getTimeSpent() { return timeSpent; }
    public String getAccuracy() { return accuracy; }
    public int getBiggestSequence() { return biggestSequence; }
}
