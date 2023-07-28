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

public class MinexpRemoveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        IConfigManager configManager = NeisisMinagePlugin.getInstance().getConfigManager();
        if(commandSender.hasPermission("neisisminage.commands.xp.remove")) {
            if (strings.length != 2) {
                commandSender.sendMessage(configManager.getMessagesMinexpRemoveHelp());
            } else {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
                NNPlayer nPlayer;
                try {
                    nPlayer = NeisisMinagePlugin.getInstance().getPlayerManager().getOfflineNNPlayer(offlinePlayer.getName());
                } catch (NNPlayerException e) {
                    commandSender.sendMessage("§cUn incident s'est produit " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
                int xp;
                try {
                    xp = Integer.parseInt(strings[1]);
                    if (xp < 0) {
                        commandSender.sendMessage(configManager.getLower0());
                        return false;
                    }
                    if(nPlayer.getTotalXP() < xp){
                        commandSender.sendMessage(configManager.getPlayerEnoughXP());
                        return false;
                    }
                } catch (NumberFormatException err) {
                    commandSender.sendMessage(configManager.getNotInt());
                    return false;
                }
                nPlayer.setTotalXP(nPlayer.getTotalXP() - xp);
                commandSender.sendMessage(configManager.getMessagesMinexpRemove().replace("{xp}", String.valueOf(xp)).replace("{pseudo}", offlinePlayer.getName()));
                NeisisMinagePlugin.getInstance().getPlayerManager().savePlayer(nPlayer, null);
            }
        } else {
            commandSender.sendMessage(configManager.getNoPermission());
        }
        return false;
    }
}
