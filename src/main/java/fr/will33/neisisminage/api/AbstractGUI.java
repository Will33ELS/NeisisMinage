package fr.will33.neisisminage.api;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGUI {

    protected Inventory inventory;
    protected final Map<Integer, String> actions = new HashMap<>();

    /**
     * Open GUI
     * @param player Instance of the player
     */
    public abstract void onDisplay(Player player);

    /**
     * Update GUI
     * @param player Instance of the player
     */
    public void onUpdate(Player player) { }

    /**
     * Click action
     * @param player Instance of the player
     * @param itemStack Instance of the item
     * @param action Action on the slot
     * @param clickType Type of the click
     */
    public void onClick(Player player, ItemStack itemStack, String action, ClickType clickType) {}

    /**
     * Define item in GUI with action
     * @param itemStack Instance of the item
     * @param slot Slot to display the item
     * @param action Action on the slot
     */
    public void setSlotData(ItemStack itemStack, int slot, String action){
        Preconditions.checkNotNull(this.inventory);
        this.inventory.setItem(slot, itemStack);
        this.actions.put(slot, action);
    }

    /**
     * Get all actions
     * @return
     */
    public Map<Integer, String> getActions() {
        return Collections.unmodifiableMap(this.actions);
    }

    /**
     * Get inventory instance
     * @return
     */
    public Inventory getInventory() {
        return inventory;
    }
}
