package fr.will33.neisisminage.commands;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XPMineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            NeisisMinagePlugin.getInstance().getPlayerManager().getNNPlayer(player.getUniqueId()).ifPresent(nPlayer -> {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        NeisisMinagePlugin.getInstance().getConfigManager().getMessagesLevelMiner()
                                .replace("{level}", String.valueOf(nPlayer.getLevel()))
                                .replace("{current_xp}", String.valueOf(nPlayer.getTotalXP()))
                                .replace("{xp_required}", String.valueOf(NeisisMinagePlugin.getInstance().getPlayerManager().getXPRequire(nPlayer.getLevel() + 1)))
                ));
            });

        }
        return false;
    }
}
