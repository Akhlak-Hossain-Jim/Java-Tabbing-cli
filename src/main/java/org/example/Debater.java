package org.example;

public class Debater extends Participants {
    private String as = "Debater";

    public Debater() {
    }

    public Debater(String n, String In) {
        super(n, In);
    }

    public void display() {
        System.out.println("\t" + super.getName() + "\t\t" + as + "\t\t" + super.getInstitution());
    }
}
