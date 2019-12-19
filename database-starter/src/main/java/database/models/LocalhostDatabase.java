package database.models;

import database.models.abstractclasses.mysql.MySqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalhostDatabase extends MySqlDatabase {

    public LocalhostDatabase() {
        super();
    }

    @Override
    protected Connection getConnection() {
        try {
            String url = properties.getProperty("localhost-server");
            String username = properties.getProperty("localhost-username");
            String password = properties.getProperty("localhost-password");

            return DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }
}
