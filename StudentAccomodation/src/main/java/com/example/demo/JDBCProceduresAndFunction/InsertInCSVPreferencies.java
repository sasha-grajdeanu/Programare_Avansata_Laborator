package com.example.demo.JDBCProceduresAndFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class InsertInCSVPreferencies {
    private DataSource dataSource;

    @Autowired
    public InsertInCSVPreferencies(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean callInsertPreferencies(){
        try(Connection connection = dataSource.getConnection()){
            System.out.println("intrat");
            CallableStatement statement  = connection.prepareCall("{call insert_preferinte}");
            System.out.println("scris");
            statement.execute();
            System.out.println("executat");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
