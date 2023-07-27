package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    public void registerCommands(JavaPlugin javaPlugin){
        javaPlugin.getCommand("minepointsadd").setExecutor(new MinePointsAddCommand());
        javaPlugin.getCommand("minepoints").setExecutor(new MinePointsCommand());
        javaPlugin.getCommand("minepointsreset").setExecutor(new MinePointsResetCommand());
        javaPlugin.getCommand("minepointstake").setExecutor(new MinePointsTakeCommand());
        javaPlugin.getCommand("xpmine").setExecutor(new XPMineCommand());
        javaPlugin.getCommand("pointsmine").setExecutor(new PointsMineCommand());
    }

}
