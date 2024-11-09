package com.example.runsyncproject;

public class Bowler {
    int balls;

    int overs;
    String name;
    int runs;
    int wickets;
    int maidens;

    public Bowler()
    {

    }
    public Bowler(String name) {
        this.name = name;
    }

    public Bowler(String name, int overs, int maidens, int runs, int wickets) {
        this.name = name;
        this.overs = overs;
        this.maidens = maidens;
        this.runs = runs;
        this.wickets = wickets;
    }



    public void setBalls(int balls) {
        this.balls=balls;
    }
    public void setBalls(){
        balls=balls+1;
    if(balls==6)
    {
     balls=0;
     overs=overs+1;
    }
    }

    public void  setName(String name){this.name=name;}

    public void setWickets(){wickets=wickets+1;}
    public void setWickets(int wickets){this.wickets = wickets;}
    public void setOvers(int overs){this.overs=overs;}


    public void setMaidens(int maidens){this.maidens=maidens;}

    public int getBalls() {return balls;}

    public String getName() {return name;}

    public int getRuns() {return runs;}

    public int getWickets() {return wickets; }
    public int getOvers() {return overs;}
    public int getMaidens() {return maidens;}

    public void setRuns(int runs) {
        this.runs = this.runs+runs;
    }
    public void setRuns2(int runs){
        this.runs = runs;
    }

}
