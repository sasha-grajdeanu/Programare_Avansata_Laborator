package Homework.members;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * this class represent a company
 *
 * this class implement the interfaces Comparable<> and Node
 */
public class Company implements Comparable<Company>, Node {
    private String nameOfCompany;
    private Map<Node, String> relationships = new HashMap<>();

    public Company(String name) {
        this.nameOfCompany = name;
    }

    public Company() {
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    @Override
    public Map<Node, String> getRelationships() {
        return relationships;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("Company = [ name : ");
        value.append(this.nameOfCompany);
        value.append(" relationship = {");
        for(Map.Entry<Node, String> entry : relationships.entrySet())
        {
            value.append("[");
            value.append(entry.getKey().getName());
            value.append(", ");
            value.append(entry.getValue());
            value.append("]");
        }
        value.append("} ]");
        return value.toString();
    }

    /**
     * add a relation between the company and a node
     * @param node the key
     * @param value the value
     */
    public void addRelationship(Node node, String value) {
        relationships.put(node, value);
    }
    /**
     * implement of the method compareTo()
     *
     * @param o the object to be compared.
     * @return an int
     */
    @Override
    public int compareTo(Company o) {
        return this.nameOfCompany.compareTo(o.nameOfCompany);
    }

    /**
     * implementation of the method getName from the interface Node
     *
     * @return name of the person
     */
    @Override
    public String getName() {
        return this.nameOfCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(nameOfCompany, company.nameOfCompany) && Objects.equals(relationships, company.relationships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfCompany, relationships);
    }
}
