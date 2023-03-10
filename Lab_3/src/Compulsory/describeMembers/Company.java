package Compulsory.describeMembers;

public class Company implements Comparable<Company>, Node {
    private String nameOfCompany;

    /**
     * all-args constructor
     *
     * @param name the name of company
     */
    public Company(String name) {
        this.nameOfCompany = name;
    }

    /**
     * default constructor
     */
    public Company() {
    }

    /**
     * setter for the name of company
     *
     * @param nameOfCompany the name of company
     */
    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    /**
     * overriding method toString()
     *
     * @return a description of the person
     */
    @Override
    public String toString() {
        return "Company{" +
                "nameOfCompany='" + nameOfCompany + '\'' +
                '}';
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
}
