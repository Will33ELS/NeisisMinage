package fr.will33.neisisminage;

import fr.will33.neisisminage.api.IConfigManager;
import fr.will33.neisisminage.commands.PointsMineCommand;
import fr.will33.neisisminage.commands.XPMineCommand;
import fr.will33.neisisminage.database.MySQLDatabase;
import fr.will33.neisisminage.listener.PlayerListener;
import fr.will33.neisisminage.manager.CommandManager;
import fr.will33.neisisminage.manager.ConfigManager;
import fr.will33.neisisminage.manager.PlayerManager;
import fr.will33.neisisminage.manager.ShopManager;
import fr.will33.neisisminage.models.Gain;
import fr.will33.neisisminage.stockage.PlayerStockage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NeisisMinagePlugin extends JavaPlugin {

    private final Map<Material, Gain> points = new HashMap<>();
    private static NeisisMinagePlugin instance;
    private IConfigManager configManager;
    private MySQLDatabase mySQLDatabase;
    private PlayerManager playerManager;
    private ShopManager shopManager;

    public NeisisMinagePlugin() {
        super();
    }

    protected NeisisMinagePlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        this.configManager = new ConfigManager(this.getConfig());
        this.mySQLDatabase = new MySQLDatabase(
                this.getConfig().getString("mysql.host"),
                this.getConfig().getInt("mysql.port"),
                this.getConfig().getString("mysql.database"),
                this.getConfig().getString("mysql.username"),
                this.getConfig().getString("mysql.password")
        );

        for(String key : this.getConfig().getConfigurationSection("points").getKeys(false)){
            Material material;
            try{
                material = Material.valueOf(key);
            } catch (IllegalArgumentException err){
                err.printStackTrace();
                continue;
            }
            int points = this.getConfig().getInt("points." + key + ".points");
            int xp = this.getConfig().getInt("points." + key + ".xp");
            this.points.put(material, new Gain(xp, points));
        }

        this.playerManager = new PlayerManager(this.configManager, new PlayerStockage(this.mySQLDatabase, this.getConfig().getString("mysql.prefixTable")));
        this.shopManager = new ShopManager();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        new CommandManager().registerCommands(this);
    }

    /**
     * Get configuration instance
     * @return
     */
    public IConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Get {@link PlayerManager} instance
     * @return
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * Get {@link ShopManager} instance
     * @return
     */
    public ShopManager getShopManager() {
        return shopManager;
    }

    /**
     * Get database connection
     * @return
     */
    public MySQLDatabase getMySQLDatabase() {
        return mySQLDatabase;
    }

    /**
     * Retrieve points value
     * @return
     */
    public Map<Material, Gain> getPoints() {
        return points;
    }

    /**
     * Get plugin instance
     * @return
     */
    public static NeisisMinagePlugin getInstance(){
        return instance;
    }
}
