package Homework.Command;

import Homework.Catalog;
import Homework.Objects.Document;
import lombok.NoArgsConstructor;

/**
 * this class has a method that adds a document to the specified catalog
 * <p>
 * this class extends the abstract class CommandAdapter
 */
@NoArgsConstructor
public class AddCommand extends CommandAdapter {
    @Override
    public void execute(Catalog catalog, Document document) {
        catalog.add(document);
    }
}
