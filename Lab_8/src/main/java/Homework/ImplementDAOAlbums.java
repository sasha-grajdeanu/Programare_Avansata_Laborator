package Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImplementDAOAlbums implements AlbumsDAO {
    private ImplementDAOArtist implementDAOArtist = new ImplementDAOArtist();
    private ImplementDAOGenres implementDAOGenres = new ImplementDAOGenres();

    @Override
    public void create(Albums albums) throws SQLException {
        Connection con = DBCPDatabase.getConnection();
        String[] genres = albums.getGenre().split(", ");
        List<String> genresList = new ArrayList<>();
        Collections.addAll(genresList, genres);
        for (String string : genres) {
            PreparedStatement check = con.prepareStatement("select * from albums where title = (?) and artist = (?) and genre = (?)");
            check.setString(1, albums.getTitle());
            check.setInt(2, implementDAOArtist.findByName(albums.getArtist()).getId());
            check.setInt(3, implementDAOGenres.findByName(string).getId());
            ResultSet resultSet = check.executeQuery();
            if(resultSet.next()){
                genresList.remove(string);
            }
            resultSet.close();
        }
        if (genresList.isEmpty()) {
            System.err.println("Album deja inserat");
        } else {
            for (int i = 0; i < genresList.size(); i++) {
                try (PreparedStatement preparedStatement = con.prepareStatement("insert into albums values ((?), (?), (?), (?), (?))")) {
                    preparedStatement.setInt(1, 1);
                    preparedStatement.setInt(2, albums.getRelease_year());
                    preparedStatement.setString(3, albums.getTitle());
                    preparedStatement.setInt(4, implementDAOArtist.findByName(albums.getArtist()).getId());
                    preparedStatement.setInt(5, implementDAOGenres.findByName(genresList.get(i)).getId());
                    int x = preparedStatement.executeUpdate();
                    System.out.println(x);
                }
            }
        }
        con.close();
    }

    @Override
    public Albums findByName(String name) throws SQLException {
        Albums albums = null;
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from albums where title='" + name + "'");) {
            albums = new Albums();
            resultSet.next();
            albums.setRelease_year(resultSet.getInt("release_year"));
            albums.setTitle(resultSet.getString("title"));
            albums.setArtist(implementDAOArtist.findById(resultSet.getInt("artist")).getName());
            StringBuilder genres = new StringBuilder(implementDAOGenres.findById(resultSet.getInt("genre")).getName());
            while (resultSet.next()) {
                genres.append(",");
                genres.append(implementDAOGenres.findById(resultSet.getInt("genre")).getName());
            }
            albums.setGenre(genres.toString());
            con.close();

        }
        return albums;
    }

    @Override
    public Albums findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Albums> findAll() throws SQLException {
        return null;
    }
}
