package Compulsory;

/**
 * @author Grajdeanu Alexandru-Cristian
 * Clasa Location descrie o locatie cu un anumit nume, un anume tip si care se afla la coordonatele date.
 */
public class Location {

    //members
    private String name;
    //numele locatiei
    private LocationType type;
    //tipul locatiei
    private int coordX;
    //coordonata x a locatiei
    private int coordY;
    //coordonata y a locatiei


    /**
     * Constructorul pentru clasa Location, ce primeste ca parametri un nume, tipul locatiei si coordonatele
     *
     * @param name numele locatiei
     * @param type tipul locatiei
     * @param x    coordonata x a locatiei
     * @param y    coordonata y a locatiei
     */
    public Location(String name, LocationType type, int x, int y) {
        this.coordX = x;
        this.coordY = y;
        this.name = name;
        this.type = type;
    }

    /**
     * functie setType, cu rol de a seta tipul locatiei
     *
     * @param type tipul locatiei
     */
    public void setType(LocationType type) {
        this.type = type;
    }

    /**
     * functie setName, cu rol de a seta numele locatiei
     *
     * @param name numele locatiei
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * functie setCoordX, cu rol de a seta coordonata x (latitudinea) locatiei
     *
     * @param coordX coordonata x a locatiei
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * functie setCoordY, cu rol de a seta coordonata y (longitudinea) locatiei
     *
     * @param coordY coordonata y a locatiei
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * returneaza coordonata x (latitudinea) locatiei
     *
     * @return coordX
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * returneaza coordonata y (longitudinea) locatiei
     *
     * @return coordY
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * returneaza numele locatiei
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returneaza tipul locatiei
     *
     * @return type
     */
    public LocationType getType() {
        return type;
    }

    /**
     * suprascrie metoda toString() pentru clasa Location
     *
     * @return Location ca string
     */
    @Override
    public String toString() {
        String describeLocation = "Location = [ name = " + this.name + "; type = " + this.type + "; X coordinate = " + this.coordX + "; Y coordinate = " + this.coordY + " ]";
        return describeLocation;
    }
}

