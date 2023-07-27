package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.models.Shop;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
                Shop shop = new Shop(itemStack, fileConfiguration.getInt("shops." + key + ".price"));
                this.shops.add(shop);
            } catch (IllegalArgumentException err){
                err.printStackTrace();
            }
        }
    }

    /**
     * Get all shops
     * @return
     */
    public List<Shop> getShops() {
        return shops;
    }
}
