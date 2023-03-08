package Homework;

import java.util.Objects;

/**
 * This class models a country road
 */
public class Country extends Road {

    private String condition;

    /**
     * all-args constructor for the class Country
     *
     * @param start     start location
     * @param finish    final location
     * @param length    the length
     * @param speed     the speed
     * @param condition the condition of the country road
     */
    public Country(Location start, Location finish, int length, int speed, String condition) {
        super(start, finish, length, speed);
        this.condition = condition;
    }

    /**
     * getter of the condition of the country road
     *
     * @return the condition of the country road
     */
    public String getCondition() {
        return condition;
    }

    /**
     * setter of the condition of the country road
     *
     * @param condition the condition of the country road
     */
    public void setCondition(String condition) {
        this.condition = condition;
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
        Country country = (Country) o;
        return Objects.equals(condition, country.condition);
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the country road
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Country = {").append("start=").append(super.getStart()).append(", finish=").append(super.getFinish()).append(", speed=").append(super.getSpeed()).append(", length=").append(super.getLength()).append(", condition= ").append(this.condition).append('}').toString();
    }
}
