package Homework.Command;

import Compulsory.InvalidCatalogException;
import Homework.Catalog;
import Homework.Exception.WrongPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * this class is responsible for creating a class that contains information from a json file
 * <p>
 * this class extends the abstract class CommandAdapter
 */
@NoArgsConstructor
public class LoadCommand extends CommandAdapter {
    @Override
    public void execute(Catalog catalog, String path) throws InvalidCatalogException, WrongPath {
        ObjectMapper object = new ObjectMapper();
        if (!path.contains(".json")) {
            throw new InvalidCatalogException(new Exception("Nu este de tipul JSON."));
        }
        try {
            catalog = object.readValue(new File(path), Catalog.class);
        } catch (IOException e) {
            throw new WrongPath(e);
        }
    }
}
