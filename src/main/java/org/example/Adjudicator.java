package org.example;

public class Adjudicator extends Participants {
    private String as = "Adjudicator";
    private double initialPoint;

    private Score score = new Score();

    public Adjudicator() {
    }

    public Adjudicator(String n, String In, double ip) {
        super(n, In);
        this.initialPoint = ip;
    }

    public double getInitialPoint() {
        return initialPoint;
    }

    public Score getScore() {
        return score;
    }
    
    public int getTotalScore() {
        return score.getTotalScores();
    }

    public void display() {
        System.out.println("\t" + super.getName() + "\t\t" + as + "\t\t" + super.getInstitution());
    }
}
