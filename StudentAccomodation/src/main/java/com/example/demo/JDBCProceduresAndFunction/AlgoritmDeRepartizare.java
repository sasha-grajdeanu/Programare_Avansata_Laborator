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
    List<Student> student;
    List<Camin> camins;
    Map<Integer, List<Integer>> preferencies;

    Map<Integer, Integer> repartizare = new HashMap<>();

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

    public void sortare() throws SQLException {
        studentiAnIntaiM.clear();
        studentiAnIntaiF.clear();
        studentiAnDoiF.clear();
        studentiAnDoiM.clear();
        studentiAnTreiM.clear();
        studentiAnTreiF.clear();
        repartizare.clear();
        this.student = listOfStudent.studentiInscrisi();
        this.camins = listOfCamine.camineDate();
        this.preferencies = preferenciesOfStudent.preferinteleStudentilor();
        for (Student student1 : this.student) {
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
        for (Camin camin : camins) {
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
                locuri3M =  (locuri3M + loc3M);
                System.out.println(locuri3M);
            }
            if (camin.getCapacitateF() != 0) {
                int loc1M = round(camin.getCapacitateF() * ((float) this.studentiAnIntaiF.size() / studentiF));
                System.out.println(loc1M + " " +  loc1M);
                locuriAnIntaiF.put(camin.getId(),  loc1M);
                locuri1F =  (locuri1F + loc1M);
                System.out.println(locuri1F);
                int loc2M = round(camin.getCapacitateF() * ((float) this.studentiAnDoiF.size() / studentiF));
                System.out.println(loc2M + " " +  loc2M);
                locuriAnDoiF.put(camin.getId(), loc2M);
                locuri2F = (locuri2F + loc2M);
                System.out.println(locuri2F);
                int loc3M = round(camin.getCapacitateF() * ((float) this.studentiAnTreiF.size() / studentiF));
                System.out.println(loc3M + " " + loc3M);
                locuriAnTreiF.put(camin.getId(),  loc3M);
                locuri3F =  (locuri3F + loc3M);
                System.out.println(locuri3F);
            }
        }
        int sum = locuri1M + locuri2M + locuri3M;
        System.out.println(sum);
        for (Map.Entry<Integer, Integer> camines : locuriAnIntaiM.entrySet()) {
            System.out.println(camines.getKey() + " " + camines.getValue());
        }
    }

    public void repartitie() throws SQLException {
        sortare();
        impartireLocuri();
        completeRoom(studentiAnIntaiM, locuri1M, locuriAnIntaiM);
        completeRoom(studentiAnDoiM, locuri2M, locuriAnDoiM);
        completeRoom(studentiAnTreiM, locuri3M, locuriAnTreiM);
        completeRoom(studentiAnIntaiF, locuri1F, locuriAnIntaiF);
        completeRoom(studentiAnDoiF, locuri2F, locuriAnDoiF);
        completeRoom(studentiAnTreiF, locuri3F, locuriAnTreiF);
        insertInDatabase();
    }

    private void insertInDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM REPARTIZARE")) {
                int deletedRows = deleteStatement.executeUpdate();
                System.out.println("Executat");
            }
            for (Map.Entry<Integer, Integer> repartitii : repartizare.entrySet()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("insert into REPARTIZARE (ID_STUDENT, ID_CAMIN) values (?, ?)")) {
                    preparedStatement.setInt(1, repartitii.getKey());
                    preparedStatement.setInt(2, repartitii.getValue());
                    int val = preparedStatement.executeUpdate();
                    System.out.println(val);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void completeRoom(List<Student> studentiAn, int locuri, Map<Integer, Integer> locuriDate){
        for (Student student1 : studentiAn) {
            if (locuri != 0) {
                int verificare = locuri;
                int id_student = student1.getId();
                for (Map.Entry<Integer, List<Integer>> e : preferencies.entrySet()) {
                    if (e.getKey() == id_student) {
                        for (int i = 0; i < e.getValue().size(); i++) {
                            for (Map.Entry<Integer, Integer> camines : locuriDate.entrySet()) {
                                if (e.getValue().get(i) == camines.getKey()) {
                                    if (camines.getValue() != 0) {
                                        repartizare.put(id_student, camines.getKey());
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
