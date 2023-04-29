package Homework;

import Compulsory.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementDAOArtist implements ArtistDAO{

    @Override
    public void create(Artist artist) throws SQLException {
        Connection con = DBCPDatabase.getConnection();
        PreparedStatement check = con.prepareStatement("select * from artists where name = ((?))");
        check.setString(1, artist.getName());
        ResultSet resultSet = check.executeQuery();
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
        resultSet.close();
        con.close();
    }

    @Override
    public Artist findByName(String name) throws SQLException {
        Artist artist = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from artists where name='" + name + "'")) {
             if(resultSet.next()){
                 artist = new Artist(resultSet.getInt("id"), resultSet.getString("name"));
             }
            resultSet.close();
             con.close();
             return artist;
        }
    }

    @Override
    public Artist findById(int id) throws SQLException {
        Artist artist = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from artists where id='" + id + "'")) {
            if(resultSet.next()){
                artist = new Artist(resultSet.getInt("id"), resultSet.getString("name"));
            }
            resultSet.close();
            con.close();
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
            resultSet.close();
            con.close();
            return artists;
        }
    }
}
