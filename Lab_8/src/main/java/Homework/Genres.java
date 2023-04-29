package Homework;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * the Genres model used for DAO
 */
@Data
@AllArgsConstructor
public class Genres {
    private int id;
    private String name;
}
