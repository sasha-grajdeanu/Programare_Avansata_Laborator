package com.example.demo.JDBCProceduresAndFunction;

import com.example.demo.Entities.Camin;
import com.example.demo.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PreferenciesOfStudent {

    private DataSource dataSource;

    @Autowired
    public PreferenciesOfStudent(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method: return the list with the preferencies of students
     */
    public Map<Integer, List<Integer>> preferinteleStudentilor() throws SQLException {
        Map<Integer, List<Integer>> mapare = new HashMap<>();

        Connection con = dataSource.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from PREFERINTE_CAMIN")) {
            while (resultSet.next()) {
                List<Integer> camine = new ArrayList<>();
                camine.add(resultSet.getInt("preferinta_1"));
                camine.add(resultSet.getInt("preferinta_2"));
                camine.add(resultSet.getInt("preferinta_3"));
                camine.add(resultSet.getInt("preferinta_4"));
                camine.add(resultSet.getInt("preferinta_5"));
                mapare.put(resultSet.getInt("id_student"), camine);
            }
            resultSet.close();
            con.close();
            return mapare;
        }
    }
}
