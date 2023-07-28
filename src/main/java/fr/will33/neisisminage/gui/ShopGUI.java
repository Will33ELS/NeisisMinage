package fr.will33.neisisminage.gui;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.AbstractGUI;
import fr.will33.neisisminage.api.IConfigManager;
import fr.will33.neisisminage.models.NNPlayer;
import fr.will33.neisisminage.models.Shop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShopGUI extends AbstractGUI {
    private final IConfigManager configManager = NeisisMinagePlugin.getInstance().getConfigManager();
    private int page = 0;
    private final int[] slot = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    @Override
    public void onDisplay(Player player) {
        this.inventory = Bukkit.createInventory(null, 6  * 9, configManager.getShopTitleGUI());
        this.onUpdate(player);
        player.openInventory(this.inventory);
    }

    @Override
    public void onUpdate(Player player) {
        this.actions.clear();
        this.inventory.clear();
        List<Shop> shops = NeisisMinagePlugin.getInstance().getShopManager().getShops();

        if(this.page > 0){
            this.setSlotData(this.build(Material.BOOK, 1, (short) 0, configManager.getPreviousItemName(), null), 45, "previous");
        }
        if(shops.size() > this.slot.length * this.page + this.slot.length){
            this.setSlotData(this.build(Material.BOOK, 1, (short) 0, configManager.getNextItemName(), null), 53, "next");
        }
        int index = 0;
        for(int i = this.slot.length * this.page; i < this.slot.length * page + this.slot.length; i++) {
            if (i >= shops.size()) break;
            Shop shop = shops.get(i);
            List<String> lore = new ArrayList<>(this.configManager.getShopItemLore().stream().map(l -> l.replace("{price}", String.valueOf(shop.getPrice()))).collect(Collectors.toList()));
            if(player.hasPermission("neisisminage.shop.delete")){
                lore.add("");
                lore.add(this.configManager.getDeleteShop());
            }
            this.setSlotData(this.build(
                    shop.getItemStack().getType(),
                    shop.getItemStack().getAmount(),
                    shop.getItemStack().getData().getData(),
                    null,
                    lore
            ), this.slot[index++], "");
        }
    }

    @Override
    public void onClick(Player player, ItemStack itemStack, String action, ClickType clickType) {
        if("previous".equals(action)){
            this.page --;
            this.onUpdate(player);
        } else if("next".equals(action)){
            this.page ++;
            this.onUpdate(player);
        } else {
            NeisisMinagePlugin.getInstance().getPlayerManager().getNNPlayer(player.getName()).ifPresent(nnPlayer -> {
                Shop shop = NeisisMinagePlugin.getInstance().getShopManager().getShop(itemStack.getType(), itemStack.getAmount(), itemStack.getData().getData());
                Optional.ofNullable(shop).ifPresent(s -> {
                    if(clickType == ClickType.DROP && player.hasPermission("neisisminage.shop.delete")){
                        NeisisMinagePlugin.getInstance().getShopManager().deleteShop(shop);
                    } else {
                        if (nnPlayer.getPoints() < shop.getPrice()) {
                            player.sendMessage(this.configManager.getEnoughPoint());
                        } else {
                            player.getInventory().addItem(shop.getItemStack());
                            nnPlayer.takePoints(shop.getPrice());
                        }
                    }
                });
            });
        }
    }

    private ItemStack build(Material material, int amount, short data, String displayname, List<String> lore){
        ItemStack itemStack = new ItemStack(material, amount, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
