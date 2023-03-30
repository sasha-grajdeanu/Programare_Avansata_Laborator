package Homework.Command;

import Homework.Catalog;
import Homework.Exception.WrongPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * this class is responsible for creating a json file that contains information about a catalog
 * <p>
 * this class extends the abstract class CommandAdapter
 */
@NoArgsConstructor
public class SaveCommand extends CommandAdapter {
    @Override
    public void execute(Catalog catalog, String path) throws WrongPath {
        ObjectMapper object = new ObjectMapper();
        try {
            object.writeValue(new File(path + '\\' + catalog.getName() + ".json"), catalog);
        } catch (IOException e) {
            throw new WrongPath(e);
        }
    }
}
