package fr.will33.neisisminage.stockage;

import fr.will33.neisisminage.database.MySQLDatabase;
import fr.will33.neisisminage.exception.NNPlayerException;
import fr.will33.neisisminage.models.NNPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStockage {
    private final MySQLDatabase mySQLDatabase;
    private final String prefixTable;

    /**
     * @param mySQLDatabase Instance of the connection to database
     * @param prefixTable Prefix of plugin table
     */
    public PlayerStockage(MySQLDatabase mySQLDatabase, String prefixTable) {
        this.mySQLDatabase = mySQLDatabase;
        this.prefixTable = prefixTable;
        this.onCreateTable();
    }

    /**
     * Create the tables needed of the plugin
     */
    private void onCreateTable(){
        try{
            PreparedStatement preparedStatement = this.mySQLDatabase.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + this.prefixTable + "players (" +
                    "`pseudo` VARCHAR(32) PRIMARY KEY," +
                    "`points` INT UNSIGNED," +
                    "`level` INT UNSIGNED," +
                    "`xp` INT UNSIGNED" +
                    ")");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException err){
            err.printStackTrace();
        }
    }

    /**
     * Load player data
     * @param player Instance of the player
     * @throws NNPlayerException
     */
    public void loadPlayer(NNPlayer player) throws NNPlayerException {
        try{
            PreparedStatement preparedStatement = this.mySQLDatabase.getConnection().prepareStatement("SELECT * FROM " + this.prefixTable + "players WHERE pseudo = ?");
            preparedStatement.setString(1, player.getPlayerName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                player.setPoints(resultSet.getInt("points"));
                player.setLevel(resultSet.getInt("level"));
                player.setTotalXP(resultSet.getInt("xp"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException err){
            err.printStackTrace();
            throw new NNPlayerException(err.getMessage());
        }
    }

    /**
     * Save player data
     * @param player
     */
    public void savePlayer(NNPlayer player) throws NNPlayerException{
        try{
            PreparedStatement preparedStatement = this.mySQLDatabase.getConnection().prepareStatement("INSERT INTO " + this.prefixTable + "players (pseudo, points, level, xp) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE points = ?, level = ?, xp = ?");
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.setInt(2, player.getPoints());
            preparedStatement.setInt(3, player.getLevel());
            preparedStatement.setInt(4, player.getTotalXP());
            preparedStatement.setInt(5, player.getPoints());
            preparedStatement.setInt(6, player.getLevel());
            preparedStatement.setInt(7, player.getTotalXP());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException err){
            err.printStackTrace();
            throw new NNPlayerException(err.getMessage());
        }
    }

}
