package Homework;

/**
 * This class models an airport.
 */
public class Airport extends Location {
    private int terminals;

    /**
     * all-args constructor
     *
     * @param name      name of the airport
     * @param coordX    coordinate x of the airport
     * @param coordY    coordinate y of the airport
     * @param terminals number of the terminals
     */
    public Airport(String name, int coordX, int coordY, int terminals) {
        super(name, coordX, coordY);
        this.terminals = terminals;
    }

    /**
     * getter for the number of the terminals
     *
     * @return the number of the terminals
     */
    public int getTerminals() {
        return terminals;
    }

    /**
     * setter for the numbers of the terminals
     *
     * @param terminals number of the terminals
     */
    public void setTerminals(int terminals) {
        this.terminals = terminals;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the airport
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Airport = {name = ").append(super.getName()).append(", coordX = ").append(super.getCoordX()).append(", coordY = ").append(super.getCoordY()).append(", terminals = ").append(this.getTerminals()).append("}").toString();

    }
}
