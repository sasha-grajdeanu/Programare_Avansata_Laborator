package Homework;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * a tool for inserting data from csv file in database
 */
public class ToolToInsertCSV {
    private ImplementDAOAlbums implementDAOAlbums = new ImplementDAOAlbums();
    private ImplementDAOGenres implementDAOGenres = new ImplementDAOGenres();
    private ImplementDAOArtist implementDAOArtist = new ImplementDAOArtist();
    List<Albums> albumsList = new ArrayList<>();

    /**
     * method for collecting data from the csv file and responsible for inserting artists, genres and albums in database
     *
     * @throws SQLException
     */
    public void execute() throws SQLException {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C:/Users/alexa/Desktop/albumlist.csv"));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[3].contains("'")) {
                    StringBuilder stringBuilder = new StringBuilder(nextLine[3]);
                    stringBuilder.replace(stringBuilder.indexOf("'"), stringBuilder.indexOf("'") + 1, "`");
                    nextLine[3] = stringBuilder.toString();
                    System.out.println(nextLine[3]);
                }
                Albums albums = new Albums(Integer.parseInt(nextLine[1]), nextLine[2], nextLine[3], nextLine[4]);
                System.out.println(albums);
                albumsList.add(albums);
                if (implementDAOArtist.findByName(albums.getArtist()) == null) {
                    Artist artist = new Artist(1, albums.getArtist());
                    implementDAOArtist.create(artist);
                } else {
                    System.out.println("Existent");
                }

                if (implementDAOGenres.findByName(albums.getGenre()) == null) {
                    String[] genresList = albums.getGenre().split(", ");
                    for (int i = 0; i < genresList.length; i++) {
                        Genres genres = new Genres(1, genresList[i]);
                        implementDAOGenres.create(genres);
                    }
                } else {
                    System.out.println("Gen Existent");
                }
                System.out.println("ADAUGAT");
            }
            for (int i = 0; i < albumsList.size(); i++) {
                implementDAOAlbums.create(albumsList.get(i));
                System.out.println("PUS");
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
