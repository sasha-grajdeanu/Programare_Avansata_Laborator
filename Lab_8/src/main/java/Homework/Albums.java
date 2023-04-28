package Homework;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Albums {
    private int release_year;
    private String title;
    private String artist;
    private String genre;

    public Albums(){

    }
}
