import java.io.IOException;

public class Main {
    /**
     * this function is for testing the functionality of the created classes
     */
    public static void compulsory()
    {
        Document d1 = new Document("Istoria Romaniei", "ddr", "12-ED-1234");
        Document d2 = new Document("Istoria Americii", "ddr", "13-ED-1345");
        Document d3 = new Document("Istoria Europei", "ddr", "12-EV-3422");
        Catalog catalog = new Catalog("Istorie");
        CatalogCommand.add(catalog, d1);
        CatalogCommand.add(catalog, d2);
        CatalogCommand.add(catalog, d3);
        System.out.println(CatalogCommand.toString(catalog));
        try {
            CatalogCommand.save(catalog, "test.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Catalog c2 = null;
        try {
            c2 = CatalogCommand.load("test.json");
        } catch (InvalidCatalogException | IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(c2);
        }

    }
    public static void main(String [] args) {
        compulsory();
    }
}
