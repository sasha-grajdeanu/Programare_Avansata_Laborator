package Homework.Command;

import Homework.Catalog;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ListCommand extends CommandAdapter{
    @Override
    public void execute(Catalog catalog) {
        for (int i =0; i<catalog.getContains().size(); i++)
        {
            System.out.println(catalog.getContains().get(i));
        }
    }
}
