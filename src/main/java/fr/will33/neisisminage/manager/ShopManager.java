package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.AbstractGUI;
import fr.will33.neisisminage.gui.ShopGUI;
import fr.will33.neisisminage.models.Shop;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShopManager {
    private final List<Shop> shops = new ArrayList<>();

    public ShopManager() {
        try {
            this.loadConfiguration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadConfiguration() throws IOException {
        File file = new File(NeisisMinagePlugin.getInstance().getDataFolder(), "shops.yml");
        if(!file.exists()){
            Files.copy(NeisisMinagePlugin.getInstance().getResource("shops.yml"), file.toPath());
        }
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        for(String key : fileConfiguration.getConfigurationSection("shops").getKeys(false)){
            try {
                ItemStack itemStack = new ItemStack(
                        Material.valueOf(key),
                        fileConfiguration.getInt("shops." + key + ".amount"),
                        (short) fileConfiguration.getInt("shops." + key + ".data")
                );
                if(this.getShop(itemStack.getType(), itemStack.getAmount(), itemStack.getData().getData()) == null){
                    Shop shop = new Shop(itemStack, fileConfiguration.getInt("shops." + key + ".price"));
                    this.shops.add(shop);
                }
            } catch (IllegalArgumentException err){
                err.printStackTrace();
            }
        }
    }

    /**
     * Delete shop
     * @param shop Instance of the shop
     */
    public void deleteShop(Shop shop){
        this.shops.remove(shop);
        //TODO SAVE SHOPS.YML
        for(Map.Entry<Player, AbstractGUI> gui : NeisisMinagePlugin.getInstance().getGuiManager().getGuis().entrySet()){
            if(gui.getValue() instanceof ShopGUI){
                gui.getValue().onUpdate(gui.getKey());
            }
        }
    }

    /**
     * Get all shops
     * @return
     */
    public List<Shop> getShops() {
        return Collections.unmodifiableList(this.shops);
    }

    public Shop getShop(Material material, int amount, short data){
        return this.getShops().stream().filter(shop -> shop.getItemStack().getType() == material && shop.getItemStack().getAmount() == amount && shop.getItemStack().getData().getData() == data).findFirst().orElse(null);
    }
}
