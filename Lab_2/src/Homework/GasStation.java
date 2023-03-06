package Homework;

/**
 * This class models a gas station.
 */
public class GasStation extends Location {
    private float gasolinePrice;
    private float dieselPrice;

    /**
     * all-args constructor
     *
     * @param name          the name of the gas station
     * @param coordX        the coordinate x
     * @param coordY        the coordinate y
     * @param gasolinePrice the price of gasoline
     * @param dieselPrice   the price of diesel fuel
     */
    public GasStation(String name, int coordX, int coordY, float gasolinePrice, float dieselPrice) {
        super(name, coordX, coordY);
        this.gasolinePrice = gasolinePrice;
        this.dieselPrice = dieselPrice;
    }

    /**
     * getter for the price of gasoline
     *
     * @return the price of gasoline
     */
    public float getGasolinePrice() {
        return gasolinePrice;
    }

    /**
     * setter for the price of gasoline
     *
     * @param gasolinePrice the price of gasoline
     */
    public void setGasolinePrice(float gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    /**
     * getter for the price of diesel fuel
     *
     * @return the price of diesel fuel
     */
    public float getDieselPrice() {
        return dieselPrice;
    }

    /**
     * setter for the price of diesel fuel
     *
     * @param dieselPrice the price of gasoline
     */
    public void setDieselPrice(float dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the airport
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Gas Station = {name = ").append(super.getName()).append(", coordX = ").append(super.getCoordX()).append(", coordY = ").append(super.getCoordY()).append(", gasoline price = ").append(this.getGasolinePrice()).append(", diesel price = ").append(this.getDieselPrice()).append("}").toString();
    }
}

