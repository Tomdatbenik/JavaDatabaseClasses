package database.models.abstractclasses;

import database.models.abstractclasses.mysql.MySqlDatabase;
import database.models.data.DataCollection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author Tom van Kaathoven
 */
public abstract class Database {
    protected final Properties properties = new Properties();

    //Loads in properties file
    private void setUpProperties() {
        try {
            InputStream input = MySqlDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
        } catch (IOException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public Database() {
        setUpProperties();
    }

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("server"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }

    public DataCollection excecuteQuery(String query, List<Parameter> parameters) {
        return null;
    }

    public DataCollection excecuteQuery(String query, Parameter parameter) {
        return null;
    }

}
