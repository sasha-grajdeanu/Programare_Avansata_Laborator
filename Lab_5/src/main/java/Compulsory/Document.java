package Compulsory;

import lombok.*;

import java.io.Serializable;
import java.util.Map;


/**
 * this class is a representation of a document
 */
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Document implements Serializable {
    private String name;
    private String path;
    private String id;
    private Map<String, String> tagList;

    public void addTag(String key, String value)
    {
        tagList.put(key, value);
    }

}
