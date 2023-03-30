package Homework.Command;

import Homework.Catalog;
import Homework.Exception.WrongPath;
import Homework.Objects.Document;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class CommandAdapter{
    public void execute(Catalog catalog) throws IOException {}
    public void execute(Catalog catalog, Document document){}
    public void execute(Catalog catalog, String path) throws Compulsory.InvalidCatalogException, IOException, URISyntaxException, WrongPath {}
}
