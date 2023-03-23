import Problem.InstanceOfProblem.Project;
import Problem.InstanceOfProblem.Student;
import Problem.Problem;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    /**
     * this function create an array with students, put this array in a linked list,
     * sort the linked list using lambda function by the name and print the linked list
     */
    public static void sectionWithStudents() {
        var students = IntStream.rangeClosed(0, 5).mapToObj(i -> new Student("S" + i)).toArray(Student[]::new);
        List<Student> listOfStudents = new LinkedList<>();
        Collections.addAll(listOfStudents, students);
        Collections.sort(listOfStudents, (x, y) -> x.getName().compareTo(y.getName()));
        System.out.println(listOfStudents);
    }

    /**
     * this function create an array with projects, put this array in a tree set,
     * and print the tree set
     */
    public static void sectionWithProjects() {
        var projects = IntStream.rangeClosed(0, 5).mapToObj(i -> new Project("P" + i)).toArray(Project[]::new);
        Set<Project> listOfProjects = new TreeSet<>();
        Collections.addAll(listOfProjects, projects);
        System.out.println(listOfProjects);
    }

    /**
     * This function create a random instance
     *
     * @return a problem
     */
    public static Problem sectionWithProblem() {
        Problem p1 = new Problem();
        Random rand = new Random();
        Faker faker = new Faker();
        Set<Project> listOfProject = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            listOfProject.add(new Project(faker.ancient().hero()));
        }
        for (int i = 0; i < 20; i++) {
            int luckyNumber = rand.nextInt(listOfProject.size()) + 1;
            List<Project> auxiliar = new ArrayList<>(listOfProject);
            List<Project> listMe = new ArrayList<>();
            for (int j = 0; j < luckyNumber; j++) {
                int randomNumber = rand.nextInt(auxiliar.size());
                listMe.add(auxiliar.get(randomNumber));
                auxiliar.remove(randomNumber);
            }
            p1.addPreferencies(new Student(faker.name().fullName()), listMe);
        }
        return p1;
    }

    /**
     * This function is responsible for solving the requirements of the homework.
     */
    public static void homework() {
        Problem instance = sectionWithProblem();
        System.out.println(instance);
        instance.interogateMap();
        String solved = instance.greedyAlg();
        System.out.println(solved);
    }

    /**
     * This function is responsible for solving the requirements of the compulsory.
     */
    public static void compulsory() {
        sectionWithStudents();
        sectionWithProjects();
    }

    public static void main(String[] args) {
        compulsory();
        homework();
    }
}
