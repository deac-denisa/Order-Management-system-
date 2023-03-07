package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConnectionFactory class establishes the link between the application and the database
 */
public class ConnectionFactory {


    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DBURL = "jdbc:postgresql://localhost:5432/assignment3";
    private static final String USER = "postgres";
    private static final String PASS = "dontworrybehappy";
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * The constructor takes the connection to the DB and places it into a Singleton object
     */
    public ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that establishes a connection with the database and gives an error message if it was ot successful
     * @return the created connection
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Method that gets the active connection
     * @return the active connection
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Method that closes the connection
     * @param connection - the connection that has to be closed
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * method that closes a statement
     * @param statement - the statement needed to be closed
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * method that closes a resultSet
     * @param resultSet - the resultSet needed to be closed
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}


