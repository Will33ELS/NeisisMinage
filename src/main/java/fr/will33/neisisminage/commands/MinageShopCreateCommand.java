package fr.will33.neisisminage.commands;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.IConfigManager;
import fr.will33.neisisminage.models.Shop;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MinageShopCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            IConfigManager configManager = NeisisMinagePlugin.getInstance().getConfigManager();
            if (commandSender.hasPermission("neisisminage.shop.create")) {
                if (strings.length != 1) {
                    commandSender.sendMessage(configManager.getMessagesMinageShopCreateHelp());
                } else {
                    int points;
                    try {
                        points = Integer.parseInt(strings[0]);
                        if (points < 0) {
                            commandSender.sendMessage(configManager.getLower0());
                            return false;
                        }
                    } catch (NumberFormatException err) {
                        commandSender.sendMessage(configManager.getNotInt());
                        return false;
                    }
                    ItemStack itemStack = player.getItemInHand();
                    if(itemStack == null || itemStack.getType() == Material.AIR){
                        player.sendMessage(configManager.getNoItemInHand());
                    } else {
                        Shop shop = NeisisMinagePlugin.getInstance().getShopManager().getShop(itemStack.getType(), itemStack.getAmount(), itemStack.getData().getData());
                        if(shop != null){
                            player.sendMessage(configManager.getShopAlreadyExist());
                        } else {
                            shop = new Shop(itemStack, points);
                            NeisisMinagePlugin.getInstance().getShopManager().createShop(shop);
                            player.sendMessage(configManager.getMessagesMinageShopCreate());
                        }
                    }
                }
            }
        }
        return false;
    }
}
