import java.sql.*;

public class AlbumDAO {
    private ArtistDAO artistDAO = new ArtistDAO();
    private GenreDAO genreDAO = new GenreDAO();

    /**
     * this method insert in database an album
     * @param an
     * @param nameOfAlbum
     * @param artist
     * @param genre
     * @throws SQLException
     */
    public void create(int an, String nameOfAlbum, String artist, String genre) throws SQLException {
        Connection con = Database.getConnection();
        String[] genres = genre.split(",");
        for (String string : genres) {
            System.out.println(string);
        }
        for (int i = 0; i < genres.length; i++) {
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into albums  values (?, ?, ?, ?, ?)");) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, an);
                preparedStatement.setString(3, nameOfAlbum);
                preparedStatement.setInt(4, artistDAO.findByName(artist));
                preparedStatement.setInt(5, genreDAO.findByName(genres[i]));
                preparedStatement.executeUpdate();
            }
        }
    }

    /**
     * this method search the id of the given name in the albums tabel
     * @param name
     * @return
     * @throws SQLException
     */
    public int findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select id from albums where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }

    /**
     * this method returns the name of the album with the given id
     * @param id
     * @return
     * @throws SQLException
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select name from albums where id='" + id + "'")) {
            return resultSet.next() ? resultSet.getString(1) : null;
        }
    }
}
