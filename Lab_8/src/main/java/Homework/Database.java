package Homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "LAB_8";
    private static final String PASSWD = "student";
    private static Connection connection = null;

    private Database() {

    }

    /**
     * this method create a connection if this is null
     *
     * @return the connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    /**
     * this method create a connection to the database
     */
    private static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWD);
            ;
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    /**
     * this method close the connection to the database
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
