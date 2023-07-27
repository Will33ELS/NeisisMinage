package fr.will33.neisisminage.commands;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.IConfigManager;
import fr.will33.neisisminage.exception.NNPlayerException;
import fr.will33.neisisminage.models.NNPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MinePointsTakeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        IConfigManager configManager = NeisisMinagePlugin.getInstance().getConfigManager();
        if(commandSender.hasPermission("neisisminage.commands.take")) {
            if (strings.length != 2) {
                commandSender.sendMessage(configManager.getMessagesMinepointTakeHelp());
            } else {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
                NNPlayer nPlayer;
                try {
                    nPlayer = NeisisMinagePlugin.getInstance().getPlayerManager().getOfflineNNPlayer(offlinePlayer.getUniqueId());
                } catch (NNPlayerException e) {
                    commandSender.sendMessage("§cUn incident s'est produit " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
                int points;
                try {
                    points = Integer.parseInt(strings[1]);
                    if (points < 0) {
                        commandSender.sendMessage(configManager.getLower0());
                        return false;
                    }
                    if(nPlayer.getPoints() < points){
                        commandSender.sendMessage(configManager.getPlayerEnoughPoint());
                        return false;
                    }
                } catch (NumberFormatException err) {
                    commandSender.sendMessage(configManager.getNotInt());
                    return false;
                }
                nPlayer.setPoints(nPlayer.getPoints() - points);
                commandSender.sendMessage(configManager.getMessagesMinepointReset().replace("{points}", String.valueOf(points)).replace("{pseudo}", offlinePlayer.getName()));
                NeisisMinagePlugin.getInstance().getPlayerManager().savePlayer(nPlayer, null);
            }
        } else {
            commandSender.sendMessage(configManager.getNoPermission());
        }
        return false;
    }
}
