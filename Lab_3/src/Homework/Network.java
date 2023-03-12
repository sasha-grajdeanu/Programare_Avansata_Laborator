package Homework;

import Homework.members.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class represent a network, populated with some nodes
 */
public class Network {
    private List<Node> nodes;

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * this method computes how many connections a node has in the network
     *
     * @param node the node which we want to compute the importance
     * @return an integer who represent the importance
     */
    public int getImportance(Node node) {
        int nrConnex = 0;
        if (!this.nodes.contains(node)) {
            return nrConnex;
        } else {
            nrConnex = node.getRelationships().size();
            for (Node value : nodes) {
                if (value.equals(node))
                    continue;
                else {
                    if (value.getRelationships().containsKey(node) && value.getRelationships() != null) {
                        nrConnex++;
                    }
                }
            }
        }
        return nrConnex;
    }

    /**
     * first, we sort the list according to the importance of the node
     *
     * @return a desciption of the network
     */
    @Override
    public String toString() {
        StringBuilder returnedValue = new StringBuilder("Network = \n");
        Collections.sort(this.nodes, (Node n1, Node n2) -> Integer.compare(getImportance(n2), getImportance(n1)));
        for (Node node : this.nodes) {
            returnedValue.append(node);
            returnedValue.append(" | importance = ");
            returnedValue.append(getImportance(node));
            returnedValue.append('\n');
        }
        return returnedValue.toString();
    }
}
