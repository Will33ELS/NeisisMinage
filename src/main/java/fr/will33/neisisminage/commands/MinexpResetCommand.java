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

public class MinexpResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        IConfigManager configManager = NeisisMinagePlugin.getInstance().getConfigManager();
        if(commandSender.hasPermission("neisisminage.commands.xp.reset")) {
            if (strings.length != 1) {
                commandSender.sendMessage(configManager.getMessagesMinexpResetHelp());
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
                nPlayer.reset();
                commandSender.sendMessage(configManager.getMessagesMinexpReset().replace("{pseudo}", offlinePlayer.getName()));
                NeisisMinagePlugin.getInstance().getPlayerManager().savePlayer(nPlayer, null);
            }
        } else {
            commandSender.sendMessage(configManager.getNoPermission());
        }
        return false;
    }
}
