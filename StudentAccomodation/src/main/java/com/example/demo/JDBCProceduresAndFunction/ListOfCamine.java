package com.example.demo.JDBCProceduresAndFunction;

import com.example.demo.Entities.Camin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ListOfCamine {
    private DataSource dataSource;

    @Autowired
    public ListOfCamine(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method: return the list with the campus
     */
    public List<Camin> camineDate() throws SQLException {
        List<Camin> camine = new ArrayList<>();

        Connection con = dataSource.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select id_camin, CAPACITATE_ALOCATA_M, CAPACITATE_ALOCATA_F from CAMINE")) {
            while (resultSet.next()) {
                camine.add(new Camin(resultSet.getInt("id_camin"), resultSet.getInt("capacitate_alocata_f"), resultSet.getInt("capacitate_alocata_m")));
            }
            resultSet.close();
            con.close();
            return camine;
        }
    }

}
