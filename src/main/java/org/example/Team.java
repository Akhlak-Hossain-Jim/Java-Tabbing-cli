package org.example;

import java.util.ArrayList;

public class Team {
    private String name;
    private String institution;
    private ArrayList<Debater> Members = new ArrayList<Debater>();
    private Score score = new Score();

    public Team() {
    }

    public Team(String n, String in, ArrayList<Debater> a) {
        this.name = n;
        this.institution = in;
        Members = a;
    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public Score getScore() {
        return score;
    }
    
    public int getTotalScore() {
        return score.getTotalScores();
    }
    
    public int getWins() {
        return score.getWins();
    }

    public void display() {
        System.out.println("\t" + name + score.toString());
    }

    public String toString() {
        return "\t" + name + "\t" + institution + "\t" + Members.get(0).getName() + "\t" + Members.get(1).getName()
                + "\t" + Members.get(2).getName();
    }
}
