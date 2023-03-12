package Homework;

import Homework.members.Company;
import Homework.members.Node;
import Homework.members.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * in this class we test the functionalities of the created classes
 */
public class Main {

    /**
     * this function populates a list with persons, companies and relationship
     *
     * @return a list with some nodes
     */
    public static List<Node> population() {
        Person andrei = new Person("Andrei", LocalDate.of(2000, 1, 1));
        Person vasile = new Person("Vasile", LocalDate.of(2000, 1, 1));
        Person java = new Person("Java", LocalDate.of(2000, 1, 1));
        Person silica = new Person("Silica", LocalDate.of(2000, 1, 1));
        Person fanus = new Person("Fanus", LocalDate.of(2000, 1, 1));

        Company cfrCalatori = new Company("CFR Calatori");
        Company bitdefender = new Company("Bitdefender");
        Company ura = new Company("Uzina De Reparatii Avioane");

        andrei.addRelationship(vasile, "Friend");
        andrei.addRelationship(fanus, "Enemy/Dusman");
        andrei.addRelationship(ura, "munca");

        vasile.addRelationship(silica, "Enemy");
        vasile.addRelationship(andrei, "Friend");
        vasile.addRelationship(fanus, "Colegu");
        vasile.addRelationship(cfrCalatori, "munca");
        vasile.addRelationship(java, "Enemy");

        fanus.addRelationship(vasile, "Colegu");
        fanus.addRelationship(cfrCalatori, "munca");
        fanus.addRelationship(andrei, "Enemy/ Dusman");
        fanus.addRelationship(silica, "Friend");

        java.addRelationship(bitdefender, "munca");
        java.addRelationship(andrei, "necunoscut");
        java.addRelationship(fanus, "necunoscut");
        java.addRelationship(vasile, "necunoscut");
        java.addRelationship(silica, "cunoscut");

        silica.addRelationship(bitdefender, "munca");
        silica.addRelationship(vasile, "necunoscut");
        silica.addRelationship(fanus, "friend");
        silica.addRelationship(andrei, "necunoscut");
        silica.addRelationship(java, "limbajul nostru cel de toate zilele");

        ura.addRelationship(andrei, "Angajat");
        cfrCalatori.addRelationship(vasile, "Angajat");
        cfrCalatori.addRelationship(fanus, "Angajat");
        bitdefender.addRelationship(java, "Angajat");
        bitdefender.addRelationship(silica, "Angajat");

        List<Node> listOfNodes = new ArrayList<>();

        listOfNodes.add(andrei);
        listOfNodes.add(vasile);
        listOfNodes.add(java);
        listOfNodes.add(silica);
        listOfNodes.add(fanus);
        listOfNodes.add(cfrCalatori);
        listOfNodes.add(bitdefender);
        listOfNodes.add(ura);

        return listOfNodes;
    }

    /**
     * this function create a network and print it
     */
    public static void createAndPrintOurNetwork() {
        Network n1 = new Network(population());
        System.out.println(n1);
    }

    public static void main(String[] args) {
        createAndPrintOurNetwork();
    }
}
