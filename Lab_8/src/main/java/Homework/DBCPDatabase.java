package Homework;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDatabase {
    private static BasicDataSource dataSource = new BasicDataSource();
    static {
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("LAB_8");
        dataSource.setPassword("student");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setAutoCommitOnReturn(false);
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    private DBCPDatabase(){

    }
}
