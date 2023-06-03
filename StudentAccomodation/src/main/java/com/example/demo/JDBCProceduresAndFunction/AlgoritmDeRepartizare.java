package com.example.demo.JDBCProceduresAndFunction;

import com.example.demo.Entities.An;
import com.example.demo.Entities.Camin;
import com.example.demo.Entities.Gen;
import com.example.demo.Entities.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static java.lang.Math.round;

@Data
@Repository
public class AlgoritmDeRepartizare {
    List<Student> students;
    List<Camin> campus;
    Map<Integer, List<Integer>> preferencies;

    Map<Integer, Integer> repartition = new HashMap<>();

    int locuri1M = 0;
    int locuri2M = 0;
    int locuri3M = 0;
    int locuri1F = 0;
    int locuri2F = 0;
    int locuri3F = 0;

    List<Student> studentiAnIntaiM = new ArrayList<>();
    List<Student> studentiAnDoiM = new ArrayList<>();
    List<Student> studentiAnTreiM = new ArrayList<>();
    List<Student> studentiAnIntaiF = new ArrayList<>();
    List<Student> studentiAnDoiF = new ArrayList<>();
    List<Student> studentiAnTreiF = new ArrayList<>();

    Map<Integer, Integer> locuriAnIntaiM = new HashMap<>();
    Map<Integer, Integer> locuriAnDoiM = new HashMap<>();
    Map<Integer, Integer> locuriAnTreiM = new HashMap<>();
    Map<Integer, Integer> locuriAnIntaiF = new HashMap<>();
    Map<Integer, Integer> locuriAnDoiF = new HashMap<>();
    Map<Integer, Integer> locuriAnTreiF = new HashMap<>();


    ListOfCamine listOfCamine;
    ListOfStudent listOfStudent;
    PreferenciesOfStudent preferenciesOfStudent;
    private DataSource dataSource;

    @Autowired
    public AlgoritmDeRepartizare(DataSource dataSource, ListOfStudent listOfStudent, ListOfCamine listOfCamine, PreferenciesOfStudent preferenciesOfStudent) {
        this.dataSource = dataSource;
        this.listOfCamine = listOfCamine;
        this.listOfStudent = listOfStudent;
        this.preferenciesOfStudent = preferenciesOfStudent;
    }

    private void reset()
    {
        locuri1M = 0;
        locuri2M = 0;
        locuri3M = 0;
        locuri1F = 0;
        locuri2F = 0;
        locuri3F = 0;
        studentiAnIntaiM.clear();
        studentiAnIntaiF.clear();
        studentiAnDoiF.clear();
        studentiAnDoiM.clear();
        studentiAnTreiM.clear();
        studentiAnTreiF.clear();
        locuriAnIntaiM.clear();
        locuriAnIntaiF.clear();
        locuriAnDoiM.clear();
        locuriAnDoiF.clear();
        locuriAnTreiM.clear();
        locuriAnTreiF.clear();
        repartition.clear();
        students.clear();
        campus.clear();
        preferencies.clear();
    }

    public void sortare() throws SQLException {
        if(this.students != null){
            this.reset();
        }
        this.students = listOfStudent.studentiInscrisi();
        this.campus = listOfCamine.camineDate();
        this.preferencies = preferenciesOfStudent.preferinteleStudentilor();
        for (Student student1 : this.students) {
            System.out.println(student1.toString());
            if (student1.getAn() == An.LICENTA_1) {
                if (student1.getGen() == Gen.MASCULIN) {
                    studentiAnIntaiM.add(student1);
                } else {
                    studentiAnIntaiF.add(student1);
                }
            } else if (student1.getAn() == An.LICENTA_2) {
                if (student1.getGen() == Gen.MASCULIN) {
                    studentiAnDoiM.add(student1);
                } else {
                    studentiAnDoiF.add(student1);
                }
            } else {
                if (student1.getGen() == Gen.MASCULIN) {
                    studentiAnTreiM.add(student1);
                } else {
                    studentiAnTreiF.add(student1);
                }
            }
        }
        for (int i = 0; i < studentiAnIntaiM.size(); i++) {
            System.out.println(studentiAnIntaiM.get(i).toString());
        }
        studentiAnIntaiM.sort(Comparator.comparingInt(Student::getPunctaj).reversed());
        studentiAnDoiM.sort(Comparator.comparingInt(Student::getPunctaj).reversed());
        studentiAnTreiM.sort(Comparator.comparingInt(Student::getPunctaj).reversed());
        studentiAnIntaiF.sort(Comparator.comparingInt(Student::getPunctaj).reversed());
        studentiAnDoiF.sort(Comparator.comparingInt(Student::getPunctaj).reversed());
        studentiAnTreiF.sort(Comparator.comparingInt(Student::getPunctaj).reversed());
        for (int i = 0; i < studentiAnIntaiM.size(); i++) {
            System.out.println(studentiAnIntaiM.get(i).toString());
        }
    }

    public void impartireLocuri() {
        int studentiM = this.studentiAnTreiM.size() + this.studentiAnIntaiM.size() + this.studentiAnDoiM.size();
        System.out.println("nrs" + studentiM);
        int studentiF = this.studentiAnTreiF.size() + this.studentiAnIntaiF.size() + this.studentiAnDoiF.size();
        System.out.println(studentiF);
        for (Camin camin : campus) {
            System.out.println(camin.toString());
            if (camin.getCapacitateM() != 0) {
                int loc1M = round(camin.getCapacitateM() * ((float) this.studentiAnIntaiM.size() / studentiM));
                System.out.println(loc1M + " " + loc1M);
                locuriAnIntaiM.put(camin.getId(), loc1M);
                locuri1M = (locuri1M + loc1M);
                System.out.println(locuri1M);
                int loc2M = round(camin.getCapacitateM() * ((float) this.studentiAnDoiM.size() / studentiM));
                System.out.println(loc2M + " " + loc2M);
                locuriAnDoiM.put(camin.getId(), loc2M);
                locuri2M = (locuri2M + loc2M);
                System.out.println(locuri2M);
                int loc3M = round(camin.getCapacitateM() * ((float) this.studentiAnTreiM.size() / studentiM));
                System.out.println(loc3M + " " + loc3M);
                locuriAnTreiM.put(camin.getId(), loc3M);
                locuri3M = (locuri3M + loc3M);
                System.out.println(locuri3M);
            }
            if (camin.getCapacitateF() != 0) {
                int loc1M = round(camin.getCapacitateF() * ((float) this.studentiAnIntaiF.size() / studentiF));
                System.out.println(loc1M + " " + loc1M);
                locuriAnIntaiF.put(camin.getId(), loc1M);
                locuri1F = (locuri1F + loc1M);
                System.out.println(locuri1F);
                int loc2M = round(camin.getCapacitateF() * ((float) this.studentiAnDoiF.size() / studentiF));
                System.out.println(loc2M + " " + loc2M);
                locuriAnDoiF.put(camin.getId(), loc2M);
                locuri2F = (locuri2F + loc2M);
                System.out.println(locuri2F);
                int loc3M = round(camin.getCapacitateF() * ((float) this.studentiAnTreiF.size() / studentiF));
                System.out.println(loc3M + " " + loc3M);
                locuriAnTreiF.put(camin.getId(), loc3M);
                locuri3F = (locuri3F + loc3M);
                System.out.println(locuri3F);
            }
        }
        int sum = locuri1M + locuri2M + locuri3M;
        System.out.println(sum);
        for (Map.Entry<Integer, Integer> camines : locuriAnIntaiM.entrySet()) {
            System.out.println(camines.getKey() + " " + camines.getValue());
        }
    }

    public boolean repartitie(){
        try {
            sortare();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("inceput");
        impartireLocuri();
        System.out.println("inceput2");
        completeRoom(studentiAnIntaiM, locuri1M, locuriAnIntaiM);
        completeRoom(studentiAnDoiM, locuri2M, locuriAnDoiM);
        completeRoom(studentiAnTreiM, locuri3M, locuriAnTreiM);
        completeRoom(studentiAnIntaiF, locuri1F, locuriAnIntaiF);
        completeRoom(studentiAnDoiF, locuri2F, locuriAnDoiF);
        completeRoom(studentiAnTreiF, locuri3F, locuriAnTreiF);
        try {
            insertInDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void insertInDatabase() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM REPARTIZARE");
        deleteStatement.executeUpdate();
        for (Map.Entry<Integer, Integer> repartitii : repartition.entrySet()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into REPARTIZARE (ID_STUDENT, ID_CAMIN) values (?, ?)")) {
                preparedStatement.setInt(1, repartitii.getKey());
                preparedStatement.setInt(2, repartitii.getValue());
                preparedStatement.executeUpdate();
            }
        }
        deleteStatement.close();
        connection.close();
    }

    private void completeRoom(List<Student> studentiAn, int locuri, Map<Integer, Integer> locuriDate) {
        for (Student student1 : studentiAn) {
            System.out.println("hajde");
            if (locuri != 0) {
                int verificare = locuri;
                int id_student = student1.getId();
                for (Map.Entry<Integer, List<Integer>> e : preferencies.entrySet()) {
                    System.out.println("hajde2");
                    if (e.getKey() == id_student) {
                        for (int i = 0; i < e.getValue().size(); i++) {
                            System.out.println("hajde3");
                            for (Map.Entry<Integer, Integer> camines : locuriDate.entrySet()) {
                                System.out.println("hajde4");
                                if (Objects.equals(e.getValue().get(i), camines.getKey())) {
                                    if (camines.getValue() != 0) {
                                        System.out.println("hajde5");
                                        repartition.put(id_student, camines.getKey());
                                        System.out.println(id_student + " " + camines.getKey());
                                        locuriDate.put(camines.getKey(), camines.getValue() - 1);
                                        locuri--;
                                        break;
                                    }
                                }
                            }
                            if (locuri < verificare) {
                                break;
                            }
                        }
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }
}
