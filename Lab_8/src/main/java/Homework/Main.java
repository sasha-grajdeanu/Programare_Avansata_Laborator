package Homework;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        ImplementDAOAlbums impl = new ImplementDAOAlbums();
        Albums albums = null;
        try {
            albums = impl.findByName("Like a prayer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(albums);
        Artist maddona = new Artist(1, "Maddona");
        ImplementDAOArtist implem = new ImplementDAOArtist();
        try {
            implem.create(maddona);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Genres hipHop = new Genres(1, "Hip-Hop");
        ImplementDAOGenres implems = new ImplementDAOGenres();
        try {
            implems.create(hipHop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Database.getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Compulsory.Database.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
