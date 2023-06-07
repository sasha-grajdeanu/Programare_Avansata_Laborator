package com.example.demo.JDBCProceduresAndFunction;

import com.example.demo.Entities.Student;
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
public class ListOfStudent {
    private DataSource dataSource;

    @Autowired
    public ListOfStudent(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method: return the list with the students
     */
    public List<Student> studentiInscrisi() throws SQLException {
        List<Student> students = new ArrayList<>();

        Connection con = dataSource.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select STUDENT.id_student, an, gen, punctaj from student join preferinte_camin on student.id_student=preferinte_camin.id_student")) {
            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt("id_student"), resultSet.getInt("punctaj"), resultSet.getString("gen"), resultSet.getInt("an")));
            }
            resultSet.close();
            con.close();
            return students;
        }
    }
}
