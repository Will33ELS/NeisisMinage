package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.IConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ConfigManager implements IConfigManager {

    public final Integer XP_REQUIRED_PER_LEVEL;
    public final String MESSAGES_LEVEL_MINER,
            MESSAGES_POINTS,
            MESSAGES_MINEPOINT_ADD_HELP,
            MESSAGES_MINEPOINT_ADD,
            MESSAGES_MINEPOINT_HELP,
            MESSAGES_MINEPOINT,
            MESSAGES_MINEPOINT_TAKE_HELP,
            MESSAGES_MINEPOINT_TAKE,
            MESSAGES_MINEPOINT_RESET_HELP,
            MESSAGES_MINEPOINT_RESET,
            MESSAGES_PLAYER_ENOUGH_POINTS,
            MESSAGES_NO_PERMISSIONS,
            MESSAGES_NOT_INT,
            MESSAGES_LOWER_0
                    ;
    public final Boolean SYSTEM_ENABLED;
    public final ItemStack PICKAXE;
    public ConfigManager(FileConfiguration configuration) throws IllegalArgumentException{
        XP_REQUIRED_PER_LEVEL = configuration.getInt("config.xpRequiredPerLevel");
        MESSAGES_LEVEL_MINER = configuration.getString("messages.levelMiner");
        MESSAGES_POINTS = configuration.getString("messages.points");
        MESSAGES_MINEPOINT_ADD_HELP = configuration.getString("messages.minepointAddHelp");
        MESSAGES_MINEPOINT_ADD = configuration.getString("messages.minepointAdd");
        MESSAGES_MINEPOINT_HELP = configuration.getString("messages.minepointsHelp");
        MESSAGES_MINEPOINT = configuration.getString("messages.minepoints");
        MESSAGES_MINEPOINT_TAKE_HELP = configuration.getString("messages.minepointTakeHelp");
        MESSAGES_MINEPOINT_TAKE = configuration.getString("messages.minepointTake");
        MESSAGES_MINEPOINT_RESET_HELP = configuration.getString("messages.minepointResetHelp");
        MESSAGES_MINEPOINT_RESET = configuration.getString("messages.minepointReset");
        MESSAGES_PLAYER_ENOUGH_POINTS = configuration.getString("messages.playerEnoughPoint");
        MESSAGES_NO_PERMISSIONS = configuration.getString("messages.noPermission");
        MESSAGES_NOT_INT = configuration.getString("messages.notInt");
        MESSAGES_LOWER_0 = configuration.getString("messages.lowerO");
        SYSTEM_ENABLED = configuration.getBoolean("systemEnabled");
        PICKAXE = new ItemStack(Material.valueOf(configuration.getString("defaultPickaxe.material")));
        for(String key : configuration.getConfigurationSection("defaultPickaxe.enchant").getKeys(false)){
            if(Enchantment.getByName(key) == null) {
                NeisisMinagePlugin.getInstance().getLogger().warning("Enchantement " + key + " inconnu !");
            } else {
                PICKAXE.addEnchantment(Enchantment.getByName(key), configuration.getInt("defaultPickaxe.enchant." + key));
            }
        }
    }

    @Override
    public Integer getXpRequiredPerLevel() {
        return this.XP_REQUIRED_PER_LEVEL;
    }

    @Override
    public String getMessagesLevelMiner() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_LEVEL_MINER);
    }

    @Override
    public String getMessagesMinepointAddHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_ADD_HELP);
    }

    @Override
    public String getMessagesMinepointAdd() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_ADD);
    }

    @Override
    public String getMessagesMinepointHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_HELP);
    }

    @Override
    public String getMessagesMinepoint() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT);
    }

    @Override
    public String getMessagesMinepointTakeHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_TAKE_HELP);
    }

    @Override
    public String getMessagesMinepointTake() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_TAKE);
    }

    @Override
    public String getMessagesMinepointResetHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_RESET_HELP);
    }

    @Override
    public String getMessagesMinepointReset() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_RESET);
    }

    @Override
    public String getPlayerEnoughPoint() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_PLAYER_ENOUGH_POINTS);
    }

    @Override
    public String getNoPermission() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_NO_PERMISSIONS);
    }

    @Override
    public String getNotInt() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_NOT_INT);
    }

    @Override
    public String getLower0() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_LOWER_0);
    }

    @Override
    public String getMessagesPoints() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_POINTS);
    }

    @Override
    public Boolean isSystemEnabled() {
        return this.SYSTEM_ENABLED;
    }

    @Override
    public ItemStack getPickaxe() {
        return this.PICKAXE;
    }
}
