package com.example.demo.JDBCProceduresAndFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class CreateList {
    private DataSource dataSource;

    @Autowired
    public CreateList(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method: generate a csv file using a plsql procedure
     */
    public boolean callLista() {
        try (Connection connection = dataSource.getConnection()) {
            CallableStatement statement = connection.prepareCall("{call lista_repartizare()}");
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
