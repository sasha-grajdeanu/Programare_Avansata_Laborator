package Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * implementation of AlbumsDAO
 */
public class ImplementDAOAlbums implements DAO<Albums> {
    private ImplementDAOArtist implementDAOArtist = new ImplementDAOArtist();
    private ImplementDAOGenres implementDAOGenres = new ImplementDAOGenres();

    /**
     * implementation of create method
     *
     * @param albums
     * @throws SQLException
     */
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
            if (resultSet.next()) {
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

    /**
     * implementation of the findByName method
     *
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public Albums findByName(String name) throws SQLException {
        Albums albums = null;
        Connection con = DBCPDatabase.getConnection();
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

    /**
     * implementation of the findById method
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Albums findById(int id) throws SQLException {
        Albums albums = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from albums where id='" + id + "'")) {
            if (resultSet.next()) {
                albums = new Albums();
                albums.setRelease_year(resultSet.getInt("release_year"));
                albums.setTitle(resultSet.getString("title"));
                albums.setArtist(implementDAOArtist.findById(resultSet.getInt("artist")).getName());
                albums.setGenre(implementDAOGenres.findById(resultSet.getInt("genre")).getName());
            }
            resultSet.close();
            con.close();
            return albums;
        }
    }

    /**
     * implementation of the findAll method
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Albums> findAll() throws SQLException {
        List<Albums> albumsList = new ArrayList<>();
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from albums ")) {
            while (resultSet.next()) {
                String s = resultSet.getString("title");
                String g = implementDAOGenres.findById(resultSet.getInt("genre")).getName();
                boolean exist = false;
                for (int i = 0; i < albumsList.size(); i++) {
                    if (albumsList.get(i).getGenre().equals(s)) {
                        StringBuilder stringBuilder = new StringBuilder(albumsList.get(i).getGenre());
                        stringBuilder.append(", ");
                        stringBuilder.append(g);
                        albumsList.get(i).setGenre(stringBuilder.toString());
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    albumsList.add(new Albums(resultSet.getInt("release_year"), resultSet.getString("title"), implementDAOArtist.findById(resultSet.getInt("artist")).getName(), implementDAOGenres.findById(resultSet.getInt("genre")).getName()));
                }
            }
            resultSet.close();
            return albumsList;
        }
    }
}
