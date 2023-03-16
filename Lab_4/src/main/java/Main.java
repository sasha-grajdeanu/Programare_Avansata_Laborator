import instanceOfProblem.Project;
import instanceOfProblem.Student;

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

    public static void main(String[] args) {
        sectionWithStudents();
        sectionWithProjects();
    }
}
