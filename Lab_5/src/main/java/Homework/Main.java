package Homework;

import Compulsory.InvalidCatalogException;
import Homework.Command.*;
import Homework.Exception.WrongPath;
import Homework.Objects.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import static java.lang.System.exit;


public class Main {
    /**
     * this function return an integer necessary for the switch
     *
     * @param commandName the name of the command
     * @return an integer necessary for the switch
     */
    public static int command(String commandName) {
        if (commandName.contains("add")) {
            return 1;
        } else if (commandName.contains("list")) {
            return 2;
        } else if (commandName.contains("load")) {
            return 3;
        } else if (commandName.contains("report")) {
            return 4;
        } else if (commandName.contains("save")) {
            return 5;
        } else if (commandName.contains("toString")) {
            return 6;
        } else if (commandName.contains("view")) {
            return 7;
        } else if (commandName.contains("close")) {
            return 10;
        } else {
            return 0;
        }
    }

    /**
     * this function create a document
     *
     * @param name the name of the document
     * @param path the path of the document
     * @param id   the id of the document
     * @return a document
     */
    public static Document add(String name, String path, String id) {
        return new Document(name, path, id);
    }

    /**
     * this function is responsible with the read of the name, path and id of a document, create the document and add in a specified catalog
     *
     * @param catalog the catalog
     */
    public static void commandAdd(Catalog catalog) {
        AddCommand addCommand = new AddCommand();
        Scanner s = new Scanner(System.in);
        System.out.println("Furnizati numele documentului: ");
        String name = s.next();
        System.out.println("Furnizati id-ul documentului: ");
        String id = s.next();
        System.out.println("Furnizati path-ul documentului: ");
        String path = s.next();
        if (catalog.getName() == null) {
            System.out.println("Furnizati numele catalogului: ");
            String nameCatalog = s.next();
            catalog.setName(nameCatalog);
        }
        addCommand.execute(catalog, add(name, path, id));
    }

    /**
     * this function is responsible for listing the documents in a catalog
     *
     * @param catalog the catalog
     */
    public static void commandList(Catalog catalog) {
        ListCommand listCommand = new ListCommand();
        listCommand.execute(catalog);
    }

    /**
     * this function is responsible for loading a catalog with information from a json file
     *
     * @param catalog the catalog
     */
    public static void commandLoad(Catalog catalog) {
        LoadCommand loadCommand = new LoadCommand();
        Scanner s = new Scanner(System.in);
        System.out.println("Scrieti care este path-ul catalogului: ");
        String path = s.next();
        try {
            loadCommand.execute(catalog, path);
        } catch (InvalidCatalogException | WrongPath e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * this function is responsible for saving a catalog in a json file
     *
     * @param catalog the catalog
     */
    public static void commandSave(Catalog catalog) {
        SaveCommand saveCommand = new SaveCommand();
        Scanner s = new Scanner(System.in);
        System.out.println("Scrieti viitorul path complet al catalogului: ");
        String path = s.next();
        try {
            saveCommand.execute(catalog, path);
        } catch (WrongPath e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * this function is responsible for creating a report about the catalog
     *
     * @param catalog the catalog
     */
    public static void commandReport(Catalog catalog) {
        ReportCommand reportCommand = new ReportCommand();
        try {
            reportCommand.execute(catalog);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * this function calls the toString method for a catalog
     *
     * @param catalog the catalog
     */
    public static void commandToString(Catalog catalog) {
        ToStringCommand toStringCommand = new ToStringCommand();
        toStringCommand.execute(catalog);
    }

    /**
     * this class is responsible for opening a document identified with an id
     *
     * @param catalog the catalog
     */
    public static void commandView(Catalog catalog) {
        ViewCommand viewCommand = new ViewCommand();
        Scanner s = new Scanner(System.in);
        System.out.println("Scrieti id-ul documentului: ");
        String id = s.next();
        try {
            viewCommand.execute(catalog, id);
        } catch (URISyntaxException | IllegalArgumentException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * this function is responsible with the ending of the program
     */
    public static void commandClose() {
        exit(0);
    }

    /**
     * this function represent the entire application
     */
    public static void aplication() {
        Catalog catalog = new Catalog();
        while (true) {
            System.out.println("Introduceti o comanda: ");
            Scanner s = new Scanner(System.in);
            String commandString = s.next();
            int stade = command(commandString);
            switch (stade) {
                case 1 -> commandAdd(catalog);
                case 2 -> commandList(catalog);
                case 3 -> commandLoad(catalog);
                case 4 -> commandReport(catalog);
                case 5 -> commandSave(catalog);
                case 6 -> commandToString(catalog);
                case 7 -> commandView(catalog);
                case 10 -> commandClose();
                case 0 -> System.err.println("Comanda incorecta");
            }
        }
    }

    public static void main(String[] args) {
        aplication();
    }
}
