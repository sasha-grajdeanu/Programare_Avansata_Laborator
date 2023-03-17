package instanceOfProblem;

/**
 * this class is a representation for the project
 * <p>
 * this class implements the interface Comparable.
 */
public class Project implements Comparable<Project> {
    private String name;

    /**
     * all-args constructor
     *
     * @param name the name of the project
     */
    public Project(String name) {
        this.name = name;
    }

    /**
     * getter for the name of the project
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name of the project
     *
     * @param name the name of the project
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
        return "Project{" +
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
    public int compareTo(Project o) {
        return this.name.compareTo(o.getName());
    }
}