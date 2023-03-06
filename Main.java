package Homework;

public class Main {
    public static void main(String[] args) {

        Location[] listOfLocations = new Location[5];
        listOfLocations[0] = new City("Iasi", 10, 20, 350_000);
        listOfLocations[1] = new City("Pascani", 12, 34, 50_000);
        listOfLocations[2] = new GasStation("Petrom Iasi", 10, 21, 6.56f, 7.45f);
        listOfLocations[3] = new GasStation("SOCAR Halaucesti", 12, 35, 6.23f, 7.45f);
        listOfLocations[4] = new Airport("George Enescu Airport", 14, 45, 12);

        Road[] listOfRoads = new Road[4];
        listOfRoads[0] = new Highway(listOfLocations[0], listOfLocations[4], 234, 130, 0);
        listOfRoads[1] = new Country(listOfLocations[3], listOfLocations[2], 67, 40, "BAD");
        listOfRoads[2] = new Express(listOfLocations[0], listOfLocations[1], 100, 90, 2);
        listOfRoads[3] = new Express(listOfLocations[4], listOfLocations[2], 230, 90, 1);

        Problem instance = new Problem(listOfLocations, listOfRoads);
        System.out.println("the instance is " + instance.isValidInput());
        boolean test = instance.existRoadsBetween(listOfLocations[0], listOfLocations[2]);
        System.out.println("the answer is " + test);


    }
}
