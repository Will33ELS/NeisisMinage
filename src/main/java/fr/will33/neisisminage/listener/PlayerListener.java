package fr.will33.neisisminage.listener;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.event.NNPlayerUpgradeLevelEvent;
import fr.will33.neisisminage.manager.PlayerManager;
import fr.will33.neisisminage.models.NNPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

public class PlayerListener implements Listener {

    private final PlayerManager playerManager = NeisisMinagePlugin.getInstance().getPlayerManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        NNPlayer nPlayer = new NNPlayer(player.getName());
        this.playerManager.loadPlayer(nPlayer, () -> {
            if(NeisisMinagePlugin.getInstance().getConfigManager().isSystemEnabled() && player.getInventory().getItem(0) == null) {
                player.getInventory().setItem(0, NeisisMinagePlugin.getInstance().getConfigManager().getPickaxe());
            }
            this.playerManager.getPlayers().add(nPlayer);
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        this.playerManager.getNNPlayer(player.getName()).ifPresent(nPlayer ->
                this.playerManager.savePlayer(nPlayer, () -> this.playerManager.getPlayers().remove(nPlayer))
        );
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(NeisisMinagePlugin.getInstance().getConfigManager().isSystemEnabled()) {
            this.playerManager.getNNPlayer(player.getName()).ifPresent(nPlayer -> {
                Block block = event.getBlock();
                Optional.ofNullable(NeisisMinagePlugin.getInstance().getPoints().get(block.getType())).ifPresent(gain -> {
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    this.playerManager.addProgression(nPlayer, gain.getXp(), gain.getMines());
                });
            });
        }
    }

    @EventHandler
    public void onUpgrade(NNPlayerUpgradeLevelEvent event){
        ItemStack pickaxe = event.getPlayer().getInventory().getItem(0);
        Optional.ofNullable(pickaxe).ifPresent(item -> {
            Map<Enchantment, Integer> upgrade = NeisisMinagePlugin.getInstance().getConfigManager().getUpgradePickaxe().get(event.getNewLevel());
            if(upgrade != null){
                for(Map.Entry<Enchantment, Integer> enchantment : upgrade.entrySet()){
                    pickaxe.addEnchantment(enchantment.getKey(), enchantment.getValue());
                }
            }
        });
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        if(inventory != null && event.getCurrentItem() != null){
            NeisisMinagePlugin.getInstance().getGuiManager().getGUIOpened(player).ifPresent(gui -> {
                if(gui.getInventory().equals(inventory)) {
                    event.setCancelled(true);
                    gui.onClick(player, event.getCurrentItem(), gui.getActions().get(event.getSlot()), event.getClick());
                }
            });
        }
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event){
        ItemStack itemStack = event.getItem();
        if(NeisisMinagePlugin.getInstance().getConfigManager().isSystemEnabled() && itemStack.getType().name().contains("_PICKAXE")){
            event.setCancelled(true);
        }
    }
}
