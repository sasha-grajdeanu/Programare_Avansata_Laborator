package Compulsory;

/**
 * clasa main unde testam clasele Location si Road
 */

public class Main {

    /**
     * In main dam exemple pentru clasele Location si Road si testam o parte din metode
     *
     * @param args
     */
    public static void main(String[] args) {
        Location iasi = new Location("Iasi", LocationType.CITY, 10, 20);
        Location adjud = new Location("Adjud", LocationType.CITY, 5, 40);
        System.out.println(iasi);
        System.out.println(adjud);
        Road autobahn = new Road(RoadType.HIGHWAY, iasi, adjud, 244, 130);
        System.out.println(autobahn);
        Location bacau = new Location("George Enescu Airport", LocationType.AIRPORT, 6, 37);
        System.out.println(bacau);
    }
}