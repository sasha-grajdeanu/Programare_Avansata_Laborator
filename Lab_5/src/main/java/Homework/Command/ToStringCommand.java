package Homework.Command;

import Homework.Catalog;

/**
 * this class calls the toString method
 * <p>
 * this class extends the abstract class CommandAdapter
 */
public class ToStringCommand extends CommandAdapter {
    public void execute(Catalog catalog) {
        System.out.println(catalog.toString());
    }
}
