package fr.will33.neisisminage.models;

import fr.will33.neisisminage.manager.PlayerManager;

import java.util.UUID;

public class NNPlayer {

    private final String playerName;
    private int level, points, totalXP;

    public NNPlayer(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get name of the player
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Define total XP
     * @param totalXP Total XP
     */
    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }

    /**
     * Get total XP
     * @return
     */
    public int getTotalXP() {
        return totalXP;
    }

    /**
     * Get points
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * Take points
     * @param points
     */
    public void takePoints(int points){
        this.points -= points;
    }

    /**
     * Define points
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Add xp
     * @param xp XP to add
     */
    public void addProgression(PlayerManager playerManager, int xp, int points){
        this.points += points;
        int xpRequire = playerManager.getXPRequire(this.level + 1);
        if (xp + this.totalXP >= xpRequire){
            this.level ++;
            this.totalXP = xp + this.totalXP - xpRequire;
        } else {
            this.totalXP += xp;
        }
    }

    /**
     * Get level
     * @return
     */
    public int getLevel(){
        return this.level;
    }

    /**
     * Define level
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Reset player
     */
    public void reset(){
        this.totalXP = 0;
        this.level = 0;
    }

}
