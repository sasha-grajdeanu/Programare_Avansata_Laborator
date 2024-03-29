package Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementDAOGenres implements DAO<Genres> {

    /**
     * implementation of create method
     *
     * @param genres a genre
     * @throws SQLException error
     */
    @Override
    public void create(Genres genres) throws SQLException {
        Connection con = DBCPDatabase.getConnection();
        Statement statement = con.createStatement();
        PreparedStatement check = con.prepareStatement("select * from genres where name = (?)");
        check.setString(1, genres.getName());
        ResultSet resultSet = check.executeQuery();
        if (!resultSet.next()) {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into genres (id, name) values (?, ?)")) {
                preparedStatement.setInt(1, genres.getId());
                preparedStatement.setString(2, genres.getName());
                int val = preparedStatement.executeUpdate();
                System.out.println(val);
            }
        } else {
            System.err.println("Gen deja inserat");
        }
        resultSet.close();
        con.close();
    }

    /**
     * implementation of the findByName method
     *
     * @param name the name of the genre
     * @return a genre
     * @throws SQLException error
     */
    @Override
    public Genres findByName(String name) throws SQLException {
        Genres genres = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from genres where name='" + name + "'")) {
            if (resultSet.next()) {
                genres = new Genres(resultSet.getInt("id"), resultSet.getString("name"));
            }
            resultSet.close();
            con.close();
            return genres;
        }
    }

    /**
     * implementation of the findById method
     *
     * @param id the id of the genre
     * @return a genre
     * @throws SQLException error
     */
    @Override
    public Genres findById(int id) throws SQLException {
        Genres genres = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from genres where id='" + id + "'")) {
            if (resultSet.next()) {
                genres = new Genres(resultSet.getInt("id"), resultSet.getString("name"));
            }
            resultSet.close();
            con.close();
            return genres;
        }
    }

    /**
     * implementation of the findAll method
     *
     * @return a list
     * @throws SQLException error
     */
    @Override
    public List<Genres> findAll() throws SQLException {
        List<Genres> genres = new ArrayList<>();
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from genres ")) {
            while (resultSet.next()) {
                genres.add(new Genres(resultSet.getInt("id"), resultSet.getString("name")));
            }
            resultSet.close();
            return genres;
        }
    }
}
