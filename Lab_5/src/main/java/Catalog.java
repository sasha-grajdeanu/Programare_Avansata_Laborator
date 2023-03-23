import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * This class is a representation of a catalog
 */
public class Catalog implements Serializable {
    private String name;
    private List<Document> contains = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog() {

    }

    public Catalog(String name, List<Document> docs) {
        this.contains = docs;
        this.name = name;
    }

    /**
     * this method put a document in the catalog
     *
     * @param d1 the document
     */
    public void add(Document d1) {
        this.contains.add(d1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(name, catalog.name) && Objects.equals(contains, catalog.contains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contains);
    }

    public List<Document> getContains() {
        return contains;
    }

    public void setContains(List<Document> contains) {
        this.contains = contains;
    }

    @Override
    public String toString() {
        return "Catalog{\n" +
                "name='" + name + "'\n" +
                "contains=\n" + contains +
                '}';
    }

    /**
     * this method search in catalog the document with the id
     *
     * @param id document id
     * @return the document with the id/null
     */
    public Document findById(String id) {
        for (Document doc : this.contains) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }
}
