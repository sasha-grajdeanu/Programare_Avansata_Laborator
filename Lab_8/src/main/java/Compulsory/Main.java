package Compulsory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    /**
     * In main, we create some instance and try to insert in database
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            artists.create("Maddona");
            Database.getConnection().commit();
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk");
            albums.create(1989, "Like a prayer", "Maddona", "Pop,Rock");
            Database.getConnection().commit();

            Statement stmt = Database.getConnection().createStatement();
            String sql = "SELECT * FROM albums";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int an = resultSet.getInt("release_year");
                String title = resultSet.getString("title");
                String theArtist = artists.findById(resultSet.getInt("artist"));
                String genre = genres.findById(resultSet.getInt("genre"));
                System.out.println(id + " | " + an + " | " + title + " | " + theArtist + " | " + genre);
            }

            System.out.println("Finish");
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }
}
