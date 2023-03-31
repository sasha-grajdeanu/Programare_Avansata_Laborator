package Compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * This class contains methods that work with the catalog class
 */
public class CatalogCommand {
    /**
     * this method put information from the catalog class in a json document
     *
     * @param catalog the catalog what we want to save
     * @param path    the path where we want to save
     * @throws IOException input is not correct
     */
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper object = new ObjectMapper();
        object.writeValue(new File(path), catalog);
    }

    /**
     * this method put the information from a json file in a catalog class
     *
     * @param path the path where we find the json document
     * @return a catalog
     * @throws InvalidCatalogException if the catalog is invalid
     * @throws IOException             IO excption
     */
    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        ObjectMapper object = new ObjectMapper();
        if (!path.contains(".json")) {
            throw new InvalidCatalogException(new Exception());
        }
        return object.readValue(new File(path), Catalog.class);
    }

    public static String toString(Catalog catalog) {
        return catalog.toString();
    }

    /**
     * this method add in the catalog a document
     *
     * @param catalog the catalog where we want to add the document
     * @param d1      the document what we want to add in the catalog
     */
    public static void add(Catalog catalog, Document d1) {
        catalog.add(d1);
    }
}
