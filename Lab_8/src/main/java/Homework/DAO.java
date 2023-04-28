package Homework;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void create(T t) throws SQLException;
    T findByName(String name) throws SQLException;

    T findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;


}
