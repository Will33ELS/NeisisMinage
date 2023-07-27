package fr.will33.neisisminage.listener;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.manager.ConfigManager;
import fr.will33.neisisminage.manager.PlayerManager;
import fr.will33.neisisminage.models.NNPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class PlayerListener implements Listener {

    private final PlayerManager playerManager = NeisisMinagePlugin.getInstance().getPlayerManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        NNPlayer nPlayer = new NNPlayer(player.getUniqueId());
        this.playerManager.loadPlayer(nPlayer, () -> {
            if(NeisisMinagePlugin.getInstance().getConfigManager().isSystemEnabled() && player.getInventory().getItem(0).getType() != Material.AIR) {
                player.getInventory().setItem(0, NeisisMinagePlugin.getInstance().getConfigManager().getPickaxe());
            }
            this.playerManager.getPlayers().add(nPlayer);
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        this.playerManager.getNNPlayer(player.getUniqueId()).ifPresent(nPlayer ->
                this.playerManager.savePlayer(nPlayer, () -> this.playerManager.getPlayers().remove(nPlayer))
        );
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(NeisisMinagePlugin.getInstance().getConfigManager().isSystemEnabled()) {
            this.playerManager.getNNPlayer(player.getUniqueId()).ifPresent(nPlayer -> {
                Block block = event.getBlock();
                Optional.ofNullable(NeisisMinagePlugin.getInstance().getPoints().get(block.getType())).ifPresent(gain -> {
                    event.getBlock().getDrops().clear();
                    nPlayer.addProgression(this.playerManager, gain.getXp(), gain.getMines());
                });
            });
        }
    }

}
