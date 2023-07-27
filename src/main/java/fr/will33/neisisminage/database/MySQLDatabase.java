package fr.will33.neisisminage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase {

    private final String host, name, username, password;
    private final int port;
    private Connection connection;

    /**
     * @param host Host of the database
     * @param port Port of the database
     * @param name Name of the database
     * @param username Username of the database
     * @param password Password of the database
     */
    public MySQLDatabase(String host, int port, String name, String username, String password) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Connect to the database
     */
    public void setup() {
        if (!this.isConnected()) {
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.name + "?autoreconnect=true&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false", this.username, this.password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieve connection
     * @return
     */
    public Connection getConnection() {
        if (!this.isConnected()) {
            try {
                this.shutdownDataSource();
            }catch (Exception err){
                err.printStackTrace();
            }
            this.setup();
        }
        return connection;
    }

    /**
     * Close connection
     * @throws Exception
     */
    public void shutdownDataSource() throws Exception{
        if(this.connection == null) return;
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new Exception("Erreur: "+e.getMessage());
        }
    }

    /**
     * Check if connection is valid
     * @return
     */
    private boolean isConnected() {
        try {
            return (this.connection != null) && (!this.connection.isClosed()) && this.connection.isValid(1000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
