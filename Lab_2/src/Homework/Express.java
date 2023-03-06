package Homework;

/**
 * This class models an express road
 */
public class Express extends Road {

    private int lanes;

    /**
     * all-args constructor
     *
     * @param start  start location
     * @param finish final location
     * @param length the length
     * @param speed  the speed
     * @param lanes  the number of the lanes
     */
    public Express(Location start, Location finish, int length, int speed, int lanes) {
        super(start, finish, length, speed);
        this.lanes = lanes;
    }

    /**
     * getter for the number of the lanes
     *
     * @return number of the lanes
     */
    public int getLanes() {
        return lanes;
    }

    /**
     * setter for the number of the lanes
     *
     * @param lanes number of the lanes
     */
    public void setLanes(int lanes) {
        this.lanes = lanes;
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
        Express express = (Express) o;
        return lanes == express.lanes;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the express road
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Express = {").append("start=").append(super.getStart()).append(", finish=").append(super.getFinish()).append(", speed=").append(super.getSpeed()).append(", length=").append(super.getLength()).append(", lanes= ").append(this.lanes).append('}').toString();
    }
}
