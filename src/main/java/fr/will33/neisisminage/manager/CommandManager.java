package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    /**
     * Register plugin commands
     * @param javaPlugin Instance of the plugin
     */
    public void registerCommands(JavaPlugin javaPlugin){
        javaPlugin.getCommand("minepointsadd").setExecutor(new MinePointsAddCommand());
        javaPlugin.getCommand("minepoints").setExecutor(new MinePointsCommand());
        javaPlugin.getCommand("minepointsreset").setExecutor(new MinePointsResetCommand());
        javaPlugin.getCommand("minepointstake").setExecutor(new MinePointsTakeCommand());
        javaPlugin.getCommand("minexp").setExecutor(new MinexpCommand());
        javaPlugin.getCommand("minexpreset").setExecutor(new MinexpResetCommand());
        javaPlugin.getCommand("minexpadd").setExecutor(new MinexpAddCommand());
        javaPlugin.getCommand("minexpremove").setExecutor(new MinexpRemoveCommand());
        javaPlugin.getCommand("xpmine").setExecutor(new XPMineCommand());
        javaPlugin.getCommand("pointsmine").setExecutor(new PointsMineCommand());
    }

}
