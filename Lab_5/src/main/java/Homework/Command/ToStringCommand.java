package Homework.Command;

import Homework.Catalog;

public class ToStringCommand extends CommandAdapter{
    public void execute(Catalog catalog){
        System.out.println(catalog.toString());
    }
}
