package org.example;

import java.util.ArrayList;

public class MatchUp {
    private Team Gov;
    private Team Opp;
    private String Room;
    private ArrayList<Adjudicator> Judges = new ArrayList<Adjudicator>();

    public MatchUp(Team a, Team b, String r) {
        Gov = a;
        Opp = b;
        Room = r;
    }

    public void AddJudge(Adjudicator a) {
        Judges.add(a);
    }

    public void RemoveJudge(Adjudicator a) {
        for (int i = 0; i < Judges.size(); i++) {
            if (Judges.get(i).getName() == a.getName()) {
                Judges.remove(i);
            }
        }
    }

    public Team getGov() {
        return Gov;
    }

    public Team getOpp() {
        return Opp;
    }

    public Adjudicator getJudges() {
        return Judges.get(1);
    }

    private String displayAdj() {
        String a = "";
        for (int i = 0; i < Judges.size(); i++) {
            a += Judges.get(i).getName();
            if (i != Judges.size() - 1) {
                a += ", ";
            }
        }
        return a;
    }

    public void display() {
        System.out.println("\t" + Gov.getName() + "\t\t" + Opp.getName() + "\t\t" + Room + "\t" + "\t" + displayAdj());
    }
}
