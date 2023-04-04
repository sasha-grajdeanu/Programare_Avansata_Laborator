package org.example;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * a class where is memorized related information about selection of the player
 */
@Data
public class Player implements Serializable {
    public Map<Integer, Set<Integer>> subgraph = new HashMap<>();
    public List<Edge> edges = new ArrayList<>();

    /**
     * a method to find if in the graph exist a triangle
     *
     * @return true if in the graph exist a triangle, false otherwise
     */
    public boolean hasATriangle() {
        System.out.println(edges);
        for (int j = 0; j < edges.size(); j++) {
            for (Map.Entry<Integer, Set<Integer>> entry : subgraph.entrySet()) {
                Edge edge1 = new Edge(edges.get(j).y, entry.getKey());
                Edge edge2 = new Edge(entry.getKey(), edges.get(j).x);
                if (edges.contains(edge1) && edges.contains(edge2)) {
                    System.out.println("GASIT");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * a method to put information
     *
     * @param node1 coord x
     * @param node2 coord y
     */
    public void addEdge(int node1, int node2) {
        Edge edge1 = new Edge(node1, node2);
        edges.add(edge1);
        Edge edge2 = new Edge(node2, node1);
        edges.add(edge2);
        if (!subgraph.containsKey(node1)) {
            subgraph.put(node1, new HashSet<>());
        }
        if (!subgraph.containsKey(node2)) {
            subgraph.put(node2, new HashSet<>());
        }
        subgraph.get(node1).add(node2);
        subgraph.get(node2).add(node1);
    }
}
