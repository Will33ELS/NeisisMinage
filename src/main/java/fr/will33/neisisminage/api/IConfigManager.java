package fr.will33.neisisminage.api;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

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
    String getMessagesMinexpHelp();
    String getMessagesMinexp();
    String getMessagesMinexpResetHelp();
    String getMessagesMinexpReset();
    String getMessagesMinexpAddHelp();
    String getMessagesMinexpAdd();
    String getMessagesMinexpRemoveHelp();
    String getMessagesMinexpRemove();
    String getMessagesMinageShopCreateHelp();
    String getMessagesMinageShopCreate();
    String getShopAlreadyExist();
    String getNoItemInHand();
    String getPlayerEnoughPoint();
    String getPlayerEnoughXP();
    String getNoPermission();
    String getNotInt();
    String getLower0();
    String getMessagesPoints();
    String getEnoughPoint();
    String getShopTitleGUI();
    String getPreviousItemName();
    String getNextItemName();
    String getDeleteShop();
    List<String> getShopItemLore();
    Boolean isSystemEnabled();
    ItemStack getPickaxe();
    Map<Integer, Map<Enchantment, Integer>> getUpgradePickaxe();
}
