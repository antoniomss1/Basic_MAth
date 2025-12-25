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

    public double getPrecision(){
        return (double)(this.numberOfProblems)/(this.wrongProblems+1);
    }
    public double getScore(){
        System.out.println("getting store:");
        double val = this.getNumberOfProblems();
        System.out.println("val: "+val);
        System.out.println("precision: "+this.getPrecision());
        val *= this.getPrecision();
        System.out.println("val: "+val);
        val *= this.averageTime;
        System.out.println("val/(1/avg): "+val);
        val = val*1000;
        val = Math.round(val);
        System.out.println("val rounded: "+val);
        System.out.println("last val: "+val/1000);
        return val/1000;
    }

}
