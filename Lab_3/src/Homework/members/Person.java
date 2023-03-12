package Homework.members;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * this class represent a person
 * <p>
 * this class implement the interfaces Comparable<> and Node
 */
public class Person implements Comparable<Person>, Node {
    private String nameOfPerson;
    private LocalDate birthDate;
    private Map<Node, String> relationships = new HashMap<>();

    public Person(String nameOfPerson, LocalDate birthDate) {
        this.nameOfPerson = nameOfPerson;
        this.birthDate = birthDate;
    }

    /**
     * add a relation between the person and a node
     *
     * @param node  the key
     * @param value the value
     */
    public void addRelationship(Node node, String value) {
        relationships.put(node, value);
    }

    public Person() {
    }

    public void setNameOfPerson(String nameOfPerson) {
        this.nameOfPerson = nameOfPerson;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * implement of the method compareTo()
     *
     * @param o the object to be compared.
     * @return an int
     */
    @Override
    public int compareTo(Person o) {
        return this.nameOfPerson.compareTo(o.nameOfPerson);
    }

    /**
     * implementation of the method getName from the interface Node
     *
     * @return name of the person
     */
    @Override
    public String getName() {
        return this.nameOfPerson;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("Person = [ name : ");
        value.append(this.nameOfPerson);
        value.append(", birthday= ");
        value.append(this.birthDate);
        value.append(" relationship = {");
        for (Map.Entry<Node, String> entry : relationships.entrySet()) {
            value.append("[");
            value.append(entry.getKey().getName());
            value.append(", ");
            value.append(entry.getValue());
            value.append("]");
        }
        value.append("} ]");
        return value.toString();
    }

    @Override
    public Map<Node, String> getRelationships() {
        return relationships;
    }
}
