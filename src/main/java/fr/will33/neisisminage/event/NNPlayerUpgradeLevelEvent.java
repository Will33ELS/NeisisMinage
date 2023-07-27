package fr.will33.neisisminage.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event called when player levels up
 */
public class NNPlayerUpgradeLevelEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    private final Player player;
    private final Integer newLevel;
    public NNPlayerUpgradeLevelEvent(Player player, Integer newLevel){
        this.player = player;
        this.newLevel = newLevel;
    }

    /**
     * Get player instance
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get new level
     * @return
     */
    public Integer getNewLevel() {
        return newLevel;
    }

    /**
     * @exclude
     */
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * @exclude
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Call event
     */
    public void callEvent(){
        Bukkit.getPluginManager().callEvent(this);
    }

}
