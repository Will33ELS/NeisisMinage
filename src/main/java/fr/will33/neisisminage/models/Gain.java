package fr.will33.neisisminage.models;

public class Gain {

    private final int xp, mines;

    public Gain(int xp, int mines) {
        this.xp = xp;
        this.mines = mines;
    }

    public int getXp() {
        return xp;
    }

    public int getMines() {
        return mines;
    }
}
