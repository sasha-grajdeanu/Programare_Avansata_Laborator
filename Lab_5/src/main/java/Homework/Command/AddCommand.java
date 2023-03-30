package Homework.Command;

import Homework.Catalog;
import Homework.Objects.Document;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AddCommand extends CommandAdapter {
    @Override
    public void execute(Catalog catalog, Document document) {
        catalog.add(document);
    }
}
