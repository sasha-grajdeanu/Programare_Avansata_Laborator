package Homework;

import Compulsory.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * implementation of ArtistDAO
 */
public class ImplementDAOArtist implements DAO<Artist> {

    /**
     * implementation of create method
     *
     * @param artist
     * @throws SQLException
     */
    @Override
    public void create(Artist artist) throws SQLException {
        Connection con = DBCPDatabase.getConnection();
        PreparedStatement check = con.prepareStatement("select * from artists where name = ((?))");
        check.setString(1, artist.getName());
        ResultSet resultSet = check.executeQuery();
        if (!resultSet.next()) {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into artists (id, name) values (?, ?)")) {
                preparedStatement.setInt(1, artist.getId());
                preparedStatement.setString(2, artist.getName());
                int val = preparedStatement.executeUpdate();
                System.out.println(val);
            }
        } else {
            System.err.println("Artist deja inserat");
        }
        resultSet.close();
        con.close();
    }

    /**
     * implementation of the findByName method
     *
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public Artist findByName(String name) throws SQLException {
        Artist artist = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from artists where name='" + name + "'")) {
            if (resultSet.next()) {
                artist = new Artist(resultSet.getInt("id"), resultSet.getString("name"));
            }
            resultSet.close();
            con.close();
            return artist;
        }
    }

    /**
     * implementation of the findById method
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Artist findById(int id) throws SQLException {
        Artist artist = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from artists where id='" + id + "'")) {
            if (resultSet.next()) {
                artist = new Artist(resultSet.getInt("id"), resultSet.getString("name"));
            }
            resultSet.close();
            con.close();
            return artist;
        }
    }

    /**
     * implementation of the findAll method
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Artist> findAll() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from artists ")) {
            while (resultSet.next()) {
                artists.add(new Artist(resultSet.getInt("id"), resultSet.getString("name")));
            }
            resultSet.close();
            con.close();
            return artists;
        }
    }
}
