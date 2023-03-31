package Homework.Command;

import Compulsory.InvalidCatalogException;
import Homework.Catalog;
import Homework.Exception.WrongPath;
import Homework.Objects.Document;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * this abstract class is the foundation for the necessary command
 */
public abstract class CommandAdapter {
    public void execute(Catalog catalog) throws IOException {
    }

    public void execute(Catalog catalog, Document document) {
    }

    public void execute(Catalog catalog, String path) throws Compulsory.InvalidCatalogException, IOException, URISyntaxException, WrongPath {
    }

    public Catalog execute(String path) throws InvalidCatalogException, WrongPath{
        return new Catalog();
    }
}
