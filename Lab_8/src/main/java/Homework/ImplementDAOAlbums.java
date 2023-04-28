package Homework;

import java.sql.*;
import java.util.List;

public class ImplementDAOAlbums implements AlbumsDAO{
    ImplementDAOArtist implementDAOArtist = new ImplementDAOArtist();
    ImplementDAOGenres implementDAOGenres = new ImplementDAOGenres();
    @Override
    public void create(Albums albums) throws SQLException {
        Connection con = Database.getConnection();
        String[] genres = albums.getGenre().split(",");
        for (String string : genres) {

        }
        for (int i = 0; i < genres.length; i++) {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into albums  values (?, ?, ?, ?, ?)");) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, albums.getRelease_year());
                preparedStatement.setString(3, albums.getTitle());
                preparedStatement.setInt(4, implementDAOArtist.findByName(albums.getArtist()).getId());
                preparedStatement.setInt(5, implementDAOGenres.findByName(genres[i]).getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public Albums findByName(String name) throws SQLException {
        Albums albums = null;
        Connection con  = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from albums where title='" + name + "'")) {
             albums = new Albums();
             resultSet.next();
            albums.setRelease_year(resultSet.getInt("release_year"));
            albums.setTitle(resultSet.getString("title"));
            albums.setArtist(implementDAOArtist.findById(resultSet.getInt("artist")).getName());
            StringBuilder genres = new StringBuilder(implementDAOGenres.findById(resultSet.getInt("genre")).getName());
            while(resultSet.next()){
                genres.append(",");
                genres.append(implementDAOGenres.findById(resultSet.getInt("genre")).getName());
            }
            albums.setGenre(genres.toString());

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
