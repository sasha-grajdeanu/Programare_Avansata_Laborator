package Compulsory;

import Compulsory.describeMembers.Company;
import Compulsory.describeMembers.Node;
import Compulsory.describeMembers.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * for the exercise no. 4
 */
public class Main {

    public static void createAndPrintList() {
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Company("Amazon"));
        nodeList.add(new Company("Dacia"));
        nodeList.add(new Company("CTP Iasi"));
        nodeList.add(new Company("CFR Calatori"));
        nodeList.add(new Person("Marian"));
        nodeList.add(new Person("Iosif"));
        nodeList.add(new Person("Magda"));
        nodeList.add(new Person("Liza"));
        Collections.sort(nodeList, (Node n1, Node n2) ->n1.getName().compareTo(n2.getName()));
        System.out.println(nodeList);
    }

    public static void main(String[] args) {
        createAndPrintList();
    }
}
