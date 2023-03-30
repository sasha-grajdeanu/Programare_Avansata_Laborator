package Homework.Exception;

import java.io.IOException;

public class WrongPath extends Exception {
    public WrongPath(IOException e) {
        super("Path gresit: "+e.getMessage());
    }
}
