package Problem;

import Problem.InstanceOfProblem.Project;
import Problem.InstanceOfProblem.Student;

import java.util.*;

public class Problem {
    private Map<Student, List<Project>> preferencies;

    /**
     * default constructor
     */
    public Problem() {
        this.preferencies = new HashMap<>();
    }

    /**
     * This method adds problem instances to the map.
     *
     * @param s a student
     * @param p a list of favorited projects
     */
    public void addPreferencies(Student s, List<Project> p) {
        this.preferencies.put(s, p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return Objects.equals(preferencies, problem.preferencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferencies);
    }

    /**
     * overriding method toString()
     *
     * @return a description of this problem
     */
    @Override
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        s1.append("Problem.Problem =[\n");
        for (Map.Entry<Student, List<Project>> entry : preferencies.entrySet()) {
            s1.append(entry.getKey());
            s1.append(" = ");
            s1.append(entry.getValue());
            s1.append(";\n");
        }
        s1.append("]");
        return s1.toString();
    }

    /**
     * getter
     *
     * @return a map
     */
    public Map<Student, List<Project>> getPreferencies() {
        return preferencies;
    }

    /**
     * setter
     *
     * @param preferencies a map
     */
    public void setPreferencies(Map<Student, List<Project>> preferencies) {
        this.preferencies = preferencies;
    }

    /**
     * This method shows us which students have a list length shorter than the average.
     */
    public void interogateMap() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Student, List<Project>> entry : preferencies.entrySet()) {
            studentList.add(entry.getKey());
        }
        List<Integer> noOfProject = new ArrayList<>();
        for (Map.Entry<Student, List<Project>> entry : preferencies.entrySet()) {
            noOfProject.add(entry.getValue().size());
        }
        studentList.stream().filter(s -> preferencies.get(s).size() < (noOfProject.stream().mapToInt(Integer::intValue).average().getAsDouble())).forEach(System.out::println);
    }


    /**
     * This algorithm solves the problem
     * <p>
     * It could be optimized if we sorted the map according to the length of the list of projects.
     */
    public String greedyAlg() {
        Map<Student, Project> mapOne = new HashMap<>();
        List<Project> visited = new ArrayList<>();
        for (Map.Entry<Student, List<Project>> entry : preferencies.entrySet()) {
            List<Project> auxTwo = entry.getValue();
            for (Project p : auxTwo) {
                if (!visited.contains(p)) {
                    visited.add(p);
                    mapOne.put(entry.getKey(), p);
                    break;
                }
            }
            if (!mapOne.containsKey(entry.getKey())) {
                mapOne.put(entry.getKey(), null);
            }
        }
        StringBuilder printed = new StringBuilder();
        printed.append("Solution: [\n");
        for (Map.Entry<Student, Project> entry : mapOne.entrySet()) {
            printed.append(entry.getKey());
            printed.append(" : ");
            printed.append(entry.getValue());
            printed.append(";\n");
        }
        printed.append("]");
        return printed.toString();
    }
}
