package Compulsory.describeMembers;

/**
 * This class represent a person.
 *
 * This class implements interface Node and Comparable<>
 */
public class Person implements Comparable<Person>, Node {
    private String nameOfPerson;

    /**
     * all-args constructor
     *
     * @param name name of the person
     */
    public Person(String name) {
        this.nameOfPerson = name;
    }

    /**
     * default constuctor
     */
    public Person() {

    }

    /**
     * implementation of the method getName from the interface Node
     *
     * @return name of the person
     */
    @Override
    public String getName() {
        return nameOfPerson;
    }

    /**
     * setter for the name of the person
     *
     * @param nameOfPerson name of the person
     */
    public void setNameOfPerson(String nameOfPerson) {
        this.nameOfPerson = nameOfPerson;
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
     * overriding method toString()
     *
     * @return a description of the person
     */
    @Override
    public String toString() {
        return "Person{" +
                "nameOfPerson='" + nameOfPerson + '\'' +
                '}';
    }
}
