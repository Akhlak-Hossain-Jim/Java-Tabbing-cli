package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Round {
    private int roundNo;
    private String motion;

    private ArrayList<Team> Teams = new ArrayList<Team>();
    private ArrayList<Adjudicator> Adj = new ArrayList<Adjudicator>();
    private ArrayList<MatchUp> MatchUps = new ArrayList<MatchUp>();

    public Round(int no, ArrayList<Team> t, ArrayList<Adjudicator> a) {
        roundNo = no;
        Teams = t;
        Adj = a;
    }

    private void Sorter() {
    	 Comparator<Team> comparator = Comparator.comparingInt(Team::getTotalScore);
    	 Collections.sort(Teams, comparator);
    	 Comparator<Team> comparator2 = Comparator.comparingInt(Team::getWins);
    	 Collections.sort(Teams, comparator2);
//    	 for(Team a: Teams) {
//    		 System.out.println(a.getName()+"\t"+a.getTotalScore()+"\t"+a.getWins());
//    	 }
    	 Comparator<Adjudicator> comparatorA = Comparator.comparingInt(Adjudicator::getTotalScore);
    	 Collections.sort(Adj, comparatorA);
    }

    public void Generate() {
    	System.out.println("\t:........Generating matchup for " + RoundNoDisplay());
        switch (roundNo) {
            case 1: {
                int q = 0;
                for (int i = Teams.size() - 1; i >= 0; i -= 2) {
                    MatchUps.add(new MatchUp(Teams.get(i), Teams.get(i - 1), ("0" + (q + 1))));
                    MatchUps.get(q).AddJudge(Adj.get(Adj.size() - 1 - q));
                    q++;
                }
                break;
            }
            case 2:
            case 3: {
                Sorter();
                int q = 0;
                for (int i = Teams.size() - 1; i >= 0; i -= 2) {
                    MatchUps.add(new MatchUp(Teams.get(i), Teams.get(i - 1), ("0" + (q + 1))));
                    MatchUps.get(q).AddJudge(Adj.get(Adj.size() - 1 - q));
                    q++;
                }
                break;
            }
            case 4: {
                Sorter();
                int s = Teams.size();
                    MatchUps.add(new MatchUp(Teams.get(s-1), Teams.get(s - 2), ("01")));
                    MatchUps.get(0).AddJudge(Adj.get(Adj.size() - 1));
                    MatchUps.get(0).AddJudge(Adj.get(Adj.size() - 2));
                    MatchUps.get(0).AddJudge(Adj.get(Adj.size() - 3));
                    
                    MatchUps.add(new MatchUp(Teams.get(s-3), Teams.get(s - 4), ("02")));
                    MatchUps.get(1).AddJudge(Adj.get(Adj.size() - 4));
                    MatchUps.get(1).AddJudge(Adj.get(Adj.size() - 5));
                    MatchUps.get(1).AddJudge(Adj.get(Adj.size() - 6));
                break;
            }
            case 5: {
                Sorter();
                MatchUps.add(new MatchUp(Teams.get(Teams.size() - 1), Teams.get(Teams.size() - 2), ("01")));
                MatchUps.get(0).AddJudge(Adj.get(Adj.size() - 1));
                MatchUps.get(0).AddJudge(Adj.get(Adj.size() - 2));
                MatchUps.get(0).AddJudge(Adj.get(Adj.size() - 3));
                break;
            }
            default:
                break;
        }
        System.out.println("\t:::::::::Matchup generation completed for " + RoundNoDisplay());
    }

    private String RoundNoDisplay() {
        switch (roundNo) {
            case 1:
                return "Round 1";
            case 2:
                return "Round 2";
            case 3:
                return "Round 3";
            case 4:
                return "Semi Finals";
            case 5:
                return "Finals";
            default:
                return "";
        }
    }

    public void displayMatchUp() {
        System.out.println("\t**********Match UP for round" + roundNo + "**********");
        System.out.println("\tGovernment\t\tOpposition\t\tRoom No\tAdjudicators");
        for (int i = 0; i < MatchUps.size(); i++) {
            MatchUps.get(i).display();
        }
    }

    public void displayMotion() {
        System.out.println();
        System.out.println("\tMotion for round " + roundNo);
        System.out.println("\t\t" + motion);
        System.out.println();
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public ArrayList<MatchUp> getMatchUps() {
        return MatchUps;
    }
}
