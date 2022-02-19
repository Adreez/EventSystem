package sk.adr3ez.eventsystem.utils;

import sk.adr3ez.eventsystem.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private final String host = Main.config.get().getString("MySQL.host");
    private final String port = Main.config.get().getString("MySQL.port");
    private final String database = Main.config.get().getString("MySQL.database");
    private final String username = Main.config.get().getString("MySQL.username");
    private final String password = Main.config.get().getString("MySQL.password");
    private final Boolean useSSL = Main.config.get().getBoolean("MySQL.useSSL");

    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }
    public void connect() throws SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL, username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
