package Bonus;

import Homework.Albums;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * the Playlist model used for DAO
 */
@Data
@AllArgsConstructor
public class Playlist {
    private int id;
    private String nameOfPlaylist;
    private LocalDate creationOfPlaylist;
    private List<Albums> albumsList;

    public Playlist(){

    }
}
