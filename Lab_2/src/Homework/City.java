package Homework;

/**
 * This class models a city.
 */
public class City extends Location {

    private int population;

    /**
     * all-args constructor
     *
     * @param name       name of the city
     * @param coordX     coordinate x
     * @param coordY     coordinate y
     * @param population the population of the city
     */
    public City(String name, int coordX, int coordY, int population) {
        super(name, coordX, coordY);
        this.population = population;
    }

    /**
     * setter for the population
     *
     * @param population the population of the city
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * getter of the population of the city
     *
     * @return the population of the city
     */
    public int getPopulation() {
        return population;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the location
     */
    @Override
    public String toString() {
        return new StringBuilder().append("City = {name = ").append(super.getName()).append(", coordX = ").append(super.getCoordX()).append(", coordY = ").append(super.getCoordY()).append(", population = ").append(this.getPopulation()).append("}").toString();

    }
}
