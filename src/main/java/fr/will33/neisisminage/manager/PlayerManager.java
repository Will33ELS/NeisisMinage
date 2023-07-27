package fr.will33.neisisminage.manager;

import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.api.IConfigManager;
import fr.will33.neisisminage.event.NNPlayerUpgradeLevelEvent;
import fr.will33.neisisminage.exception.NNPlayerException;
import fr.will33.neisisminage.models.NNPlayer;
import fr.will33.neisisminage.stockage.PlayerStockage;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                NeisisMinagePlugin.getInstance().getLogger().warning("Un incident à eu lieu au chargement des données de l'UUID " + player.getPlayerUUID());
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
                NeisisMinagePlugin.getInstance().getLogger().warning("Un incident à eu lieu à la sauvegarde des données de l'UUID " + player.getPlayerUUID());
            }
            if(callback != null) {
                Bukkit.getScheduler().runTask(NeisisMinagePlugin.getInstance(), callback);
            }
        });
    }

    /**
     * Add progression
     * @param player Instance of the player
     * @param xp XP to add
     * @param points Points to add
     */
    public void addProgression(NNPlayer player, int xp, int points){
        int oldLevel = player.getLevel();
        player.addProgression(this, xp, points);
        int newLevel = player.getLevel();
        if(oldLevel != newLevel){
            new NNPlayerUpgradeLevelEvent(Bukkit.getPlayer(player.getPlayerUUID()), newLevel).callEvent();
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
     * @param playerUUID UUID of the player
     * @return
     */
    public Optional<NNPlayer> getNNPlayer(UUID playerUUID){
        return this.players.stream().filter(player -> player.getPlayerUUID().equals(playerUUID)).findFirst();
    }

    /**
     * Get offline {@link NNPlayer}
     * @param playerUUID UUID of the offline player
     * @return
     * @throws NNPlayerException
     */
    public NNPlayer getOfflineNNPlayer(UUID playerUUID) throws NNPlayerException {
        if(this.getNNPlayer(playerUUID).isPresent()) return this.getNNPlayer(playerUUID).get();
        NNPlayer nPlayer = new NNPlayer(playerUUID);
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
