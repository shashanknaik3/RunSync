package com.example.runsyncproject;

public class Batsman {

    public  String name;
//    public String status;
    public  int runs,balls,four,six;

    public Batsman() {
    }

    public Batsman(String name)
    {
        this.name=name;
        this.runs =0;
        this.balls=0;
        this.four=0;
        this.six=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }




    public void addballs()
    {

        balls=balls+1;
    }
    public void addruns(int run)
    {
        if(run==4)
            this.four++;

        if(run==6)
            this.six++;

        runs = runs + run;

    }



}
