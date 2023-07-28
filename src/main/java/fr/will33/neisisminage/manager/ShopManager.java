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

    /**
     * Load shops.yml configuration
     * @throws IOException
     */
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
        this.saveShopsConfiguration();
        this.updateShopGUI();
    }

    /**
     * Create shop
     * @param shop Instance of the new shop
     */
    public void createShop(Shop shop){
        this.shops.add(shop);
        this.saveShopsConfiguration();
        this.updateShopGUI();
    }

    /**
     * Save shops.yml
     */
    private void saveShopsConfiguration(){
        try {
            File file = new File(NeisisMinagePlugin.getInstance().getDataFolder(), "shops.yml");
            YamlConfiguration configuration = new YamlConfiguration();
            for (Shop shop : this.shops) {
                ItemStack itemStack = shop.getItemStack();
                configuration.set("shops." + itemStack.getType() + ".amount", itemStack.getAmount());
                configuration.set("shops." + itemStack.getType() + ".data", itemStack.getData().getData());
                configuration.set("shops." + itemStack.getType() + ".price", shop.getPrice());
            }
            configuration.save(file);
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    /**
     * Update all shop GUI
     */
    private void updateShopGUI(){
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

    /**
     * Get shop
     * @param material Material of the item
     * @param amount Amount of the item
     * @param data Data of the item
     * @return
     */
    public Shop getShop(Material material, int amount, short data){
        return this.getShops().stream().filter(shop -> shop.getItemStack().getType() == material && shop.getItemStack().getAmount() == amount && shop.getItemStack().getData().getData() == data).findFirst().orElse(null);
    }
}
