package Homework;

import java.sql.SQLException;

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
