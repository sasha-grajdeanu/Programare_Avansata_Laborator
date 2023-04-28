package Homework;

import Compulsory.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementDAOArtist implements ArtistDAO{

    @Override
    public void create(Artist artist) throws SQLException {
        Connection con = Database.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select name from artists where name = '" + artist.getName() + "'");
        if(!resultSet.next()){
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into artists (id, name) values (?, ?)")) {
                preparedStatement.setInt(1, artist.getId());
                preparedStatement.setString(2, artist.getName());
                int val = preparedStatement.executeUpdate();
                System.out.println(val);
            }
        }
        else{
            System.err.println("Artist deja inserat");
        }
    }

    @Override
    public Artist findByName(String name) throws SQLException {
        Artist artist = null;
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from artists where name='" + name + "'")) {
             if(resultSet.next()){
                 artist = new Artist(resultSet.getInt("id"), resultSet.getString("name"));
             }
             return artist;
        }
    }

    @Override
    public Artist findById(int id) throws SQLException {
        Artist artist = null;
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from artists where id='" + id + "'")) {
            if(resultSet.next()){
                artist = new Artist(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return artist;
        }
    }

    @Override
    public List<Artist> findAll() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from artists ")) {
            while(resultSet.next()){
                artists.add(new Artist(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return artists;
        }
    }
}
