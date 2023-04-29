package Homework;

import java.sql.SQLException;
import java.util.List;

/**
 * generic interface for DAO object
 *
 * @param <T>
 */
public interface DAO<T> {

    /**
     * insert in database a T object
     *
     * @param t
     * @throws SQLException
     */
    void create(T t) throws SQLException;

    /**
     * find a T object in database, using the name
     *
     * @param name
     * @return
     * @throws SQLException
     */
    T findByName(String name) throws SQLException;

    /**
     * find a T object in database, using the id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    T findById(int id) throws SQLException;

    /**
     * return all T object from the database
     *
     * @return
     * @throws SQLException
     */
    List<T> findAll() throws SQLException;


}
