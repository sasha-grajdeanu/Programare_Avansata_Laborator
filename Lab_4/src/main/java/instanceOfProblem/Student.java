package instanceOfProblem;

import java.util.Objects;

/**
 * this class is a representation for the student
 * <p>
 * this class implements the interface Comparable.
 */
public class Student implements Comparable<Student> {
    private String name;

    /**
     * all-args constructor
     *
     * @param name the name of the student
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * getter for the name of the student
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name of the student
     *
     * @param name the name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * overriding the method toString()
     *
     * @return a description of the class
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * implementation of compareTo()
     *
     * @param o the object to be compared.
     * @return an integer
     */
    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.getName());
    }

    /**
     * overriding method equals
     * @param o an object
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    /**
     * overriding method hashCode
     *
     * @return an integer represent the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
