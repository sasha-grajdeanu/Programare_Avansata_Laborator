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

    public boolean callLista() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("intrat");
            CallableStatement statement = connection.prepareCall("{call lista_repartizare}");
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
