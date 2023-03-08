package Homework;

import java.util.Objects;

/**
 * This class models a road with two location, the length and the speed.
 */
public class Road {
    private Location start;
    private Location finish;
    private int speed;
    private int length;

    /**
     * default constructor
     */
    public Road() {
    }

    /**
     * all-args constructor
     *
     * @param start  start location
     * @param finish final location
     * @param length the length
     * @param speed  the speed
     */
    public Road(Location start, Location finish, int length, int speed) {
        this.start = start;
        this.finish = finish;
        this.length = length;
        this.speed = speed;
    }

    /**
     * getter for the start location
     *
     * @return start location
     */
    public Location getStart() {
        return start;
    }

    /**
     * getter for the speed
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * getter for the final location
     *
     * @return final location
     */
    public Location getFinish() {
        return finish;
    }

    /**
     * getter for the length
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * setter for the start location
     *
     * @param start start location
     */
    public void setStart(Location start) {
        this.start = start;
    }

    /**
     * setter for the speed of road
     *
     * @param speed the speed of road
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * setter for the length of road
     *
     * @param length the length of road
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * setter for the finish location
     *
     * @param finish finish location
     */
    public void setFinish(Location finish) {
        this.finish = finish;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the road
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Road = {").append("start=").append(start).append(", finish=").append(finish).append(", speed=").append(speed).append(", length=").append(length).append('}').toString();
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
        Road road = (Road) o;
        return speed == road.speed && length == road.length && Objects.equals(start, road.start) && Objects.equals(finish, road.finish);
    }
}
