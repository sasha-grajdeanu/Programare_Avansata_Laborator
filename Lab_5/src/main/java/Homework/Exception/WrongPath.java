package Homework.Exception;

import java.io.IOException;

/**
 * this exception is called if the path isn`t correct
 */
public class WrongPath extends Exception {
    public WrongPath(IOException e) {
        super("Path gresit: "+e.getMessage());
    }
}
