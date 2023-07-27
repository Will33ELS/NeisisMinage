package fr.will33.neisisminage.commands;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointsMineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            NeisisMinagePlugin.getInstance().getPlayerManager().getNNPlayer(player.getUniqueId()).ifPresent(nPlayer -> {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        NeisisMinagePlugin.getInstance().getConfigManager().getMessagesPoints()
                                .replace("{points}", String.valueOf(nPlayer.getPoints()))
                ));
            });

        }
        return false;
    }
}
