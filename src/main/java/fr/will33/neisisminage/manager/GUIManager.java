package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.api.AbstractGUI;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GUIManager {

    private final Map<Player, AbstractGUI> guis = new HashMap<>();

    /**
     * Open GUI
     * @param player Instance of the player
     * @param abstractGUI Instance of the GUI
     */
    public void openInventory(Player player, AbstractGUI abstractGUI){
        player.closeInventory();
        this.guis.put(player, abstractGUI);
        abstractGUI.onDisplay(player);
    }

    /**
     * Close inventory
     * @param player
     */
    public void closeInventory(Player player){
        player.closeInventory();
        this.guis.remove(player);
    }

    /**
     * Retrieve open GUI
     * @param player Instance of the player
     * @return
     */
    public Optional<AbstractGUI> getGUIOpened(Player player){
        return Optional.ofNullable(this.guis.get(player));
    }

    /**
     * Get all guis opened
     * @return
     */
    public Map<Player, AbstractGUI> getGuis() {
        return Collections.unmodifiableMap(this.guis);
    }
}
