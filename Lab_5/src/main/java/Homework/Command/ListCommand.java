package Homework.Command;

import Homework.Catalog;
import lombok.NoArgsConstructor;

/**
 * this class is responsible for displaying documents from a catalog
 * <p>
 * this class extends abstract class CommandAdapter
 */
@NoArgsConstructor
public class ListCommand extends CommandAdapter {
    @Override
    public void execute(Catalog catalog) {
        for (int i = 0; i < catalog.getContains().size(); i++) {
            System.out.println(catalog.getContains().get(i));
        }
    }
}
