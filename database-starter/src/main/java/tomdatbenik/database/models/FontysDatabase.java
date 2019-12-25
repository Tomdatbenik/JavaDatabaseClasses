package tomdatbenik.database.models;



import tomdatbenik.database.models.abstractClasses.mySql.MySqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FontysDatabase extends MySqlDatabase {

    public FontysDatabase() {
        super();
    }

    @Override
    protected Connection getConnection() {
        try {
            String url = properties.getProperty("fontys-server");
            String username = properties.getProperty("fontys-username");
            String password = properties.getProperty("fontys-password");

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
