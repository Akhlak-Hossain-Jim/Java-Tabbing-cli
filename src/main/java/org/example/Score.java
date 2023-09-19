package org.example;

public class Score {
    private int round1 = 0;
    private int round2 = 0;
    private int round3 = 0;
    private int wins = 0;
    private int semifinal = -1;
    private int finalScore = -1;

    public Score() {
    }

    public void addScore(int cr, int val, int wo) {
        switch (cr) {
            case 1:
                round1 = val;
                wins += wo;
                break;
            case 2:
                round2 = val;
                wins += wo;
                break;
            case 3:
                round3 = val;
                wins += wo;
                break;
            case 4:
                semifinal = wo;
                break;
            case 5:
                finalScore = wo;
                break;
            default:
                break;
        }
    }

    public int getTotalScores() {
        return round1 + round2 + round3;
    }

    public int getRound1() {
        return round1;
    }

    public int getRound2() {
        return round2;
    }

    public int getRound3() {
        return round3;
    }

    public int getSF() {
        return semifinal;
    }

    public int getF() {
        return finalScore;
    }

    public int getWins() {
        return wins;
    }

    public void display() {
        System.out.println("\tScores:");
        System.out.println("\t\tRound 1 : " + round1);
        System.out.println("\t\tRound 2 : " + round2);
        System.out.println("\t\tRound 3 : " + round3);
        if (semifinal == 0 || semifinal == 1) {
            if (semifinal == 1)
                System.out.println("\t\tSemifinal: Win");
            else
                System.out.println("\t\tSemifinal: Lose");
        }
        if (finalScore == 0 || finalScore == 1) {
            if (finalScore == 1)
                System.out.println("\t\tFinal       : Win");
            else
                System.out.println("\t\tFinal       : Lose");
        }
    }

    public String toString() {
        String s = "";
        s += "\t" + round1;
        s += "\t" + round2;
        s += "\t" + round3;
        s += "\t" + wins;
        s += "\t" + getTotalScores();
        // if (semifinal == 0 || semifinal == 1) {
        // if (semifinal == 1)
        // System.out.println("\t\tSemifinal: Win");
        // else
        // System.out.println("\t\tSemifinal: Lose");
        // }
        // if (finalScore == 0 || finalScore == 1) {
        // if (finalScore == 1)
        // System.out.println("\t\tFinal : Win");
        // else
        // System.out.println("\t\tFinal : Lose");
        // }
        return s;
    }
}
