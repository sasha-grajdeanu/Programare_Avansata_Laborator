package Bonus;

import Homework.Albums;
import Homework.DAO;
import Homework.DBCPDatabase;
import Homework.ImplementDAOAlbums;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * implementation of PlaylistDAO
 */
public class ImplementDAOPlaylist implements DAO<Playlist> {
    private ImplementDAOAlbums implementDAOAlbums = new ImplementDAOAlbums();

    /**
     * implementation of the create method
     *
     * @param playlist the playlist object
     * @throws SQLException error
     */
    @Override
    public void create(Playlist playlist) throws SQLException {
        Connection con = DBCPDatabase.getConnection();
        PreparedStatement check = con.prepareStatement("select * from playlist where nume_playlist = (?)");
        check.setString(1, playlist.getNameOfPlaylist());
        ResultSet resultSet = check.executeQuery();
        if (resultSet.next()) {
            System.err.println("Playlist deja inserat");
            resultSet.close();
        } else {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into playlist values ((?), (?), (?))")) {
                preparedStatement.setInt(1, playlist.getId());
                preparedStatement.setString(2, playlist.getNameOfPlaylist());
                preparedStatement.setDate(3, Date.valueOf(playlist.getCreationOfPlaylist()));
                int x = preparedStatement.executeUpdate();
                System.out.println(x);
                for(Albums albums: playlist.getAlbumsList()){
                    try (PreparedStatement preparedStatement2 = con.prepareStatement("insert into playlist_albums values ((?), (?))")) {
                        System.out.println(playlist.getId() + "  " + albums.getId());
                        preparedStatement2.setInt(1, playlist.getId());
                        preparedStatement2.setInt(2, albums.getId());
                        int x2 = preparedStatement2.executeUpdate();
                        System.out.println(x2);
                    }
                }
                resultSet.close();
            }
        }
        con.close();
    }

    /**
     * implementation of the findByName method
     *
     * @param name the name of the playlist
     * @return a playlist
     * @throws SQLException error
     */
    @Override
    public Playlist findByName(String name) throws SQLException {
        Playlist playlist = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from playlist where nume_playlist='" + name + "'");) {
            playlist= new Playlist();
            resultSet.next();
            playlist.setId(resultSet.getInt("id"));
            playlist.setNameOfPlaylist(resultSet.getString("nume_playlist"));
            playlist.setCreationOfPlaylist(resultSet.getDate("data_create").toLocalDate());
            try(Statement statementTwo = con.createStatement(); ResultSet resultSetTwo = statementTwo.executeQuery("select albums.title from albums join playlist_albums on playlist_albums.id_album = albums.id and playlist_albums.id_playlist =" + playlist.getId());){
                List<Albums> albumsList = new ArrayList<>();
                while(resultSetTwo.next()){
                    albumsList.add(implementDAOAlbums.findByName(resultSetTwo.getString(1)));
                }
                playlist.setAlbumsList(albumsList);
            }
            con.close();

        }
        return playlist;
    }

    /**
     * implementation of the findById method
     *
     * @param id id of the playlist
     * @return a playlist object
     * @throws SQLException error
     */
    @Override
    public Playlist findById(int id) throws SQLException {
        Playlist playlist = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from playlist where id='" + id + "'");) {
            playlist= new Playlist();
            resultSet.next();
            playlist.setId(resultSet.getInt("id"));
            playlist.setNameOfPlaylist(resultSet.getString("nume_playlist"));
            playlist.setCreationOfPlaylist(resultSet.getDate("data_creare").toLocalDate());
            try(Statement statementTwo = con.createStatement(); ResultSet resultSetTwo = statementTwo.executeQuery("select albums.title from albums join playlist_albums on playlist_albums.id_album = albums.id and playlist_albums.id_playlist =" + playlist.getId());){
                List<Albums> albumsList = new ArrayList<>();
                while(resultSetTwo.next()){
                    albumsList.add(implementDAOAlbums.findByName(resultSetTwo.getString(1)));
                }
                playlist.setAlbumsList(albumsList);
            }
            con.close();

        }
        return playlist;
    }

    /**
     * implementation of the findAll method
     *
     * @return a list
     * @throws SQLException error
     */
    @Override
    public List<Playlist> findAll() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from playlist ")) {
            while (resultSet.next()) {
                int s = resultSet.getInt("id");
                playlists.add(this.findById(s));
            }
        }
        con.close();
        return playlists;
    }
}
