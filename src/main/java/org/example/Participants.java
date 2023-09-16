package org.example;

public abstract class Participants {
    private String name;
    private String Id;
    private String institution;
    private static int assigner = 1;

    public Participants() {
    }

    public Participants(String n, String In) {
        this.name = n;
        this.institution = In;
        if (Participants.assigner < 10) {
            this.Id = "0" + Participants.assigner;
        } else {
            this.Id = "" + Participants.assigner;
        }
        assigner++;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public String getInstitution() {
        return this.institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display();
}
