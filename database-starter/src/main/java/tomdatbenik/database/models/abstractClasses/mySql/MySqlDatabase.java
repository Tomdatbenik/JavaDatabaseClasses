package tomdatbenik.database.models.abstractClasses.mySql;

import tomdatbenik.database.models.abstractClasses.Database;
import tomdatbenik.database.models.abstractClasses.Parameter;
import tomdatbenik.database.models.data.Data;
import tomdatbenik.database.models.data.DataCollection;
import tomdatbenik.database.models.data.DataRow;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author Tom van Kaathoven
 */
public abstract class MySqlDatabase extends Database {
    public MySqlDatabase() {
        super();
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    @Override
    protected void setParameter(PreparedStatement cst, Parameter parameter) {
        try {
            if (parameter.getValue() instanceof String) {
                cst.setString(1, parameter.getValue().toString());
            } else if (parameter.getValue() instanceof Integer) {
                cst.setInt(1, (Integer) parameter.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setParameters(PreparedStatement cst, List<Parameter> parameters) {
        try {
            Integer parameterNumber = 1;

            for (Parameter parameter : parameters) {
                if (parameter.getValue() instanceof String) {
                    cst.setString(parameterNumber, parameter.getValue().toString());
                } else if (parameter.getValue() instanceof Integer) {
                    cst.setInt(parameterNumber, (Integer) parameter.getValue());
                }
                parameterNumber++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataCollection excecuteQuery(String query) {
        Connection connection = getConnection();
        DataCollection dataCollection = new DataCollection();

        try (PreparedStatement cst = connection.prepareStatement(query)) {
            try (ResultSet resultSet = cst.executeQuery()) {
                while (resultSet.next()) {
                    ResultSetMetaData metadata = resultSet.getMetaData();

                    for (int i = 1; i <= metadata.getColumnCount(); i++) {
                        DataRow dataRow = dataCollection.addRow();
                        Data data = new Data();

                        data.setValue(resultSet.getObject(i));
                        data.setName(metadata.getColumnName(i));

                        dataCollection.getDataList().add(data);
                        dataRow.getDataList().add(data);
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
            setParameter(cst, parameter);

            try (ResultSet resultSet = cst.executeQuery()) {
                while (resultSet.next()) {
                    DataRow dataRow = dataCollection.addRow();
                    ResultSetMetaData metadata = resultSet.getMetaData();

                    for (int i = 1; i <= metadata.getColumnCount(); i++) {
                        Data data = new Data();

                        data.setValue(resultSet.getObject(i));
                        data.setName(metadata.getColumnName(i));

                        dataCollection.getDataList().add(data);
                        dataRow.getDataList().add(data);
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
    public DataCollection excecuteQuery(String query, List<Parameter> parameters) {
        Connection connection = getConnection();
        DataCollection dataCollection = new DataCollection();

        try (PreparedStatement cst = connection.prepareStatement(query)) {
            try {

                setParameters(cst, parameters);

                try (ResultSet resultSet = cst.executeQuery()) {
                    while (resultSet.next()) {
                        DataRow dataRow = dataCollection.addRow();
                        ResultSetMetaData metadata = resultSet.getMetaData();

                        for (int i = 1; i <= metadata.getColumnCount(); i++) {
                            Data data = new Data();

                            data.setValue(resultSet.getObject(i));
                            data.setName(metadata.getColumnName(i));

                            dataCollection.getDataList().add(data);
                            dataRow.getDataList().add(data);
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
}
