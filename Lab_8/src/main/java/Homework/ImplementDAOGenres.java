package Homework;

import Compulsory.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementDAOGenres implements GenresDAO {

    @Override
    public void create(Genres genres) throws SQLException {
        Connection con = Database.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select name from genres where name = '" + genres.getName() + "'");
        if (!resultSet.next()) {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into genres (id, name) values (?, ?)")) {
                preparedStatement.setInt(1, genres.getId());
                preparedStatement.setString(2, genres.getName());
                int val = preparedStatement.executeUpdate();
                System.out.println(val);
            }
        }
        else{
            System.err.println("Gen deja inserat");
        }
    }

    @Override
    public Genres findByName(String name) throws SQLException {
        Genres genres = null;
        Connection con = Compulsory.Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from genres where name='" + name + "'")) {
            if (resultSet.next()) {
                genres = new Genres(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return genres;
        }
    }

    @Override
    public Genres findById(int id) throws SQLException {
        Genres genres = null;
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from genres where id='" + id + "'")) {
            if (resultSet.next()) {
                genres = new Genres(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return genres;
        }
    }

    @Override
    public List<Genres> findAll() throws SQLException {
        List<Genres> genres = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from genres ")) {
            while (resultSet.next()) {
                genres.add(new Genres(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return genres;
        }
    }
}
