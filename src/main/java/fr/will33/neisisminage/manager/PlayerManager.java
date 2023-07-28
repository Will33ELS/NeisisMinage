package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.IConfigManager;
import fr.will33.neisisminage.event.NNPlayerUpgradeLevelEvent;
import fr.will33.neisisminage.exception.NNPlayerException;
import fr.will33.neisisminage.models.NNPlayer;
import fr.will33.neisisminage.stockage.PlayerStockage;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerManager {

    private final PlayerStockage playerStockage;
    private final IConfigManager configManager;
    private final List<NNPlayer> players = new ArrayList<>();

    public PlayerManager(IConfigManager configManager, PlayerStockage playerStockage) {
        this.configManager = configManager;
        this.playerStockage = playerStockage;
    }

    /**
     * Load player data
     * @param player Instance of the player
     * @param callback Action to be taken after data loading
     */
    public void loadPlayer(NNPlayer player, Runnable callback){
        Bukkit.getScheduler().runTaskAsynchronously(NeisisMinagePlugin.getInstance(), () -> {
            try {
                this.playerStockage.loadPlayer(player);
            } catch (NNPlayerException e) {
                NeisisMinagePlugin.getInstance().getLogger().warning("Un incident à eu lieu au chargement des données de " + player.getPoints());
            }
            Bukkit.getScheduler().runTask(NeisisMinagePlugin.getInstance(), callback);
        });
    }

    /**
     * Save player data
     * @param player Instance of the player
     * @param callback Action to be taken after data saving
     */
    public void savePlayer(NNPlayer player, Runnable callback){
        Bukkit.getScheduler().runTaskAsynchronously(NeisisMinagePlugin.getInstance(), () -> {
            try {
                this.playerStockage.savePlayer(player);
            } catch (NNPlayerException e) {
                NeisisMinagePlugin.getInstance().getLogger().warning("Un incident à eu lieu à la sauvegarde des données de " + player.getPlayerName());
            }
            if(callback != null) {
                Bukkit.getScheduler().runTask(NeisisMinagePlugin.getInstance(), callback);
            }
        });
    }

    /**
     * Add progression
     * @param nPlayer Instance of the player
     * @param xp XP to add
     * @param points Points to add
     */
    public void addProgression(NNPlayer nPlayer, int xp, int points){
        int oldLevel = nPlayer.getLevel();
        nPlayer.addProgression(this, xp, points);
        int newLevel = nPlayer.getLevel();
        if(oldLevel != newLevel){
            Player player = Bukkit.getPlayer(nPlayer.getPlayerName());
            new NNPlayerUpgradeLevelEvent(player, newLevel).callEvent();
        }
    }

    /**
     * Collect the number of xp needed for the level
     * @param level Level to check
     * @return
     */
    public Integer getXPRequire(int level){
        return level == 1 ? this.configManager.getXpRequiredPerLevel() : getXPRequire(level - 1) + this.configManager.getXpRequiredPerLevel() * level;
    }

    /**
     * Get {@link NNPlayer} instance
     * @param playerName Name of the player
     * @return
     */
    public Optional<NNPlayer> getNNPlayer(String playerName){
        return this.players.stream().filter(player -> player.getPlayerName().equals(playerName)).findFirst();
    }

    /**
     * Get offline {@link NNPlayer}
     * @param playerName Name of the offline player
     * @return
     * @throws NNPlayerException
     */
    public NNPlayer getOfflineNNPlayer(String playerName) throws NNPlayerException {
        if(this.getNNPlayer(playerName).isPresent()) return this.getNNPlayer(playerName).get();
        NNPlayer nPlayer = new NNPlayer(playerName);
        this.playerStockage.loadPlayer(nPlayer);
        return nPlayer;
    }

    /**
     * Get all players instance
     * @return
     */
    public List<NNPlayer> getPlayers() {
        return players;
    }

    /**
     * Get {@link PlayerStockage} instance
     * @return
     */
    public PlayerStockage getPlayerStockage() {
        return playerStockage;
    }
}
