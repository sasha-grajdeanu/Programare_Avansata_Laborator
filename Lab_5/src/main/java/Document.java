import java.io.Serializable;
import java.util.Objects;


/**
 * this class is a representation of a document
 */
public class Document implements Serializable {
    private String name;
    private String path;
    private String id;

    public Document(String name, String path, String id) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
    public Document()
    {

    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", id='" + id + '\'' +
                "}\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(name, document.name) && Objects.equals(path, document.path) && Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, id);
    }

}
