package Compulsory;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a representation of a catalog
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Catalog implements Serializable {
    @NonNull
    private String name;
    private List<Document> contains = new ArrayList<>();
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
}
