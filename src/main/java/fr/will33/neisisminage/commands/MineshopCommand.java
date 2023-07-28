package fr.will33.neisisminage.commands;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.gui.ShopGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineshopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            NeisisMinagePlugin.getInstance().getGuiManager().openInventory(player, new ShopGUI());
        }
        return false;
    }
}
