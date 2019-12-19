package database.models.abstractclasses.mysql;

import database.models.abstractclasses.Database;
import database.models.abstractclasses.Parameter;
import database.models.data.Data;
import database.models.data.DataCollection;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MySqlDatabase extends Database {
    public MySqlDatabase() {
        super();
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    @Override
    public DataCollection excecuteQuery(String query, List<Parameter> parameters) {
        Connection connection = getConnection();
        DataCollection dataCollection = new DataCollection();

        try (PreparedStatement cst = connection.prepareStatement(query)) {
            try {
                int parameterNumber = 1;

                for (Parameter parameter : parameters) {
                    if (parameter.getValue() instanceof String) {
                        cst.setString(parameterNumber, parameter.getValue().toString());
                    } else if (parameter.getValue() instanceof Integer) {
                        cst.setInt(parameterNumber, (Integer) parameter.getValue());
                    }

                    parameterNumber++;
                }

                try (ResultSet resultSet = cst.executeQuery()) {
                    while (resultSet.next()) {
                        ResultSetMetaData metadata = resultSet.getMetaData();

                        for (int i = 1; i != metadata.getColumnCount(); i++) {
                            Data data = new Data();

                            data.setValue(resultSet.getObject(i));
                            data.setName(metadata.getColumnName(i));

                            dataCollection.dataList.add(data);
                        }
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(MySqlDatabase.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        } catch (SQLException e) {
            Logger.getLogger(MySqlDatabase.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(MySqlDatabase.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return dataCollection;
    }


    @Override
    public DataCollection excecuteQuery(String query, Parameter parameter) {
        Connection connection = getConnection();
        DataCollection dataCollection = new DataCollection();

        try (PreparedStatement cst = connection.prepareStatement(query)) {
            if (parameter.getValue() instanceof String) {
                cst.setString(1, parameter.getValue().toString());
            } else if (parameter.getValue() instanceof Integer) {
                cst.setInt(1, (Integer) parameter.getValue());
            }

            try (ResultSet resultSet = cst.executeQuery()) {
                while (resultSet.next()) {
                    ResultSetMetaData metadata = resultSet.getMetaData();

                    for (int i = 1; i != metadata.getColumnCount(); i++) {
                        Data data = new Data();

                        data.setValue(resultSet.getObject(i));
                        data.setName(metadata.getColumnName(i));

                        dataCollection.dataList.add(data);
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(MySqlDatabase.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        } catch (SQLException e) {
            Logger.getLogger(MySqlDatabase.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(MySqlDatabase.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return dataCollection;
    }
}
