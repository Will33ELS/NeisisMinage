package fr.will33.neisisminage.models;

import org.bukkit.inventory.ItemStack;

public class Shop {

    private final ItemStack itemStack;
    private final Integer price;

    public Shop(ItemStack itemStack, Integer price) {
        this.itemStack = itemStack;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
