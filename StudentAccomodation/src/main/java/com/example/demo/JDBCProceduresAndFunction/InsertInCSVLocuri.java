package com.example.demo.JDBCProceduresAndFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class InsertInCSVLocuri {
    private DataSource dataSource;

    @Autowired
    public InsertInCSVLocuri(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method: call a plsql procedure which insert in database the campus
     */
    public boolean callInsertInCamine() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("intrat");
            CallableStatement statement = connection.prepareCall("{call insert_in_camine()}");
            System.out.println("scris");
            statement.execute();
            System.out.println("executat");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
