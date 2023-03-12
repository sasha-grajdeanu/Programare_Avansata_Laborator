package Homework.members;

import java.util.Map;

public interface Node {
    /**
     * this method return the name of an implementation
     *
     * @return a name of the implementation
     */
    String getName();

    /**
     * this method return a map with represent the relationship of the node
     *
     * @return a map with represent the relationship of the node
     */
    public Map<Node, String> getRelationships();
}
