package Homework.Command;

import Homework.Catalog;
import Homework.Exception.WrongPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
@NoArgsConstructor
public class SaveCommand extends CommandAdapter{
    @Override
    public void execute(Catalog catalog, String path) throws WrongPath {
        ObjectMapper object = new ObjectMapper();
        try {
            object.writeValue(new File(path+'\\'+catalog.getName()+".json"), catalog);
        } catch (IOException e) {
            throw new WrongPath(e);
        }
    }
}
