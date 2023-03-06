package Homework;

/**
 * This class models a highway
 */
public class Highway extends Road {

    private int taxPrice;

    /**
     * all-args constructor
     *
     * @param start    start location
     * @param finish   final location
     * @param length   the length
     * @param speed    the speed
     * @param taxPrice the value of tax
     */
    public Highway(Location start, Location finish, int length, int speed, int taxPrice) {
        super(start, finish, length, speed);
        this.taxPrice = taxPrice;
    }


    /**
     * getter for the value of the tax
     *
     * @return the value of the tax
     */
    public int getTaxPrice() {
        return taxPrice;
    }

    /**
     * setter for the value of the tax
     *
     * @param taxPrice the value of the tax
     */
    public void setTaxPrice(int taxPrice) {
        this.taxPrice = taxPrice;
    }

    /**
     * overriding method equals()
     *
     * @param o an object with which we will compare our class
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Highway highway = (Highway) o;
        return taxPrice == highway.taxPrice;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the highway
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Highway = {").append("start=").append(super.getStart()).append(", finish=").append(super.getFinish()).append(", speed=").append(super.getSpeed()).append(", length=").append(super.getLength()).append(", tax price= ").append(this.taxPrice).append('}').toString();
    }
}
