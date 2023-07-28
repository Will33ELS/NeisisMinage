package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.IConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

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
            MESSAGES_MINEXP_HELP,
            MESSAGES_MINEXP,
            MESSAGES_MINEXP_RESET_HELP,
            MESSAGES_MINEXP_RESET,
            MESSAGES_MINEXP_ADD_HELP,
            MESSAGES_MINEXP_ADD,
            MESSAGES_MINEXP_REMOVE_HELP,
            MESSAGES_MINEXP_REMOVE,
            MESSAGES_PLAYER_ENOUGH_XP,
            MESSAGES_PLAYER_ENOUGH_POINTS,
            MESSAGES_NO_PERMISSIONS,
            MESSAGES_NOT_INT,
            MESSAGES_LOWER_0
                    ;
    public final Boolean SYSTEM_ENABLED;
    public final ItemStack PICKAXE;
    public final Map<Integer, Map<Enchantment, Integer>> UPGRADE_PICKAXE = new HashMap<>();
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
        MESSAGES_MINEXP_HELP = configuration.getString("messages.minexpHelp");
        MESSAGES_MINEXP = configuration.getString("messages.minexp");
        MESSAGES_MINEXP_RESET_HELP = configuration.getString("messages.minexpResetHelp");
        MESSAGES_MINEXP_RESET = configuration.getString("messages.minexpReset");
        MESSAGES_MINEXP_ADD_HELP = configuration.getString("messages.minexpAddHelp");
        MESSAGES_MINEXP_ADD = configuration.getString("messages.minexpAdd");
        MESSAGES_MINEXP_REMOVE_HELP = configuration.getString("messages.minexpRemoveHelp");
        MESSAGES_MINEXP_REMOVE = configuration.getString("messages.minexpRemove");
        MESSAGES_PLAYER_ENOUGH_XP = configuration.getString("messages.playerEnoughXP");
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
        for(String key : configuration.getConfigurationSection("level").getKeys(false)){
            Integer level = Integer.parseInt(key);
            Map<Enchantment, Integer> enchantments = new HashMap<>();
            configuration.getConfigurationSection("level." + level).getKeys(false).forEach(enchantment -> enchantments.put(Enchantment.getByName(enchantment), configuration.getInt("level." + level + "." + enchantment)));
            UPGRADE_PICKAXE.put(level, enchantments);
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
    public String getMessagesMinexpHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP_HELP);
    }

    @Override
    public String getMessagesMinexp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP);
    }

    @Override
    public String getMessagesMinexpResetHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP_RESET_HELP);
    }

    @Override
    public String getMessagesMinexpReset() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEPOINT_RESET);
    }

    @Override
    public String getMessagesMinexpAddHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP_ADD_HELP);
    }

    @Override
    public String getMessagesMinexpAdd() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP_ADD);
    }

    @Override
    public String getMessagesMinexpRemoveHelp() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP_REMOVE_HELP);
    }

    @Override
    public String getMessagesMinexpRemove() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_MINEXP_REMOVE);
    }

    @Override
    public String getPlayerEnoughPoint() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_PLAYER_ENOUGH_POINTS);
    }

    @Override
    public String getPlayerEnoughXP() {
        return ChatColor.translateAlternateColorCodes('&', this.MESSAGES_PLAYER_ENOUGH_XP);
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

    @Override
    public Map<Integer, Map<Enchantment, Integer>> getUpgradePickaxe() {
        return this.UPGRADE_PICKAXE;
    }
}
