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
     * @param albums the album object
     * @throws SQLException error
     */
    @Override
    public void create(Albums albums) throws SQLException {
        Connection con = DBCPDatabase.getConnection();
        String[] genres = albums.getGenre().split(", ");
        List<String> genresList = new ArrayList<>();
        Collections.addAll(genresList, genres);
        PreparedStatement check = con.prepareStatement("select * from albums where title = (?) and artist = (?)");
        check.setString(1, albums.getTitle());
        check.setInt(2, implementDAOArtist.findByName(albums.getArtist()).getId());
        ResultSet resultSet = check.executeQuery();
        if (resultSet.next()) {
            System.err.println("Album deja inserat");
            resultSet.close();
        } else {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into albums values ((?), (?), (?), (?))")) {
                preparedStatement.setInt(1, albums.getId());
                preparedStatement.setInt(2, albums.getRelease_year());
                preparedStatement.setString(3, albums.getTitle());
                preparedStatement.setInt(4, implementDAOArtist.findByName(albums.getArtist()).getId());
                int x = preparedStatement.executeUpdate();
                System.out.println(x);
                for(String string: genresList){
                    try (PreparedStatement preparedStatement2 = con.prepareStatement("insert into junction_table values ((?), (?))")) {
                        preparedStatement2.setInt(1, albums.getId());
                        preparedStatement2.setInt(2, implementDAOGenres.findByName(string).getId());
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
     * @param name the name of the album
     * @return an album
     * @throws SQLException error
     */
    @Override
    public Albums findByName(String name) throws SQLException {
        Albums albums = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from albums where title='" + name + "'");) {
            albums = new Albums();
            resultSet.next();
            albums.setId(resultSet.getInt("id"));
            albums.setRelease_year(resultSet.getInt("release_year"));
            albums.setTitle(resultSet.getString("title"));
            albums.setArtist(implementDAOArtist.findById(resultSet.getInt("artist")).getName());
            try(Statement statementTwo = con.createStatement(); ResultSet resultSetTwo = statementTwo.executeQuery("select genres.name from genres join junction_table on junction_table.id_gen = genres.id and junction_table.id_album =" + albums.getId());){
                StringBuilder genres = new StringBuilder();
                resultSetTwo.next();
                genres.append(resultSetTwo.getString(1));
                while(resultSetTwo.next()){
                    genres.append("," + resultSetTwo.getString(1));
                }
                albums.setGenre(genres.toString());
            }
            con.close();

        }
        return albums;
    }

    /**
     * implementation of the findById method
     *
     * @param id the id of the album
     * @return an album
     * @throws SQLException error
     */
    @Override
    public Albums findById(int id) throws SQLException {
        Albums albums = null;
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("select * from albums where id ='" + id + "'");) {
            albums = new Albums();
            resultSet.next();
            albums.setId(resultSet.getInt("id"));
            albums.setRelease_year(resultSet.getInt("release_year"));
            albums.setTitle(resultSet.getString("title"));
            albums.setArtist(implementDAOArtist.findById(resultSet.getInt("artist")).getName());
            try(Statement statementTwo = con.createStatement(); ResultSet resultSetTwo = statementTwo.executeQuery("select genres.name from genres join junction_table on junction_table.id_gen = genres.id and junction_table.id_album =" + albums.getId());){
                StringBuilder genres = new StringBuilder();
                resultSetTwo.next();
                genres.append(resultSetTwo.getString(1));
                while(resultSetTwo.next()){
                    genres.append(", " + resultSetTwo.getString(1));
                }
                albums.setGenre(genres.toString());
            }
            con.close();

        }
        return albums;
    }

    /**
     * implementation of the findAll method
     *
     * @return a list
     * @throws SQLException error
     */
    @Override
    public List<Albums> findAll() throws SQLException {
        List<Albums> albumsList = new ArrayList<>();
        Connection con = DBCPDatabase.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from albums ")) {
            while (resultSet.next()) {
                int s = resultSet.getInt("id");
                albumsList.add(this.findById(s));
            }
        }
        con.close();
        return albumsList;
    }
}
