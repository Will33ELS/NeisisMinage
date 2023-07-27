package fr.will33.neisisminage.api;

import org.bukkit.inventory.ItemStack;

public interface IConfigManager {

    Integer getXpRequiredPerLevel();
    String getMessagesLevelMiner();
    String getMessagesMinepointAddHelp();
    String getMessagesMinepointAdd();
    String getMessagesMinepointHelp();
    String getMessagesMinepoint();
    String getMessagesMinepointTakeHelp();
    String getMessagesMinepointTake();
    String getMessagesMinepointResetHelp();
    String getMessagesMinepointReset();
    String getPlayerEnoughPoint();
    String getNoPermission();
    String getNotInt();
    String getLower0();
    String getMessagesPoints();
    Boolean isSystemEnabled();
    ItemStack getPickaxe();
}
