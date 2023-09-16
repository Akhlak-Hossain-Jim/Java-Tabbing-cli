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
        this.Members = a;
    }

    public String getName() {
        return this.name;
    }

    public String getInstitution() {
        return this.institution;
    }

    public Score getScore() {
        return this.score;
    }

    public int getTotalScore() {
        return this.score.getTotalScores();
    }

    public int getWins() {
        return this.score.getWins();
    }

    public void display() {
        System.out.println("\t" + this.name + "\t" + this.score.toString());
    }

    public String toString() {
        return "\t" + this.name + "\t" + this.institution + "\t" + this.Members.get(0).getName() + "\t"
                + this.Members.get(1).getName()
                + "\t" + this.Members.get(2).getName();
    }
}
