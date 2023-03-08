package Homework;


/**
 * This class models a location with a name and some coordinates.
 */
public class Location {
    private String name;
    private int coordX;
    private int coordY;


    /**
     * default constructor
     */
    public Location() {
    }

    /**
     * all-args constructor
     *
     * @param name   name of location
     * @param coordX coordinate x
     * @param coordY coordinate y
     */
    public Location(String name, int coordX, int coordY) {
        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * setter for coordY
     *
     * @param coordY coordinate y
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * setter for coordX
     *
     * @param coordX coordinate x
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * setter for name
     *
     * @param name name of location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the name of location
     *
     * @return name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the coordinate X of location
     *
     * @return coordinate x of the location
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * getter for the coordinate Y of location
     *
     * @return coordinate y of the location
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the location
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Location = {name = ").append(name).append(", coordX = ").append(coordX).append(", coordY = ").append(coordY).append("}").toString();
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
        Location location = (Location) o;
        return coordX == location.coordX && coordY == location.coordY && name == location.name;
    }
}
