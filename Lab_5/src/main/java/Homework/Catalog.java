package Homework;

import Homework.Objects.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a representation of a catalog
 */
public class Catalog implements Serializable {
    private String name;
    private List<Document> contains = new ArrayList<>();

    public Catalog(String name, List<Document> contains) {
        this.name = name;
        this.contains = contains;
    }

    public Catalog() {
    }

    /**
     * this method put a document in the catalog
     *
     * @param d1 the document
     */
    public void add(Document d1) {
        this.contains.add(d1);
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

    public String getName() {
        return this.name;
    }

    public List<Document> getContains() {
        return this.contains;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContains(List<Document> contains) {
        this.contains = contains;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Catalog)) return false;
        final Catalog other = (Catalog) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$contains = this.getContains();
        final Object other$contains = other.getContains();
        if (this$contains == null ? other$contains != null : !this$contains.equals(other$contains)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Catalog;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $contains = this.getContains();
        result = result * PRIME + ($contains == null ? 43 : $contains.hashCode());
        return result;
    }

    public String toString() {
        return "Catalog(name=" + this.getName() + ", contains=" + this.getContains() + ")";
    }
}
