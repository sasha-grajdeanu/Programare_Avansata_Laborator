package Homework;

import Bonus.ImplementDAOPlaylist;
import Bonus.Playlist;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    /**
     * testing if the function work
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ToolToInsertCSV tool = new ToolToInsertCSV();
            tool.execute();
            ImplementDAOAlbums implementDAOAlbums = new ImplementDAOAlbums();
            System.out.println(implementDAOAlbums.findAll());
            Playlist no_1 = new Playlist(1, "Ce propune Rolling Stone", LocalDate.of(2015, 1, 1), implementDAOAlbums.findAll());
            ImplementDAOPlaylist implementDAOPlaylist = new ImplementDAOPlaylist();
            implementDAOPlaylist.create(no_1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            DBCPDatabase.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
