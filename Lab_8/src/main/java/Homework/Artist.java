package Homework;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * the Artist model used for DAO
 */
@Data
@AllArgsConstructor
public class Artist {
    private int id;
    private String name;
}
