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
        return this.initialPoint;
    }

    public Score getScore() {
        return this.score;
    }

    public double getTotalScore() {
        return this.initialPoint + this.score.getTotalScores();
    }

    public void display() {
        System.out.println("\t" + super.getName() + "\t\t" + this.as + "\t\t" + super.getInstitution());
    }
}
